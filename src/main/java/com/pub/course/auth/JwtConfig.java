package com.pub.course.auth;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

@Data
public class JwtConfig {
    @Value("${security.jwt.uri}")
    private String uri;

    @Value("${security.jwt.prefix}")
    private String prefix;

    @Value("${security.jwt.expiration}")
    private int expiration;

    @Value("${security.jwt.refresh.token.expiration}")
    private int refreshTokenExpiration;

    @Value("${security.jwt.secret}")
    private String secret;
}
