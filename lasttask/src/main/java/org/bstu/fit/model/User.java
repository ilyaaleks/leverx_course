package org.bstu.fit.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;
    @NotBlank(message = "You need to fill lastName")
    private String lastName;
    @NotBlank(message = "You need to fill name")
    private String name;
    @NotBlank(message = "You need to fill username")
    private String username;
    @NotBlank(message = "You need to fill password")
    private String password;
    @NotBlank(message = "You need to fill email")
    @Email(message = "Email is't correct")
    private String email;
    private String photoUrl;
    private Date lastPasswordResetDate;
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToMany(mappedBy = "user",cascade = { CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH, CascadeType.REMOVE},fetch = FetchType.EAGER, orphanRemoval = true)
    private Set<Link> links;
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToMany(mappedBy = "author",cascade ={ CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH, CascadeType.REMOVE},fetch = FetchType.EAGER, orphanRemoval = true)
    private Set<Comment> comments;
    private boolean activate;
    private String activationCode;
    private String role;
}
