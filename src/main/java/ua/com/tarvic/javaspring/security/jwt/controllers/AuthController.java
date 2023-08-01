package ua.com.tarvic.javaspring.security.jwt.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.com.tarvic.javaspring.security.jwt.models.AuthenticationRequest;
import ua.com.tarvic.javaspring.security.jwt.models.AuthenticationResponse;
import ua.com.tarvic.javaspring.security.jwt.models.RefreshRequest;
import ua.com.tarvic.javaspring.security.jwt.models.RegisterRequest;
import ua.com.tarvic.javaspring.security.jwt.services.AuthenticationService;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {
    private AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest registerRequest) {
        return ResponseEntity.ok(authenticationService.register(registerRequest));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest authenticationRequest) {
        return ResponseEntity.ok(authenticationService.authenticate(authenticationRequest));
    }
    ///+++++++++++++ JWT PAIR
    @PostMapping("/refresh")
    public ResponseEntity<AuthenticationResponse> refresh(@RequestBody RefreshRequest refreshRequest) {
        return ResponseEntity.ok(authenticationService.refresh(refreshRequest));
    }
    ///+++++++++++++ JWT PAIR
}
