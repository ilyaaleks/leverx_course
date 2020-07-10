package org.bstu.fit.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private long id;
    private String lastName;
    private String name;
    private String username;
    private String email;
    private String photoUrl;
    private Set<LinkDto> links;
    private Set<CommentDto> comments;
    private boolean activate;
    private String activationCode;
    private String password;
}
