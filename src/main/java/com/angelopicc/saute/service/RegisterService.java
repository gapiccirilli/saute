package com.angelopicc.saute.service;

import com.angelopicc.saute.payload.RegisterDto;
import com.angelopicc.saute.payload.UserDto;

public interface RegisterService {
    
    UserDto register(RegisterDto dto);

}
