package com.pragma.user.domain.api;


import com.pragma.user.adapters.driving.http.dto.user.response.AuthorizationResponse;
import com.pragma.user.adapters.driving.http.dto.user.response.OwnerResponse;
import com.pragma.user.domain.model.AuthResponse;
import com.pragma.user.domain.model.Login;
import com.pragma.user.domain.model.User;

public interface IUserServicePort {
    void createAdmin(User user);
    OwnerResponse validateOwner(Long id);
    void createOwner(User user);
    void createCustomer(User user);
    void createEmployee(User user, long restaurantId);
    AuthResponse login(Login login);
    AuthorizationResponse validateToken(String token);
    String getPhoneNumber(Long clientId);

}
