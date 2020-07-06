package org.bstu.fit.converter;

import javax.annotation.Generated;
import org.bstu.fit.dto.LinkDto;
import org.bstu.fit.model.Link;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-07-06T14:16:02+0300",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 11.0.7 (Oracle Corporation)"
)
public class LinkMapperImpl implements LinkMapper {

    @Override
    public LinkDto toDTO(Link link) {
        if ( link == null ) {
            return null;
        }

        LinkDto linkDto = new LinkDto();

        return linkDto;
    }

    @Override
    public Link fromDTO(LinkDto linkDto) {
        if ( linkDto == null ) {
            return null;
        }

        Link link = new Link();

        return link;
    }
}
