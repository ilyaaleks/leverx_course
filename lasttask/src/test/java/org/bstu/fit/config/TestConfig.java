package org.bstu.fit.config;

import org.bstu.fit.service.UserService;
import org.bstu.fit.validators.UserEmailValidator;
import org.bstu.fit.validators.UserValidatorTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import static org.mockito.Mockito.mock;
@Configuration
public class TestConfig {
    @Bean
    public UserEmailValidator userEmailValidator(){
        return new UserEmailValidator();
    }
    @Bean
    public UserService userService()
    {
        return mock(UserService.class);
    }
}
