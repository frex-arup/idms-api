package com.drivesoft.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseDTO {
    private String id;
    private String createdBy;
    private String updatedBy;
    private OffsetDateTime createdOn;
    private OffsetDateTime updatedOn;
}
