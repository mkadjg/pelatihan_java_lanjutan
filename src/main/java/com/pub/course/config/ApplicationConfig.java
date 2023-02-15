package com.pub.course.config;
import com.pub.course.auth.JwtConfig;
import com.pub.course.payload.DaftarSiswa;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

    @Bean
    public DaftarSiswa daftarSiswa() {
        return new DaftarSiswa();
    }

    @Bean
    public JwtConfig jwtConfig() {
        return new JwtConfig();
    }


}
