package org.bstu.fit.config;

import org.bstu.fit.security.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class EncoderConfig {
    @Bean
    public BCryptPasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public JwtAuthenticationFilter authenticationTokenFilterBean(){
        return new JwtAuthenticationFilter();
    }
}
