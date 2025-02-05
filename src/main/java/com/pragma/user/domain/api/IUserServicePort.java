package com.pragma.user.domain.api;



import com.pragma.usuario.usuario.adapters.driving.http.dto.user.response.AuthorizationResponse;
import com.pragma.usuario.usuario.adapters.driving.http.mapper.login.response.AuthResponse;
import com.pragma.usuario.usuario.domain.model.Login;
import com.pragma.usuario.usuario.domain.model.User;


public interface IUserServicePort {
    void createWareHouseAssistant(User user);
    AuthorizationResponse validateToken(String token);
    void createCustomer(User user);
    AuthResponse login(Login login);

}
