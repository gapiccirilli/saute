package com.angelopicc.saute.service;

import com.angelopicc.saute.payload.LoginDto;
import com.angelopicc.saute.payload.UserDto;

public interface LoginService {
    
    UserDto login(LoginDto credentials);
}
