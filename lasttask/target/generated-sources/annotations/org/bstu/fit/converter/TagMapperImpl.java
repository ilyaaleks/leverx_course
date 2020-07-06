package org.bstu.fit.converter;

import javax.annotation.Generated;
import org.bstu.fit.dto.TagDto;
import org.bstu.fit.model.Tag;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-07-06T14:16:01+0300",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 11.0.7 (Oracle Corporation)"
)
public class TagMapperImpl implements TagMapper {

    @Override
    public TagDto toDTO(Tag tag) {
        if ( tag == null ) {
            return null;
        }

        TagDto tagDto = new TagDto();

        return tagDto;
    }

    @Override
    public Tag fromDTO(TagDto tagDto) {
        if ( tagDto == null ) {
            return null;
        }

        Tag tag = new Tag();

        return tag;
    }
}
