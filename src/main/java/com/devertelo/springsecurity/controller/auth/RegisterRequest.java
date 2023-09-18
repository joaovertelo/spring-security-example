package com.devertelo.springsecurity.controller.auth;

import com.devertelo.springsecurity.service.Role;

import java.util.List;

public record RegisterRequest(String username, String password, List<Role> roles) {
}
