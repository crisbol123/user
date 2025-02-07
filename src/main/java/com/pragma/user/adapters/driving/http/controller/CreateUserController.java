package com.pragma.user.adapters.driving.http.controller;



import com.pragma.user.adapters.driving.http.dto.user.request.CreateUserRequest;
import com.pragma.user.adapters.driving.http.mapper.user.IUserRequestMapper;
import com.pragma.user.domain.api.IUserServicePort;
import com.pragma.user.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/create-user")
@RequiredArgsConstructor
public class CreateUserController {
    private final IUserRequestMapper userRequestMapper;
    private final IUserServicePort userServicePort;

@PostMapping("/owner")
    public void createOwner(@RequestBody CreateUserRequest request) {
        User user = userRequestMapper.toDomain(request);
        userServicePort.createOwner(user);
    }

    @PostMapping("/admin")
    public void createAdmin(@RequestBody CreateUserRequest request) {
        User user = userRequestMapper.toDomain(request);
        userServicePort.createAdmin(user);
    }
    @PostMapping("/customer")
    public void createCustomer(@RequestBody CreateUserRequest request) {
        User user = userRequestMapper.toDomain(request);
        userServicePort.createCustomer(user);
    }
    @PostMapping("/employee")
    public void createEmployee(@RequestBody CreateUserRequest request) {
        User user = userRequestMapper.toDomain(request);
        userServicePort.createEmployee(user);
    }






}