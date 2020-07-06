package org.bstu.fit.converter;

import javax.annotation.Generated;
import org.bstu.fit.dto.CommentDto;
import org.bstu.fit.model.Comment;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-07-06T14:16:02+0300",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 11.0.7 (Oracle Corporation)"
)
public class CommentMapperImpl implements CommentMapper {

    @Override
    public CommentDto toDTO(Comment comment) {
        if ( comment == null ) {
            return null;
        }

        CommentDto commentDto = new CommentDto();

        return commentDto;
    }

    @Override
    public Comment fromDTO(CommentDto commentDto) {
        if ( commentDto == null ) {
            return null;
        }

        Comment comment = new Comment();

        return comment;
    }
}
