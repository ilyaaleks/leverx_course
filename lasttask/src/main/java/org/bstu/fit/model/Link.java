package org.bstu.fit.model;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Link {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private String url;
    @Enumerated(EnumType.ORDINAL)
    private Status status;
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(
            name = "tag_table",
            joinColumns = {@JoinColumn(name = "link_id")},
            inverseJoinColumns = {@JoinColumn(name = "tag_id")}
    )
    private Set<Tag> tags;
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToMany(mappedBy = "link", { CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH, CascadeType.REMOVE}, fetch = FetchType.EAGER, orphanRemoval = true)
    private Set<Comment> comments;
    private long countOfLikes;
    private long countOfDislikes;
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;
    @CreatedDate
    private Date dateOfCreation;
    @LastModifiedDate
    private Date dateOfChange;

}
