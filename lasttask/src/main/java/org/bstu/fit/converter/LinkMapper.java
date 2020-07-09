package org.bstu.fit.converter;

import org.bstu.fit.dto.LinkDto;
import org.bstu.fit.model.Link;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
@Mapper(componentModel = "spring")
public interface LinkMapper {
    LinkMapper INSTANCE = Mappers.getMapper( LinkMapper.class );
    LinkDto toDTO(Link link);
    Link fromDTO(LinkDto linkDto);
}
