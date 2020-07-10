package org.bstu.fit.controller;

import org.bstu.fit.converter.UserMapper;
import org.bstu.fit.dto.UserDto;
import org.bstu.fit.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/registration")
public class RegistrationController {
    public UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserDto> register(@RequestBody UserDto user)
    {
        return ResponseEntity.ok(UserMapper.INSTANCE.toDTO(userService.register(user)));
    }
    @GetMapping("/activate/{code}")
    public ResponseEntity<UserDto> activate(@PathVariable String code)
    {
       return ResponseEntity.ok(UserMapper.INSTANCE.toDTO(userService.activateUser(code)));
    }

}
