package org.bstu.fit.converter;

import org.bstu.fit.dto.TagDto;
import org.bstu.fit.model.Tag;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface TagMapper {
    TagMapper INSTANCE = Mappers.getMapper( TagMapper.class );
    TagDto toDTO(Tag tag);
    Tag fromDTO(TagDto tagDto);
}
