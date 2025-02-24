package com.pragma.user.adapters.driving.http.controller;

import com.pragma.user.adapters.driving.http.dto.user.request.CreateUserRequest;
import com.pragma.user.adapters.driving.http.mapper.user.IUserRequestMapper;
import com.pragma.user.domain.api.IUserServicePort;
import com.pragma.user.domain.model.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static com.pragma.user.configuration.Constants.*;

@RestController
@RequestMapping(CREATE_USER_PATH)
@RequiredArgsConstructor
@Tag(name = USER_MANAGEMENT1, description = API_FOR_CREATING_DIFFERENT_TYPES_OF_USERS)
public class CreateUserController {

    private final IUserRequestMapper userRequestMapper;
    private final IUserServicePort userServicePort;

    @PostMapping(CREATE_OWNER_PATH)
    @Operation(summary = CREATE_OWNER_SUMMARY, description = CREATE_OWNER_DESCRIPTION)
    public void createOwner(@RequestBody CreateUserRequest request) {
        User user = userRequestMapper.toDomain(request);
        userServicePort.createOwner(user);
    }

    @PostMapping(CREATE_ADMIN_PATH)
    @Operation(summary = CREATE_ADMIN_SUMMARY, description = CREATE_ADMIN_DESCRIPTION)
    public void createAdmin(@RequestBody CreateUserRequest request) {
        User user = userRequestMapper.toDomain(request);
        userServicePort.createAdmin(user);
    }

    @PostMapping(CREATE_CUSTOMER_PATH)
    @Operation(summary = CREATE_CUSTOMER_SUMMARY, description = CREATE_CUSTOMER_DESCRIPTION)
    public void createCustomer(@RequestBody CreateUserRequest request) {
        User user = userRequestMapper.toDomain(request);
        userServicePort.createCustomer(user);
    }

    @PostMapping(CREATE_EMPLOYEE_PATH)
    @Operation(summary = CREATE_EMPLOYEE_SUMMARY, description = CREATE_EMPLOYEE_DESCRIPTION)
    public void createEmployee(@RequestBody CreateUserRequest request, @RequestParam long restaurantId) {
        User user = userRequestMapper.toDomain(request);
        userServicePort.createEmployee(user, restaurantId);
    }
}