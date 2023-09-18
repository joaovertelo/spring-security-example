package com.devertelo.springsecurity.service;

import com.devertelo.springsecurity.controller.auth.RegisterRequest;
import com.devertelo.springsecurity.controller.user.UserDTO;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private List<UserDTO> users = new ArrayList<>();

    public UserService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
        users.add(new UserDTO("joao", passwordEncoder.encode("senha"),
                List.of(Role.ADMIN)));
    }

    public List<UserDTO> getUsers() {
        return users;
    }

    public UserDetails register(RegisterRequest registerRequest) {
        UserDTO userDetails = new UserDTO(registerRequest.username(), passwordEncoder.encode(registerRequest.password()), registerRequest.roles());
        users.add(userDetails);
        return userDetails;
    }
}
