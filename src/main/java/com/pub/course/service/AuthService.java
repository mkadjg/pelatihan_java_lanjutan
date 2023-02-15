package com.pub.course.service;

import com.pub.course.dto.LoginDto;
import org.springframework.security.core.Authentication;

public interface AuthService {
    String authenticate(LoginDto dto);

}
