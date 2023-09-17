package com.devertelo.springsecurity.service;

import com.devertelo.springsecurity.controller.UserDTO;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class UserService implements UserDetailsService {

    private List<UserDTO> users = new ArrayList<>();

    public UserService() {
        users.add(new UserDTO("joao", "joao", "senha"));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return users.stream().filter(user1 -> user1.getUsername().equals(username)).findFirst().orElseThrow();
    }
}
