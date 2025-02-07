package com.pragma.user.adapters.driving.http.mapper.user;




import com.pragma.user.adapters.driving.http.dto.user.request.CreateUserRequest;
import com.pragma.user.domain.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface IUserRequestMapper {
@Mapping(target = "roleName", ignore = true)
User toDomain(CreateUserRequest request);

}