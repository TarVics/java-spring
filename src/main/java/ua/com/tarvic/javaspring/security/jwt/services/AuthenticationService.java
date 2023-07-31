package ua.com.tarvic.javaspring.security.jwt.services;

import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ua.com.tarvic.javaspring.security.jwt.dao.AppUserDAO;
import ua.com.tarvic.javaspring.security.jwt.models.*;

@Service
@AllArgsConstructor
public class AuthenticationService {
    private PasswordEncoder passwordEncoder;
    private AppUserDAO appUserDAO;
    private JwtService jwtService;
    private AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest registerRequest) {
        AppUser appUser = AppUser.builder()
                .firstname(registerRequest.getFirstname())
                .lastname(registerRequest.getLastname())
                .email(registerRequest.getEmail())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .role(Role.USER)
                .build();

        appUserDAO.save(appUser);

        String jwtToken = jwtService.generateToken(appUser);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                authenticationRequest.getEmail(),
                authenticationRequest.getPassword()
        ));

        AppUser appUser = appUserDAO
                .findAppUserByEmail(authenticationRequest.getEmail())
                .orElseThrow();

        String jwtToken = jwtService.generateToken(appUser);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
