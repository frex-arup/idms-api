package com.drivesoft.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class AccountDto extends BaseDTO {
    @JsonProperty("ContractSalesPrice")
    private BigDecimal contractSalesPrice;

    @JsonProperty("AcctType")
    private String acctType;

    @JsonProperty("SalesGroupPerson1ID")
    private String salesGroupPerson1ID;

    @JsonProperty("SalesGroupPerson1Commission")
    private BigDecimal salesGroupPerson1Commission;

    @JsonProperty("SalesGroupPerson2ID")
    private Number salesGroupPerson2ID;

    @JsonProperty("SalesGroupPerson2Commission")
    private BigDecimal salesGroupPerson2Commission;

    @JsonProperty("SalesManagerID")
    private Number salesManagerID;

    @JsonProperty("FIUserID")
    private Number fiUserID;

    @JsonProperty("ContractCashDown")
    private BigDecimal contractCashDown;

    @JsonProperty("ContractTotalDeferredDown")
    private BigDecimal contractTotalDeferredDown;

    @JsonProperty("CurDownPayBal")
    private BigDecimal curDownPayBal;

    @JsonProperty("AcctCurEstimatedPayoff")
    private BigDecimal acctCurEstimatedPayoff;

    @JsonProperty("ContractTotalFees")
    private BigDecimal contractTotalFees;

    @JsonProperty("ContractTotalTaxes")
    private BigDecimal contractTotalTaxes;

    @JsonProperty("ContractTotalTradeIn")
    private BigDecimal contractTotalTradeIn;

    @JsonProperty("ContractTotalTradeInPayoff")
    private BigDecimal contractTotalTradeInPayoff;

    @JsonProperty("LenderName")
    private String lenderName;

    @JsonProperty("Borrower1State")
    private String borrower1State;

    @JsonProperty("ContractTotalProductCost")
    private BigDecimal contractTotalProductCost;

    @JsonProperty("ContractTotalProductSalesPrice")
    private BigDecimal contractTotalProductSalesPrice;

    @JsonProperty("ContractAmountFinanced")
    private BigDecimal contractAmountFinanced;

    @JsonProperty("WarrantyPrice")
    private BigDecimal warrantyPrice;

    @JsonProperty("WarrantyContractCost")
    private BigDecimal warrantyContractCost;

    @JsonProperty("OptionalVSIPrice")
    private BigDecimal optionalVSIPrice;

    @JsonProperty("OptionalVSICost")
    private BigDecimal optionalVSICost;

    @JsonProperty("DealID")
    private Number dealID;

    @JsonProperty("TotalAcctCollected")
    private BigDecimal totalAcctCollected;

    @JsonProperty("PrimaryLoanTotalMiscFeeAdjusted")
    private BigDecimal primaryLoanTotalMiscFeeAdjusted;

    @JsonProperty("ContractDate")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "M/d/yyyy h:mm:ss a")
@DateTimeFormat(pattern = "M/d/yyyy h:mm:ss a")
    private LocalDateTime contractDate;

    @JsonProperty("CollateralStockNumber")
    private String collateralStockNumber;

    @JsonProperty("AcctCurTotalBalance")
    private BigDecimal acctCurTotalBalance;

    @JsonProperty("Borrower1FullName")
    private String borrower1FullName;

    @JsonProperty("LenderDesc")
    private String lenderDesc;

    @JsonProperty("CollateralBuyerID")
    private Number collateralBuyerID;

    @JsonProperty("CollateralDaysOnLot")
    private Number collateralDaysOnLot;

    @JsonProperty("CollateralAcquiredDate")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "M/d/yyyy h:mm:ss a")
@DateTimeFormat(pattern = "M/d/yyyy h:mm:ss a")
    private LocalDateTime collateralAcquiredDate;

    @JsonProperty("AcctStatus")
    private String acctStatus;

    @JsonProperty("CollateralVIN")
    private String collateralVIN;

    @JsonProperty("Borrower1SSN")
    private String borrower1SSN;

    @JsonProperty("Borrower2SSN")
    private String borrower2SSN;

    @JsonProperty("Borrower1Address1")
    private String borrower1Address1;

    @JsonProperty("Borrower2FullName")
    private String borrower2FullName;

    @JsonProperty("BookedDate")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "M/d/yyyy h:mm:ss a")
@DateTimeFormat(pattern = "M/d/yyyy h:mm:ss a")
    private LocalDateTime bookedDate;

    @JsonProperty("DealWorksheetID")
    private Number dealWorksheetID;

    @JsonProperty("Borrower1City")
    private String borrower1City;

    @JsonProperty("Borrower1Zipcode")
    private String borrower1Zipcode;

    @JsonProperty("Borrower2City")
    private String borrower2City;

    @JsonProperty("Borrower2AddressZipcode")
    private String borrower2AddressZipcode;

    @JsonProperty("AcctID")
    private String acctID;

    @JsonProperty("CurDueAmt")
    private BigDecimal curDueAmt;

    @JsonProperty("NumDaysSinceContractDate")
    private Number numDaysSinceContractDate;

    @JsonProperty("ContractLoanRate")
    private BigDecimal contractLoanRate;

    @JsonProperty("PrimaryLoanOrigTermInMonths")
    private Number primaryLoanOrigTermInMonths;

    @JsonProperty("PrimaryLoanCSRegPaymentAmt")
    private BigDecimal primaryLoanCSRegPaymentAmt;

    @JsonProperty("CollateralYearModel")
    private String collateralYearModel;

    @JsonProperty("CollateralMake")
    private String collateralMake;

    @JsonProperty("CollateralModel")
    private String collateralModel;

    @JsonProperty("PrimaryLoanCSPaymentFrequency")
    private String primaryLoanCSPaymentFrequency;

    @JsonProperty("Borrower1FirstName")
    private String borrower1FirstName;

    @JsonProperty("Borrower1LastName")
    private String borrower1LastName;

    @JsonProperty("Borrower2FirstName")
    private String borrower2FirstName;

    @JsonProperty("Borrower2LastName")
    private String borrower2LastName;

    @JsonProperty("AcctLastPaidDate")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "M/d/yyyy h:mm:ss a")
@DateTimeFormat(pattern = "M/d/yyyy h:mm:ss a")
    private LocalDateTime acctLastPaidDate;

    @JsonProperty("CollateralAcquiredFromTypeID")
    private Number collateralAcquiredFromTypeID;

    @JsonProperty("CollateralTotalCost")
    private BigDecimal collateralTotalCost;

    @JsonProperty("DebtCancellationCost")
    private BigDecimal debtCancellationCost;

    @JsonProperty("DebtCancellation")
    private BigDecimal debtCancellation;

    @JsonProperty("SalesLocationName")
    private String salesLocationName;

    @JsonProperty("SalesLocationDesc")
    private String salesLocationDesc;

    @JsonProperty("PrimaryLoanCSPaymentsRemaininginMonths")
    private Number primaryLoanCSPaymentsRemaininginMonths;

    @JsonProperty("ServiceContractCompanyName")
    private String serviceContractCompanyName;

    @JsonProperty("GAPCompanyName")
    private String gapCompanyName;

    @JsonProperty("DebtCancellationCompanyName")
    private String debtCancellationCompanyName;

    @JsonProperty("Borrower1CellPhone")
    private String borrower1CellPhone;
}