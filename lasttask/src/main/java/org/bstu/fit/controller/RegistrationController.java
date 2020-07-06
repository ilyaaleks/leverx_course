package org.bstu.fit.controller;

import org.bstu.fit.dto.UserDto;
import org.bstu.fit.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/registration")
public class RegistrationController {
    public UserService userService;
    @PostMapping
    public UserDto register(UserDto user)
    {
        return userService.register(user);
    }
    @GetMapping("/activate/{code}")
    public UserDto activate(@PathVariable String code)
    {
       return userService.activateUser(code);

    }

}
