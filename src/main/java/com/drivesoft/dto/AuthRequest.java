package com.drivesoft.dto;

import com.drivesoft.constant.GrantType;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthRequest {
    private String username;
    private String password;
    @JsonProperty("grant_type")
    private GrantType grantType;
    @JsonProperty("refresh_token")
    private String refreshToken;
}