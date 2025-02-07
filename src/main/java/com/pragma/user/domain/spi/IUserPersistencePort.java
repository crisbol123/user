package com.pragma.user.domain.spi;



import com.pragma.user.adapters.driving.http.mapper.login.response.AuthResponse;
import com.pragma.user.domain.model.Login;
import com.pragma.user.domain.model.User;

import java.util.Optional;

public interface IUserPersistencePort {
    void saveUser(User user);
    Optional<Long> findIdByUsername(String username);
    AuthResponse login(Login login);
    Optional<Long> findIdByIdentification(String identification);
    Boolean verifyRoleById(Long id, String role);
}
