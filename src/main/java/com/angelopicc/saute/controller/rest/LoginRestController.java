package com.angelopicc.saute.controller.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.angelopicc.saute.payload.LoginDto;
import com.angelopicc.saute.payload.UserDto;
import com.angelopicc.saute.service.LoginService;

@RestController
@RequestMapping("/api/users/login")
public class LoginRestController {
    
    private LoginService loginService;

    public LoginRestController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping
    public ResponseEntity<UserDto> login(@RequestBody LoginDto credentials) {
        return loginService.login(credentials);
    }
}
