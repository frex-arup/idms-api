package com.drivesoft.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "accounts")
public class Account extends BaseEntity {

    private BigDecimal contractSalesPrice;
    private String acctType;
    private String salesGroupPerson1ID;
    private LocalDateTime contractDate;
    private String collateralStockNumber;
    private String collateralYearModel;
    private String collateralMake;
    private String collateralModel;
    private String borrower1FirstName;
    private String borrower1LastName;
    private String acctID;
}
