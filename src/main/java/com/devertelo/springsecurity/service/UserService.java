package com.devertelo.springsecurity.service;

import com.devertelo.springsecurity.controller.user.UserDTO;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements UserDetailsService {

    private List<UserDTO> users = new ArrayList<>();

    public UserService() {
        users.add(new UserDTO("joao", "joao", "$2a$10$PIsCJpHH6ukAIyv1AFemu.VOew5AVYzrX6KJLdfjwwJcAWJHJN/m."));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return users.stream()
                .filter(user1 -> user1.getUsername().equals(username))
                .findFirst().orElseThrow();
    }
}
