package org.bstu.fit.controller;

import org.bstu.fit.converter.UserMapper;
import org.bstu.fit.dto.UserDto;
import org.bstu.fit.model.User;
import org.bstu.fit.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/user")
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PutMapping
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto)
    {
        User user=userService.updateUser(userDto);
        userDto= UserMapper.INSTANCE.toDTO(user);
        return ResponseEntity.ok(userDto);
    }
}
