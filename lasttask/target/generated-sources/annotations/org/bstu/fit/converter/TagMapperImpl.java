package org.bstu.fit.converter;

import javax.annotation.Generated;
import org.bstu.fit.dto.TagDto;
import org.bstu.fit.model.Tag;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-07-12T23:43:28+0300",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 11.0.7 (Oracle Corporation)"
)
@Component
public class TagMapperImpl implements TagMapper {

    @Override
    public TagDto toDTO(Tag tag) {
        if ( tag == null ) {
            return null;
        }

        TagDto tagDto = new TagDto();

        tagDto.setId( tag.getId() );
        tagDto.setName( tag.getName() );

        return tagDto;
    }

    @Override
    public Tag fromDTO(TagDto tagDto) {
        if ( tagDto == null ) {
            return null;
        }

        Tag tag = new Tag();

        tag.setId( tagDto.getId() );
        tag.setName( tagDto.getName() );

        return tag;
    }
}
