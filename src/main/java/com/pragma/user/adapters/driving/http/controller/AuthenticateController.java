package com.pragma.user.adapters.driving.http.controller;


import com.pragma.user.adapters.driving.http.dto.user.request.AuthorizationRequest;
import com.pragma.user.adapters.driving.http.dto.user.request.OwnerRequest;
import com.pragma.user.adapters.driving.http.dto.user.request.LoginRequest;
import com.pragma.user.adapters.driving.http.dto.user.response.AuthorizationResponse;
import com.pragma.user.adapters.driving.http.dto.user.response.OwnerResponse;
import com.pragma.user.adapters.driving.http.mapper.login.request.LoginRequestMapper;
import com.pragma.user.domain.model.AuthResponse;
import com.pragma.user.adapters.securityconfig.AuthService;
import com.pragma.user.domain.api.IUserServicePort;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.pragma.user.configuration.Constants.*;


@RestController
@RequestMapping(AUTH_USER)
@RequiredArgsConstructor
public class AuthenticateController {


    private final AuthService authService;
    private final IUserServicePort userServicePort;
    private final LoginRequestMapper loginRequestMapper;

    @Operation(summary = FIND_USER_ID_BY_USERNAME, description = FIND_USER_DESCRIPTION)
    @ApiResponses(value = {
            @ApiResponse(responseCode = RESPONSE_OK, description = USER_ID_FOUND,
                    content = @Content(schema = @Schema(implementation = Long.class))),
            @ApiResponse(responseCode = RESPONSE_NOT_FOUND, description = USER_NOT_FOUND,
                    content = @Content)
    })
    @PostMapping(VALIDATE_OWNER_PATH)
    public OwnerResponse validateOwner(@RequestBody OwnerRequest request) {
        return userServicePort.validateOwner(request.getId());
    }

    @PostMapping(VALIDATE_TOKEN_PATH)
    public AuthorizationResponse validateToken(@RequestBody AuthorizationRequest request) {
        return userServicePort.validateToken(request.getToken());
    }

    @Operation(summary = USER_LOGIN_SUMMARY, description = USER_LOGIN_DESCRIPTION)
    @ApiResponses(value = {
            @ApiResponse(responseCode = RESPONSE_OK, description = USER_AUTHENTICATED,
                    content = @Content(schema = @Schema(implementation = AuthResponse.class))),
            @ApiResponse(responseCode = RESPONSE_UNAUTHORIZED, description = UNAUTHORIZED,
                    content = @Content)
    })
    @PostMapping(LOGIN_PATH)
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(userServicePort.login(loginRequestMapper.toDomain(request)));
    }
}

