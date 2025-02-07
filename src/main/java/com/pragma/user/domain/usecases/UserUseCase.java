package com.pragma.user.domain.usecases;



import com.pragma.user.adapters.driven.jpa.mysql.exception.DuplicateDocumentException;
import com.pragma.user.adapters.driven.jpa.mysql.exception.DuplicateEmailException;
import com.pragma.user.adapters.driving.http.dto.user.response.AuthorizationResponse;
import com.pragma.user.adapters.driving.http.dto.user.response.OwnerResponse;
import com.pragma.user.adapters.driving.http.mapper.login.response.AuthResponse;
import com.pragma.user.domain.api.IUserServicePort;
import com.pragma.user.domain.exception.ValidationException;
import com.pragma.user.domain.model.Login;
import com.pragma.user.domain.model.Role;
import com.pragma.user.domain.model.RoleEnum;
import com.pragma.user.domain.model.User;
import com.pragma.user.domain.spi.IUserPersistencePort;
import com.pragma.user.domain.spi.JwtServicePort;
import com.pragma.user.domain.util.DomainConstants;
import lombok.AllArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class UserUseCase implements IUserServicePort {

    private final IUserPersistencePort userPersistencePort;
    private final JwtServicePort jwtServicePort;


    private void createUser(User user, String role) {
        List<String> errors = new ArrayList<>();

        if (user.getBirthDate().isAfter(LocalDate.now().minusYears(18))) {
            errors.add(DomainConstants.USER_MUST_BE_AT_LEAST_18_YEARS_OLD);
        }

        if (user.getPhoneNumber() != null && !user.getPhoneNumber().matches(DomainConstants.PHONE_NUMBER_REGEX)) {
            errors.add(DomainConstants.PHONE_NUMBER_IS_INVALID);
        }

        if (user.getEmail() != null && !user.getEmail().matches(DomainConstants.EMAIL_REGEX)) {
            errors.add(DomainConstants.EMAIL_IS_INVALID);
        }
        if (user.getIdentityDocument() != null && !user.getIdentityDocument().matches(DomainConstants.DOCUMENT_REGEX)) {
            errors.add(DomainConstants.DOCUMENT_IS_INVALID);
        }

        if (!errors.isEmpty()) {
            throw new ValidationException(errors);
        }

        Optional<Long> id = userPersistencePort.findIdByUsername(user.getEmail());
        if (id.isPresent()) {
            throw new DuplicateEmailException();
        }
        Optional<Long> idIdentification = userPersistencePort.findIdByIdentification(user.getIdentityDocument());
        if (idIdentification.isPresent()) {
            throw new DuplicateDocumentException();
        }
        user.setRoleName(role);
        userPersistencePort.saveUser(user);

    }

    @Override
    public void createOwner(User user) {
        createUser(user, RoleEnum.ROLE_OWNER.toString());

    }

    @Override
    public void createCustomer(User user) {
        createUser(user, RoleEnum.ROLE_CUSTOMER.toString());

    }

    @Override
    public void createEmployee(User user) {
        createUser(user, RoleEnum.ROLE_EMPLOYEE.toString());
    }

    @Override
    public void createAdmin(User user) {
        createUser(user, RoleEnum.ROLE_ADMIN.toString());


    }


    @Override
    public OwnerResponse validateOwner(Long id) {
      boolean isOwner = userPersistencePort.verifyRoleById(id, RoleEnum.ROLE_OWNER.toString());
         return new OwnerResponse(isOwner);
    }

    @Override
    public AuthResponse login(Login login) {
    return userPersistencePort.login(login);

    }

    @Override
    public AuthorizationResponse validateToken(String token) {
        String email = jwtServicePort.getUsernameFromToken(token);
        Optional<Long> id = userPersistencePort.findIdByUsername(email);
        String role = jwtServicePort.getAuthoritiesFromToken(token);

        return id.map(aLong -> new AuthorizationResponse(true, aLong, role)).orElseGet(() -> new AuthorizationResponse(false, null, null));

    }





}