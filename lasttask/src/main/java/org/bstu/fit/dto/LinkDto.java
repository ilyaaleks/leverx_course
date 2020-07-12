package org.bstu.fit.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bstu.fit.model.Status;

import java.util.Date;
import java.util.Set;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LinkDto {
    private long id;
    private String name;
    private String url;
    private Status status;
    private Set<TagDto> tags;
    private long countOfLikes;
    private long countOfDislikes;
    private UserDto user;
    private Date dateOfCreation;
    private Date dateOfChange;
}
