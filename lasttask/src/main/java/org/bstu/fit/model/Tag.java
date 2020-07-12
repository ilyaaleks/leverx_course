package org.bstu.fit.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Tag {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;
    @NotBlank(message = "You need to fill name")
    private String name;
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToMany(mappedBy = "tags", cascade  = { CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH, CascadeType.REMOVE}, fetch = FetchType.EAGER)
    private Set<Link> links;
}
