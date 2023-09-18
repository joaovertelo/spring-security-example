package com.devertelo.springsecurity.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
@AllArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(LoginRequest loginRequest) {

        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.username(), loginRequest.password()));

        if (authenticate.isAuthenticated()) {

            String token = "";
            return ResponseEntity.ok(new TokenResponse(token));
        }
        return ResponseEntity.badRequest().build();
    }

}
