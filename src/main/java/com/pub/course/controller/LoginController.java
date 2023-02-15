package com.pub.course.controller;

import com.pub.course.dto.LoginDto;
import com.pub.course.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class LoginController {

    AuthService authService;

    @Autowired
    LoginController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public Object create(@RequestBody LoginDto dto) {
        try {
            return authService.authenticate(dto);
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }

}
