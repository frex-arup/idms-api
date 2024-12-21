package com.drivesoft.mapper;

import com.drivesoft.dto.AccountDto;
import com.drivesoft.entity.Account;
import org.springframework.stereotype.Component;

@Component
public class AccountMapper implements BaseMapper<Account, AccountDto> {
    @Override
    public AccountDto toDto(Account account) {
        AccountDto accountDto = new AccountDto();
        accountDto.setContractSalesPrice(account.getContractSalesPrice());
        accountDto.setAcctType(account.getAcctType());
        accountDto.setSalesGroupPerson1ID(account.getSalesGroupPerson1ID());
        accountDto.setContractDate(account.getContractDate());
        accountDto.setCollateralStockNumber(account.getCollateralStockNumber());
        accountDto.setCollateralYearModel(account.getCollateralYearModel());
        accountDto.setCollateralMake(account.getCollateralMake());
        accountDto.setCollateralModel(account.getCollateralModel());
        accountDto.setBorrower1FirstName(account.getBorrower1FirstName());
        accountDto.setBorrower1LastName(account.getBorrower1LastName());
        setAuditColumn(account, accountDto);
        return accountDto;
    }

    @Override
    public Account toEntity(AccountDto accountDto) {
        Account account = new Account();
        account.setContractSalesPrice(accountDto.getContractSalesPrice());
        account.setAcctType(accountDto.getAcctType());
        account.setSalesGroupPerson1ID(accountDto.getSalesGroupPerson1ID());
        account.setContractDate(accountDto.getContractDate());
        account.setCollateralStockNumber(accountDto.getCollateralStockNumber());
        account.setCollateralYearModel(accountDto.getCollateralYearModel());
        account.setCollateralMake(accountDto.getCollateralMake());
        account.setCollateralModel(accountDto.getCollateralModel());
        account.setBorrower1FirstName(accountDto.getBorrower1FirstName());
        account.setBorrower1LastName(accountDto.getBorrower1LastName());
        account.setAcctID(accountDto.getAcctID());
        return account;
    }
}
