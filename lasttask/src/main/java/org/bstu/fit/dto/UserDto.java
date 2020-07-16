package org.bstu.fit.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private long id;
    @NotNull(message = "Lastname of user can't be null")
    private String lastName;
    @NotNull(message = "Name of user can't be null")
    private String name;
    @NotNull(message = "Username can't be null")
    private String username;
    @NotNull(message = "Email can't be null")
    @Email
    private String email;
    private String photoUrl;
    private boolean activate;
    private String activationCode;
    @NotNull(message = "Password can't be null")
    @Size(min = 8,max = 16,message = "Password must be equal or greater than 8 characters and less than 16 characters")
    private String password;
}
