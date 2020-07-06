package org.bstu.fit.converter;

import org.bstu.fit.dto.CommentDto;
import org.bstu.fit.model.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CommentMapper {
    CommentMapper INSTANCE = Mappers.getMapper( CommentMapper.class );
    CommentDto toDTO(Comment comment);
    Comment fromDTO(CommentDto commentDto);
}
