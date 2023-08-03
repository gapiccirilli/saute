package com.angelopicc.saute.service.impl;

import org.apache.catalina.connector.Response;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.angelopicc.saute.entity.User;
import com.angelopicc.saute.exception.UserNotFoundException;
import com.angelopicc.saute.payload.LoginDto;
import com.angelopicc.saute.payload.UserDto;
import com.angelopicc.saute.repository.UserRepository;
import com.angelopicc.saute.security.JwtTokenProvider;
import com.angelopicc.saute.service.LoginService;

@Service
public class StandardLoginService implements LoginService {

    private AuthenticationManager authenticationManager;
    private UserRepository userRepository;
    private JwtTokenProvider jwtTokenProvider;

    public StandardLoginService(AuthenticationManager authenticationManager, UserRepository userRepository,
            JwtTokenProvider jwtTokenProvider) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public ResponseEntity<UserDto> login(LoginDto credentials) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
            credentials.getEmail(), 
            credentials.getPassword())
            );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        User user = userRepository.findByEmail(credentials.getEmail())
        .orElseThrow(() -> new UserNotFoundException(credentials.getEmail() + " can't be found"));

        return new ResponseEntity<>(mapToDto(user), getJwtHeader(authentication), HttpStatus.OK);
    }

    private HttpHeaders getJwtHeader(Authentication authentication) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", jwtTokenProvider.generateToken(authentication));
        return headers;
    }


    private UserDto mapToDto(User user) {
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setEmail(user.getEmail());
        dto.setPhoneNumber(user.getPhoneNumber());

        return dto;
    }
    
}
