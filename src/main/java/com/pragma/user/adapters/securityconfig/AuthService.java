package com.pragma.user.adapters.securityconfig;



import com.pragma.user.adapters.driven.jpa.mysql.repository.IUserRepository;
import com.pragma.user.adapters.driving.http.dto.user.request.LoginRequest;
import com.pragma.user.domain.model.AuthResponse;
import com.pragma.user.adapters.securityconfig.jwtconfiguration.JwtService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Data
@Builder
@AllArgsConstructor
public class AuthService {
    private final IUserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsServiceImp;

    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        UserDetails user= userDetailsServiceImp.loadUserByUsername(request.getEmail());
        String token=jwtService.getToken(user);
        return AuthResponse.builder()
                .token(token)
                .build();

    }
}
