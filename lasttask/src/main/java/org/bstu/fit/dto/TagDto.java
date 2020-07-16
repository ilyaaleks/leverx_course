package org.bstu.fit.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.Set;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TagDto {
    private long id;
    @NotNull(message = "Name of tag can't be null")
    private String name;
}
