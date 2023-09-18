package com.devertelo.springsecurity.controller.user;

import com.devertelo.springsecurity.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public List<UserDTO> getUser() {

        return userService.getUsers();
    }

    @PreAuthorize("hasRole('ADMIN') || hasRole('USER')")
    @GetMapping("/username")
    public UserDTO getUserByUsername() {

        UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return (UserDTO) principal;
    }
}
