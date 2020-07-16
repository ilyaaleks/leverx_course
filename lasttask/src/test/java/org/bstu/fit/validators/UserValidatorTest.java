package org.bstu.fit.validators;

import org.bstu.fit.config.TestConfig;
import org.bstu.fit.model.User;
import org.bstu.fit.service.UserService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.validation.Errors;

import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestConfig.class,loader = AnnotationConfigContextLoader.class)
public class UserValidatorTest {
    @Autowired
    public UserEmailValidator userEmailValidator;
    @Autowired
    public UserService userService;
    private static final String userEmail="abc@gmail.com";
    private static final User user=mock(User.class);
    @BeforeAll
    public static void setup()
    {
        when(user.getEmail()).thenReturn(userEmail);
    }
    @Test
    public void validateShouldAcceptUserWithNewEmail()
    {
        when(userService.findByEmail(userEmail)).thenReturn(null);
        Errors errors=mock(Errors.class);
        userEmailValidator.validate(user,errors);
        verify(errors,never()).rejectValue(eq("email"),any(),any());

    }
    @Test
    public void validateShouldRejectUserWithAlreadyUserEmail()
    {
        when(userService.findByEmail(userEmail)).thenReturn(user);
        Errors errors=mock(Errors.class);
        userEmailValidator.validate(user,errors);
        verify(errors,times(1)).rejectValue(eq("email"),any(),any());

    }
}
