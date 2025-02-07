package com.pragma.user.adapters.driven.jpa.mysql.adapter;



import com.pragma.user.adapters.driven.jpa.mysql.entity.RoleEntity;
import com.pragma.user.adapters.driven.jpa.mysql.entity.UserEntity;
import com.pragma.user.adapters.driven.jpa.mysql.mapper.IUserEntityMapper;
import com.pragma.user.adapters.driven.jpa.mysql.repository.IRoleRepository;
import com.pragma.user.adapters.driven.jpa.mysql.repository.IUserRepository;
import com.pragma.user.adapters.driving.http.mapper.login.response.AuthResponse;
import com.pragma.user.domain.model.Login;
import com.pragma.user.domain.model.User;
import com.pragma.user.domain.spi.IUserPersistencePort;
import com.pragma.user.domain.spi.JwtServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserAdapter implements IUserPersistencePort {

    private final IUserRepository userRepository;
    private final IUserEntityMapper userEntityMapper;
    private final PasswordEncoder passwordEncoder;
    private final IRoleRepository iRoleRepository;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsServiceImp;
    private final JwtServicePort jwtServicePort;

    @Override
    public void saveUser(User user) {
        UserEntity userEntity = userEntityMapper.toEntity(user, iRoleRepository);
        userEntity.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(userEntity);
    }

    @Override
    public AuthResponse login(Login login) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login.getEmail(), login.getPassword()));
        UserDetails user= userDetailsServiceImp.loadUserByUsername(login.getEmail());
        String token=jwtServicePort.getToken(user);
        return AuthResponse.builder()
                .token(token)
                .build();
    }



    @Override
    public Optional<Long> findIdByUsername(String username) {

return userRepository.findIdByEmail(username);
    }
    @Override
    public Optional<Long> findIdByIdentification(String identification) {
        return userRepository.findIdByIdentityDocument(identification);
    }

    @Override
    public Boolean verifyRoleById(Long id, String role) {
        return userRepository.existsByIdAndRoleName(id, role);
    }
}
