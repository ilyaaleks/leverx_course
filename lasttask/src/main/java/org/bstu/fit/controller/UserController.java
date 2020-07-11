package org.bstu.fit.controller;

import com.sun.mail.iap.Response;
import org.bstu.fit.converter.UserMapper;
import org.bstu.fit.dto.AuthToken;
import org.bstu.fit.dto.UserDto;
import org.bstu.fit.model.User;
import org.bstu.fit.security.TokenProvider;
import org.bstu.fit.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/user")
public class UserController {
    private UserService userService;
    private AuthenticationManager authenticationManager;
    private TokenProvider tokenProvider;

    public UserController(UserService userService, AuthenticationManager authenticationManager, TokenProvider tokenProvider) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.tokenProvider = tokenProvider;
    }

    @PutMapping
    public ResponseEntity<AuthToken> updateUser(@RequestBody UserDto userDto)
    {
        User user=userService.updateUser(userDto);
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        user.getUsername(),
                        userDto.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final String token = tokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new AuthToken(token));
    }
    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUser(@PathVariable long userId)
    {
        User user=userService.findById(userId);
        return ResponseEntity.ok(UserMapper.INSTANCE.toDTO(user));
    }
}
