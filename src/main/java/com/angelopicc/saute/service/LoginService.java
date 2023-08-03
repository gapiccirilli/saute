package com.angelopicc.saute.service;

import org.springframework.http.ResponseEntity;

import com.angelopicc.saute.payload.LoginDto;
import com.angelopicc.saute.payload.UserDto;

public interface LoginService {
    
    ResponseEntity<UserDto> login(LoginDto credentials);
}
