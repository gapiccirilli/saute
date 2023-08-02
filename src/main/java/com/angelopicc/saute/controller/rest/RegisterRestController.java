package com.angelopicc.saute.controller.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.angelopicc.saute.payload.RegisterDto;
import com.angelopicc.saute.payload.UserDto;
import com.angelopicc.saute.service.RegisterService;

@RestController
@RequestMapping("/api/users/register")
public class RegisterRestController {

    private RegisterService registerService;
    
    public RegisterRestController(RegisterService registerService) {
        this.registerService = registerService;
    }

    @PostMapping
    public ResponseEntity<UserDto> registerUser(@RequestBody RegisterDto newUser) {
        return new ResponseEntity<UserDto>(registerService.register(newUser), HttpStatus.OK);
    }
}
