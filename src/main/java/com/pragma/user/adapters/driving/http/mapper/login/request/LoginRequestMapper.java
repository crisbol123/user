package com.pragma.user.adapters.driving.http.mapper.login.request;



import com.pragma.user.adapters.driving.http.dto.user.request.LoginRequest;
import com.pragma.user.domain.model.Login;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LoginRequestMapper {
    Login toDomain(LoginRequest request);
}
