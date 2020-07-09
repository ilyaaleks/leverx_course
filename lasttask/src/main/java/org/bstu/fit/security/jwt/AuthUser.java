package org.bstu.fit.security.jwt;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;


@Getter
public class AuthUser  implements UserDetails {
    @NonNull
    private final long id;
    @NonNull
    private final String lastName;
    @NonNull
    private final String name;
    @NonNull
    private final String username;
    @NonNull
    private final String password;
    @NonNull
    private final String email;
    @NonNull
    private final String photoUrl;
    @NonNull
    private final boolean activate;
    @NonNull
    private final String activationCode;
    @NonNull
    private final Date lastPasswordResetDate;
    @NonNull
    private final Collection<? extends GrantedAuthority> authorities;
    @NonNull
    private final boolean enabled;

    @JsonIgnore
    public Long getId() {
        return id;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    public String getFirstName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    @JsonIgnore
    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @JsonIgnore
    public Date getLastPasswordResetDate() {
        return lastPasswordResetDate;
    }

    public AuthUser(@NonNull long id, @NonNull String lastName, @NonNull String name, @NonNull String username, @NonNull String password, @NonNull String email, @NonNull String photoUrl, @NonNull boolean activate, @NonNull String activationCode, @NonNull Date lastPasswordResetDate, @NonNull Collection<? extends GrantedAuthority> authorities, @NonNull boolean enabled) {
        this.id = id;
        this.lastName = lastName;
        this.name = name;
        this.username = username;
        this.password = password;
        this.email = email;
        this.photoUrl = photoUrl;
        this.activate = activate;
        this.activationCode = activationCode;
        this.lastPasswordResetDate = lastPasswordResetDate;
        this.authorities = authorities;
        this.enabled = enabled;
    }
}