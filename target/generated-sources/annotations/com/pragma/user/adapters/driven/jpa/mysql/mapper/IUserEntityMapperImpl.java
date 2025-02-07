package com.pragma.user.adapters.driven.jpa.mysql.mapper;

import com.pragma.user.adapters.driven.jpa.mysql.entity.UserEntity;
import com.pragma.user.adapters.driven.jpa.mysql.repository.IRoleRepository;
import com.pragma.user.domain.model.User;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-02-05T18:12:36-0500",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.14 (Amazon.com Inc.)"
)
@Component
public class IUserEntityMapperImpl implements IUserEntityMapper {

    @Autowired
    private IRoleEntityMapper iRoleEntityMapper;

    @Override
    public UserEntity toEntity(User user, IRoleRepository roleRepository) {
        if ( user == null ) {
            return null;
        }

        UserEntity userEntity = new UserEntity();

        userEntity.setRole( iRoleEntityMapper.toEntity( user.getRoleName(), roleRepository ) );
        userEntity.setId( user.getId() );
        userEntity.setFirstName( user.getFirstName() );
        userEntity.setLastName( user.getLastName() );
        userEntity.setIdentityDocument( user.getIdentityDocument() );
        userEntity.setPhoneNumber( user.getPhoneNumber() );
        userEntity.setBirthDate( user.getBirthDate() );
        userEntity.setEmail( user.getEmail() );

        return userEntity;
    }

    @Override
    public User toDomain(UserEntity userEntity) {
        if ( userEntity == null ) {
            return null;
        }

        User user = new User();

        user.setRoleName( iRoleEntityMapper.toModel( userEntity.getRole() ) );
        user.setId( userEntity.getId() );
        user.setFirstName( userEntity.getFirstName() );
        user.setLastName( userEntity.getLastName() );
        user.setIdentityDocument( userEntity.getIdentityDocument() );
        user.setPhoneNumber( userEntity.getPhoneNumber() );
        user.setBirthDate( userEntity.getBirthDate() );
        user.setEmail( userEntity.getEmail() );
        user.setPassword( userEntity.getPassword() );

        return user;
    }
}
