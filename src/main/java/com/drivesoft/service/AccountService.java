package com.drivesoft.service;

import com.drivesoft.dto.AccountDto;
import com.drivesoft.exception.ResourceNotFoundException;
import com.drivesoft.mapper.AccountMapper;
import com.drivesoft.repository.AccountRepository;
import com.google.common.base.Preconditions;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j2
@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;

    public Page<AccountDto> getAllAccounts(Pageable pageable) {
        return accountRepository.findAll(pageable)
                .map(accountMapper::toDto);
    }

    public AccountDto getAccountById(String id) {
        Preconditions.checkArgument(StringUtils.isNotBlank(id), "id cannot be blank");
        return accountRepository.findById(id)
                .map(accountMapper::toDto)
                .orElseThrow(() ->
                new ResourceNotFoundException("Account not found with id: " + id));
    }

    public void deleteAccountById(String id) {
        Preconditions.checkArgument(StringUtils.isNotBlank(id), "id cannot be blank");
        accountRepository.deleteById(id);
    }

    public AccountDto createAccount(AccountDto accountDto) {
        Preconditions.checkNotNull(accountDto, "accountDto cannot be null");
        Preconditions.checkNotNull(accountDto.getAcctID(), "acctID cannot be null");
        return accountMapper.toDto(accountRepository.save(accountMapper.toEntity(accountDto)));
    }

    public AccountDto updateAccount(String id, AccountDto accountDto) {
        Preconditions.checkArgument(StringUtils.isNotBlank(id), "id cannot be blank");
        Preconditions.checkNotNull(accountDto, "accountDto cannot be null");
        Preconditions.checkNotNull(accountDto.getAcctID(), "acctID cannot be null");
        Preconditions.checkArgument(accountRepository.existsById(id), "Account not found with id: " + id);
        accountDto.setId(id);
        return accountMapper.toDto(accountRepository.save(accountMapper.toEntity(accountDto)));
    }
}
