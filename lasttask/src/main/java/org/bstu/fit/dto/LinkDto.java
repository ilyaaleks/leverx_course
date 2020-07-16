package org.bstu.fit.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bstu.fit.model.Status;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Set;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LinkDto {
    private long id;
    @NotNull(message = "Name can't be null")
    private String name;
    @NotNull(message = "Url can't be null")
    private String url;
    private Status status;
    private Set<TagDto> tags;
    private long countOfLikes;
    private long countOfDislikes;
    private UserDto user;
    private Date dateOfCreation;
    private Date dateOfChange;
}
