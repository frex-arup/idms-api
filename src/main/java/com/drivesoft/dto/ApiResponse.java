package com.drivesoft.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class ApiResponse {
    private String status;
    private String message;
    private Number total;
    private Object data;
    private Number totalPages;
    private Number pageNum;
    private Number pageSize;
    private Number errorCode;
}