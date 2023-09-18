package com.devertelo.springsecurity.controller.auth;

import com.devertelo.springsecurity.security.jwt.JwtService;
import com.devertelo.springsecurity.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
@AllArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final UserService userService;
    private final JwtService jwtService;

    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(@RequestBody LoginRequest loginRequest) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.username(), loginRequest.password())
            );
            UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.username());
            String token = jwtService.generateToken(userDetails);
            return ResponseEntity.ok(new TokenResponse(token));

        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PostMapping("/register")
    public ResponseEntity<TokenResponse> register(@RequestBody RegisterRequest registerRequest) {

        UserDetails userDetails = userService.register(registerRequest);
        String token = jwtService.generateToken(userDetails);
        return ResponseEntity.ok(new TokenResponse(token));
    }


}
