package org.bstu.fit.security.jwt;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.bstu.fit.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Data
public final class JWTUserFactory {
    public static AuthUser create(User user)
    {
        return new AuthUser(user.getId(),
                user.getLastName(),
                user.getName(),
                user.getUsername(),
                user.getPassword(),
                user.getEmail(),
                user.getPhotoUrl(),
                user.getActivationCode()==null,
                user.getActivationCode(),
                user.getLastPasswordResetDate(),
                getRole(user),
                true
                );
    }
    private static List<GrantedAuthority> getRole(User user)
    {
        List<GrantedAuthority> authorities=new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_"+user.getRole()));
        return authorities;
    }
}
