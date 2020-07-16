package org.bstu.fit.validators;

import org.bstu.fit.model.User;
import org.bstu.fit.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
@Component
public class UserEmailValidator implements Validator {
    @Autowired
    private UserService userService;


    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        User user=(User) o;
        if(userService.findByEmail(user.getEmail())!=null)
        {
            errors.rejectValue("email","","This email is already in use");
        }
    }
}
