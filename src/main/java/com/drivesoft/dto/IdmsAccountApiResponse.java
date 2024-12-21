package com.drivesoft.dto;

import lombok.Data;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@Data
public class IdmsAccountApiResponse {
    @JsonProperty("Status")
    private Number status;

    @JsonProperty("Message")
    private String message;

    @JsonProperty("TotalRecords")
    private Number totalRecords;

    @JsonProperty("TotalPages")
    private Number totalPages;

    @JsonProperty("PageNumber")
    private Number pageNumber;

    @JsonProperty("BeginningPage")
    private Number beginningPage;

    @JsonProperty("EndingPage")
    private Number endingPage;

    @JsonProperty("Data")
    private List<RowDataDTO> data;

    @Data
    public static class RowDataDTO {
        @JsonProperty("Row")
        private AccountDto row;
    }


}
