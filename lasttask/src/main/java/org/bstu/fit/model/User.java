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
    private String lastName;
    private String name;
    private String username;
    private String password;
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
