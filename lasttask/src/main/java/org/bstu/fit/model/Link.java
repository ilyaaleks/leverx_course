package org.bstu.fit.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
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
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;
    private String name;
    private String url;
    @Enumerated(EnumType.ORDINAL)
    private Status status;
    @ManyToMany(mappedBy = "links",cascade = CascadeType.ALL, fetch=FetchType.EAGER)
    private Set<Tag> tags;
    @OneToMany(mappedBy = "link",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Set<Comment> comments;
    private long countOfLikes;
    private long countOfDislikes;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="user_id")
    private User user;
    @CreatedDate
    private Date dateOfCreation;
    @LastModifiedDate
    private Date dateOfChange;

}
