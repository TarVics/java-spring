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

        String jwtToken = jwtService.generateToken(appUser);

        ///+++++++++++++ JWT PAIR
        String refreshToken = jwtService.generateRefreshToken(appUser);
        appUser.setRefreshToken(refreshToken);
        ///+++++++++++++ JWT PAIR

        appUserDAO.save(appUser);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                ///+++++++++++++ JWT PAIR
                .refreshToken(refreshToken)
                ///+++++++++++++ JWT PAIR
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

        String token = jwtService.generateToken(appUser);
        ///+++++++++++++ JWT PAIR
        String refreshToken = jwtService.generateRefreshToken(appUser);
        appUser.setRefreshToken(refreshToken);
        appUserDAO.save(appUser);
        ///+++++++++++++ JWT PAIR

        return AuthenticationResponse.builder()
                .token(token)
                ///+++++++++++++ JWT PAIR
                .refreshToken(refreshToken)
                ///+++++++++++++ JWT PAIR
                .build();
    }
    ///+++++++++++++ JWT PAIR
    public AuthenticationResponse refresh(RefreshRequest refreshRequest) {
        String token = refreshRequest.getRefreshToken();
        String username = jwtService.extractUsername(token);
        AppUser appUser = appUserDAO.findAppUserByEmail(username).orElseThrow();

        String newAccessToken = null;
        String newRefreshToken = null;

        if (appUser.getRefreshToken().equals(token)) {
            newAccessToken = jwtService.generateToken(appUser);
            newRefreshToken = jwtService.generateRefreshToken(appUser);
            appUser.setRefreshToken(newRefreshToken);
            appUserDAO.save(appUser);
        }

        return AuthenticationResponse.builder()
                .token(newAccessToken)
                .refreshToken(newRefreshToken)
                .build();
    }
    ///+++++++++++++ JWT PAIR
}
