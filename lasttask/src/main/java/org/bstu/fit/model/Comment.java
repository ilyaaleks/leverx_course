package org.bstu.fit.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Comment{
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="user_id")
    private User author;
    @NotBlank(message = "You dont fill data")
    private String text;
    private Date date;
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="link_id")
    private Link link;
}
