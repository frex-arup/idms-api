package com.drivesoft.controller;

import com.drivesoft.dto.AccountDto;
import com.drivesoft.dto.ApiResponse;
import com.drivesoft.service.AccountService;
import com.drivesoft.service.IdmsService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/accounts")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;
    private final IdmsService idmsService;

    @GetMapping
    public ApiResponse getAllAccounts(Pageable pageable) {
        Page<AccountDto> allAccounts = accountService.getAllAccounts(pageable);
        return ApiResponse.builder()
                .data(allAccounts.getContent())
                .total(allAccounts.getTotalElements())
                .pageNum(allAccounts.getNumber())
                .pageSize(allAccounts.getSize())
                .totalPages(allAccounts.getTotalPages())
                .build();
    }

    @GetMapping("/{id}")
    public ApiResponse getAccountById(@PathVariable String id) {
        return ApiResponse.builder()
                .data(accountService.getAccountById(id))
                .build();
    }

    @DeleteMapping("/{id}")
    public void deleteAccountById(@PathVariable String id) {
        accountService.deleteAccountById(id);
    }

    @PostMapping
    public ApiResponse createAccount(@RequestBody AccountDto accountDto) {
        return ApiResponse.builder()
                .data(accountService.createAccount(accountDto))
                .build();
    }

    @PutMapping("/{id}")
    public ApiResponse updateAccount(@PathVariable String id, @RequestBody AccountDto accountDto) {
        return ApiResponse.builder()
                .data(accountService.updateAccount(id, accountDto))
                .build();
    }

    @GetMapping("sync-data-from-idms")
    public ApiResponse syncDataFromIdms(
            @RequestParam(required = false, defaultValue = "true") boolean syncAll
    ) throws JsonProcessingException {
        idmsService.syncAccountList(1, syncAll);
        return ApiResponse.builder()
                .data("Accounts synced successfully from IDMS")
                .build();
    }
}
