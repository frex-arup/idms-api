package com.drivesoft.service;

import com.drivesoft.constant.IdmsApiConstants;
import com.drivesoft.dto.AccountDto;
import com.drivesoft.dto.IdmsAccountApiResponse;
import com.drivesoft.dto.IdmsAccountApiResponse.RowDataDTO;
import com.drivesoft.entity.Account;
import com.drivesoft.mapper.AccountMapper;
import com.drivesoft.repository.AccountRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class IdmsService {
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;
    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;

    @Value("${idms.api.base-url}")
    private String idmsApiBaseUrl;
    @Value("${idms.api.username}")
    private String idmsApiUsername;
    @Value("${idms.api.password}")
    private String idmsApiPassword;
    @Value("${idms.api.layout-id}")
    private String idmsApiLayoutId;
    @Value("${idms.api.institution-id}")
    private String idmsApiInstitutionId;
    @Value("${idms.api.account-status}")
    private String idmsApiAccountStatus;
    @Value("${idms.api.page-number}")
    private Number idmsApiPageNumber;


    private String getUserAuthorizationToken() throws JsonProcessingException {
        String apiUrl = idmsApiBaseUrl + String.format(IdmsApiConstants.GET_USER_AUTHORIZATION_TOKEN, idmsApiUsername, idmsApiPassword, idmsApiInstitutionId);

        String response = restTemplate.getForObject(apiUrl, String.class);
        // Parse the String into a Map using Jackson ObjectMapper
        Map<String, Object> jsonResp = objectMapper.readValue(response, new TypeReference<Map<String, Object>>() {
        });
        return Objects.toString(jsonResp.get("Token"), "");
    }

    public void syncAccountList(Number currentPage, boolean syncAll) throws JsonProcessingException {
        String apiUrlTemplate = idmsApiBaseUrl + String.format(IdmsApiConstants.GET_ACCOUNT_LIST,
                getUserAuthorizationToken(),
                idmsApiLayoutId,
                idmsApiAccountStatus,
                "%d");

        // Pre-fetch all existing account IDs in the database
        Set<String> existingAccountIds = new HashSet<>(accountRepository.findAllAccountIds());

        Number totalPages;

        do {
            String apiUrl = String.format(apiUrlTemplate, currentPage.intValue());
            IdmsAccountApiResponse resp = restTemplate.getForObject(apiUrl, IdmsAccountApiResponse.class);

            if (resp == null || CollectionUtils.isEmpty(resp.getData())
                    || !resp.getStatus().equals(200)) {
                break; // Exit if no data is received.
            }

            totalPages = resp.getTotalPages();
            List<RowDataDTO> data = resp.getData();

            // Filter out existing accounts
            List<AccountDto> newAccounts = data.stream()
                    .map(RowDataDTO::getRow)
                    .filter(account -> !existingAccountIds.contains(account.getAcctID()))
                    .toList();

            if (!newAccounts.isEmpty()) {
                // Map to entities and save the new accounts
                List<Account> accounts = accountMapper.toEntityList(newAccounts);
                accountRepository.saveAll(accounts);

                // Add newly saved accounts to the existingAccountIds set to avoid re-checking
                newAccounts.stream()
                        .map(AccountDto::getAcctID)
                        .forEach(existingAccountIds::add);
            }

            currentPage = currentPage.intValue() + 1;
        } while (currentPage.intValue() <= totalPages.intValue() && syncAll);
    }


}
