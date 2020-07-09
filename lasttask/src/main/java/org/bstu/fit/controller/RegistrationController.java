package org.bstu.fit.controller;

import org.bstu.fit.converter.UserMapper;
import org.bstu.fit.dto.UserDto;
import org.bstu.fit.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/registration")
public class RegistrationController {
    public UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public UserDto register(@RequestBody UserDto user)
    {
        return UserMapper.INSTANCE.toDTO(userService.register(user));
    }
    @GetMapping("/activate/{code}")
    public UserDto activate(@PathVariable String code)
    {
       return UserMapper.INSTANCE.toDTO(userService.activateUser(code));
    }

}
