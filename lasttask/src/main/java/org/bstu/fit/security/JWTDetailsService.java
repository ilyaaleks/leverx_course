package org.bstu.fit.security;

import org.bstu.fit.converter.UserMapper;
import org.bstu.fit.dto.UserDto;
import org.bstu.fit.model.User;
import org.bstu.fit.security.jwt.AuthUser;
import org.bstu.fit.security.jwt.JWTUserFactory;
import org.bstu.fit.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JWTDetailsService implements UserDetailsService {
    private UserService userService;

    public JWTDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       User user=userService.findByUsername(username);
       if(user==null)
       {
            throw new UsernameNotFoundException("Username not found");
       }
        AuthUser authUser= JWTUserFactory.create(user);
        return authUser;
    }
}
