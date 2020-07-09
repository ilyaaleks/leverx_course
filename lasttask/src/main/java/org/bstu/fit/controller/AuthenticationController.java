package org.bstu.fit.controller;

import org.bstu.fit.dto.JwtToken;
import org.bstu.fit.dto.LoginPasswordUser;
import org.bstu.fit.dto.UserDto;
import org.bstu.fit.model.User;
import org.bstu.fit.security.jwt.JwtTokenProvider;
import org.bstu.fit.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.AuthenticationException;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;

    public AuthenticationController(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
    }
    @PostMapping
    public ResponseEntity login(@RequestBody LoginPasswordUser userLoginPassword)
    {
        try {
                String username=userLoginPassword.getUsername();
                String password=userLoginPassword.getPassword();
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,password));
            User user=userService.findByUsername(username);
            if(user==null)
            {
                throw new UsernameNotFoundException("Username not found");
            }
            String token=jwtTokenProvider.createToken(username);
            JwtToken jwtToken=new JwtToken(token);
            return ResponseEntity.ok(jwtToken);

        }
        catch (Exception e)
        {
            throw new BadCredentialsException("Invalid username or password;");
        }
    }
}
