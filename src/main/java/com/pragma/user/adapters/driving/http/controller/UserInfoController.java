package com.pragma.user.adapters.driving.http.controller;


import com.pragma.user.domain.api.IUserServicePort;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.pragma.user.configuration.Constants.*;

@RestController
@RequestMapping(USER_PATH)
@AllArgsConstructor
@Tag(name = USER_INFO_TAG, description = USER_INFO_DESCRIPTION)
public class UserInfoController {
    private final IUserServicePort userServicePort;

    @GetMapping(GET_PHONE_NUMBER_PATH)
    @Operation(summary = GET_PHONE_NUMBER_SUMMARY, description = GET_PHONE_NUMBER_DESCRIPTION)
    public String getPhoneNumber(@RequestParam Long clientId) {
        return userServicePort.getPhoneNumber(clientId);
    }
}


