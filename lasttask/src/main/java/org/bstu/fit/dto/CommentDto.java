package org.bstu.fit.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {
    private long id;
    @NotNull(message = "Author can't be null")
    private UserDto author;
    @NotNull(message = "Text of comment can't be null")
    private String text;
    private Date date;
    @NotNull(message = "link can't be null")
    private LinkDto link;
}
