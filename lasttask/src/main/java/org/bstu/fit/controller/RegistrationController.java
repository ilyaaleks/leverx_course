package org.bstu.fit.controller;

import org.bstu.fit.converter.UserMapper;
import org.bstu.fit.dto.UserDto;
import org.bstu.fit.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("api/registration")
public class RegistrationController {
    public UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.OK)
    public void register(@RequestBody UserDto user)
    {
        UserMapper.INSTANCE.toDTO(userService.register(user));
    }
    @GetMapping("/activate/{code}")
    public void activate(@PathVariable String code, HttpServletResponse response) throws IOException {
       UserMapper.INSTANCE.toDTO(userService.activateUser(code));
       response.sendRedirect("http://localhost:4200/home");
    }

}
