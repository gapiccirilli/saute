package com.angelopicc.saute.service.impl;

import java.time.LocalDateTime;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.angelopicc.saute.entity.User;
import com.angelopicc.saute.entity.roles.Role;
import com.angelopicc.saute.exception.UserExistsException;
import com.angelopicc.saute.payload.RegisterDto;
import com.angelopicc.saute.payload.UserDto;
import com.angelopicc.saute.repository.UserRepository;
import com.angelopicc.saute.service.RegisterService;

@Service
public class StandardRegisterService implements RegisterService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public StandardRegisterService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDto register(RegisterDto dto) {

        if (userRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new UserExistsException("There is already a user with the email: '" + dto.getEmail() + "'");
        }

        User user = mapToUser(dto);

        User savedUser = userRepository.save(user);

        return mapToDto(savedUser);
    }

    private User mapToUser(RegisterDto dto) {
        User user = new User();
        user.setCreateTime(LocalDateTime.now());
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setEmail(dto.getEmail());
        user.setPhoneNumber(dto.getPhoneNumber());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setRole(Role.USER);

        return user;
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
