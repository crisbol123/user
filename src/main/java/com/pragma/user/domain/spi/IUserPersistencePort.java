package com.pragma.user.domain.spi;



import com.pragma.user.domain.model.AuthResponse;
import com.pragma.user.domain.model.Login;
import com.pragma.user.domain.model.User;

import java.util.Optional;

public interface IUserPersistencePort {
    void saveUser(User user);
    Long saveEmployee(User user, Long restaurantId);
    Optional<Long> findIdByUsername(String username);
    AuthResponse login(Login login);
    Optional<Long> findIdByIdentification(String identification);
    Boolean verifyRoleById(Long id, String role);
    String getPhoneNumber(Long clientId);
}
