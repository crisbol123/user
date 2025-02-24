package com.pragma.user.adapters.driving.http.mapper.user;

import com.pragma.user.adapters.driving.http.dto.user.request.CreateUserRequest;
import com.pragma.user.domain.model.User;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-02-08T18:32:06-0500",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.14 (Amazon.com Inc.)"
)
@Component
public class IUserRequestMapperImpl implements IUserRequestMapper {

    @Override
    public User toDomain(CreateUserRequest request) {
        if ( request == null ) {
            return null;
        }

        User user = new User();

        user.setFirstName( request.getFirstName() );
        user.setLastName( request.getLastName() );
        user.setIdentityDocument( request.getIdentityDocument() );
        user.setPhoneNumber( request.getPhoneNumber() );
        user.setBirthDate( request.getBirthDate() );
        user.setEmail( request.getEmail() );
        user.setPassword( request.getPassword() );

        return user;
    }
}
