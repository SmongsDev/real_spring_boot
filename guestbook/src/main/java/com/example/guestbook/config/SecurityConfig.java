package com.example.guestbook.config;

import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@Log4j2
public class SecurityConfig{
  
  @Bean
  PasswordEncoder PasswordEncoder(){
    return new BCryptPasswordEncoder();
  }
}