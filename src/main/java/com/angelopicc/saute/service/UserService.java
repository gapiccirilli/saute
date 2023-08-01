package com.angelopicc.saute.service;

import com.angelopicc.saute.payload.CreateUserDto;
import com.angelopicc.saute.payload.UserDto;

public interface UserService {
    
    UserDto createUser(CreateUserDto user);

    UserDto getUserByEmail(String email);

    UserDto updateUser(UserDto newUserInfo, long userId);
}
