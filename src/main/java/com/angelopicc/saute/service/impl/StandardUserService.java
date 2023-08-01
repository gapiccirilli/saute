package com.angelopicc.saute.service.impl;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.angelopicc.saute.entity.User;
import com.angelopicc.saute.entity.roles.Role;
import com.angelopicc.saute.exception.UserNotFoundException;
import com.angelopicc.saute.payload.CreateUserDto;
import com.angelopicc.saute.payload.UserDto;
import com.angelopicc.saute.repository.UserRepository;
import com.angelopicc.saute.service.UserService;
import static com.angelopicc.saute.utility.message.StatusMessage.DELETE_SUCCESSFUL;

public class StandardUserService implements UserService {

    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder;

    public StandardUserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDto createUser(CreateUserDto user) {
        User newUser = mapToEntity(user);
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
        newUser.setRole(Role.USER);
        newUser.setCreateTime(LocalDateTime.now());

        return mapToDto(userRepository.save(newUser));
    }

    @Override
    public UserDto getUserByEmail(String email) {
        Optional<User> optUser = userRepository.findByEmail(email);

        if (optUser.isEmpty()) {
            throw new UserNotFoundException("User with email: '" + email + "', cannot be found");
        }

        return mapToDto(optUser.get());
    }

    @Override
    public UserDto updateUser(UserDto newUserInfo, long userId) {
        Optional<User> optUser = userRepository.findById(userId);

        if (optUser.isEmpty()) {
            throw new UserNotFoundException("User cannot be found");
        }

        User user = optUser.get();
        user.setFirstName(newUserInfo.getFirstName());
        user.setLastName(newUserInfo.getLastName());
        user.setGender(newUserInfo.getGender());
        user.setPhoneNumber(newUserInfo.getPhoneNumber());
        // create separate services for updating password, email and profile pic

        return mapToDto(userRepository.save(user));
    }

    public String deleteUser(long userId) {
        Optional<User> optUser = userRepository.findById(userId);

        if (optUser.isEmpty()) {
            throw new UserNotFoundException("User cannot be found");
        }

        userRepository.deleteById(userId);

        return DELETE_SUCCESSFUL;
    }

    // ---------------------------------------------------------------------------------------------------|

    // ---------------------------------------------------------------------------------------------------|

    private UserDto mapToDto(User entity) {
        return new UserDto(entity.getId(), entity.getFirstName(), entity.getLastName(), entity.getEmail(), 
        entity.getPhoneNumber(), entity.getGender(), entity.getProfilePicture());
    }

    private User mapToEntity(CreateUserDto dto) {

        User user = new User();
        user.setId(dto.getId());
        user.setEmail(dto.getEmail());
        // user.setPassword(dto.getPassword());
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setPhoneNumber(dto.getPhoneNumber());
        user.setGender(dto.getGender());

        return user;
    }

    private User mapToEntity(UserDto dto) {

        User user = new User();
        user.setId(dto.getId());
        user.setEmail(dto.getEmail());
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setPhoneNumber(dto.getPhoneNumber());
        user.setGender(dto.getGender());

        return user;
    }
}
