package com.drivesoft.service;

import com.drivesoft.dto.AuthRequest;
import com.drivesoft.dto.AuthResponse;
import com.drivesoft.entity.User;
import com.drivesoft.exception.UnAuthorizedException;
import com.drivesoft.repository.UserRepository;
import com.drivesoft.security.JwtService;
import com.google.common.base.Preconditions;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Log4j2
@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Value("${application.security.jwt.expiration}")
    private long jwtExpiration;
    @Value("${application.security.jwt.refresh-token.expiration}")
    private long refreshExpiration;


    public AuthResponse authenticate(AuthRequest request) {
        Preconditions.checkNotNull(request, "request cannot be null");
        Preconditions.checkNotNull(request.getGrantType(), "grantType cannot be null");
        return switch (request.getGrantType()) {
            case PASSWORD -> authenticateUsingPassword(request);
            case REFRESH_TOKEN -> authenticateUsingRefreshToken(request);
        };
    }

    private AuthResponse authenticateUsingPassword(AuthRequest request) {
        Preconditions.checkArgument(StringUtils.isNotBlank(request.getUsername()), "Username cannot be blank");
        Preconditions.checkArgument(StringUtils.isNotBlank(request.getPassword()), "Password cannot be blank");
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        var user = userRepository.findByUsername(request.getUsername())
                .orElseThrow();
        return getAuthResponseFromUser(user);
    }

    private AuthResponse authenticateUsingRefreshToken(AuthRequest request) {
        String refreshToken = request.getRefreshToken();
        Preconditions.checkArgument(StringUtils.isNotBlank(refreshToken), "Refresh token cannot be blank");
        String username = jwtService.extractUsername(refreshToken);
        if (StringUtils.isBlank(username)) {
            throw new UnAuthorizedException("Invalid refresh token");
        }

        User user = this.userRepository.findByUsername(username)
                .orElseThrow(() -> new UnAuthorizedException("User not found"));

        if (!jwtService.isTokenExpired(refreshToken)) {
            return getAuthResponseFromUser(user);
        } else {
            throw new UnAuthorizedException("Refresh token expired");
        }
    }

    private AuthResponse getAuthResponseFromUser(User user) {
        // Convert it to UserDetails
        String password = Optional.ofNullable(user.getPassword()).orElse("");
        org.springframework.security.core.userdetails.User userDetails =
                new org.springframework.security.core.userdetails.User(user.getUsername(), password, List.of());

        var accessToken = jwtService.generateToken(userDetails);
        var refreshToken = jwtService.generateRefreshToken(userDetails);
        return AuthResponse.builder()
                .accessToken(accessToken)
                .expiresIn(jwtExpiration / 1000)
                .refreshToken(refreshToken)
                .refreshTokenExpiresIn(refreshExpiration / 1000)
                .tokenType("Bearer")
                .build();
    }

}