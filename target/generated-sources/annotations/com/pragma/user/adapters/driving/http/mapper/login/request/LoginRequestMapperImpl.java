package com.pragma.user.adapters.driving.http.mapper.login.request;

import com.pragma.user.adapters.driving.http.dto.user.request.LoginRequest;
import com.pragma.user.domain.model.Login;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-02-05T18:12:36-0500",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.14 (Amazon.com Inc.)"
)
@Component
public class LoginRequestMapperImpl implements LoginRequestMapper {

    @Override
    public Login toDomain(LoginRequest request) {
        if ( request == null ) {
            return null;
        }

        Login login = new Login();

        login.setEmail( request.getEmail() );
        login.setPassword( request.getPassword() );

        return login;
    }
}
