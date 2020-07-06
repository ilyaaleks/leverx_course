package org.bstu.fit.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {
    private long id;
    private UserDto author;
    private String text;
    private Date date;
    private LinkDto link;
}
