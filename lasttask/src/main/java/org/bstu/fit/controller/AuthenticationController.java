package org.bstu.fit.controller;

import org.bstu.fit.dto.AuthToken;
import org.bstu.fit.dto.JwtToken;
import org.bstu.fit.dto.LoginPasswordUser;
import org.bstu.fit.dto.UserDto;
import org.bstu.fit.model.User;
import org.bstu.fit.security.TokenProvider;
import org.bstu.fit.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import javax.naming.AuthenticationException;
import javax.validation.Valid;

@RestController
@RequestMapping("api/auth")
public class AuthenticationController {
    private AuthenticationManager authenticationManager;

    private TokenProvider tokenProvider;

    public AuthenticationController(AuthenticationManager authenticationManager, TokenProvider tokenProvider) {
        this.authenticationManager = authenticationManager;
        this.tokenProvider = tokenProvider;
    }

    @PostMapping("/token")
    public ResponseEntity register(@Valid @RequestBody LoginPasswordUser loginUser) {
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginUser.getUsername(),
                        loginUser.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final String token = tokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new AuthToken(token));
    }

    @PostMapping("/logout")
    @ResponseStatus(value = HttpStatus.OK)
    public void logout() {
        SecurityContextHolder.getContext().setAuthentication(null);
    }
}
