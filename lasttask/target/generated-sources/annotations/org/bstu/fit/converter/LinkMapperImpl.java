package org.bstu.fit.converter;

import java.util.HashSet;
import java.util.Set;
import javax.annotation.Generated;
import org.bstu.fit.dto.LinkDto;
import org.bstu.fit.dto.TagDto;
import org.bstu.fit.dto.UserDto;
import org.bstu.fit.model.Link;
import org.bstu.fit.model.Tag;
import org.bstu.fit.model.User;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-07-16T00:18:26+0300",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 11.0.7 (Oracle Corporation)"
)
@Component
public class LinkMapperImpl implements LinkMapper {

    @Override
    public LinkDto toDTO(Link link) {
        if ( link == null ) {
            return null;
        }

        LinkDto linkDto = new LinkDto();

        linkDto.setId( link.getId() );
        linkDto.setName( link.getName() );
        linkDto.setUrl( link.getUrl() );
        linkDto.setStatus( link.getStatus() );
        linkDto.setTags( tagSetToTagDtoSet( link.getTags() ) );
        linkDto.setCountOfLikes( link.getCountOfLikes() );
        linkDto.setCountOfDislikes( link.getCountOfDislikes() );
        linkDto.setUser( userToUserDto( link.getUser() ) );
        linkDto.setDateOfCreation( link.getDateOfCreation() );
        linkDto.setDateOfChange( link.getDateOfChange() );

        return linkDto;
    }

    @Override
    public Link fromDTO(LinkDto linkDto) {
        if ( linkDto == null ) {
            return null;
        }

        Link link = new Link();

        link.setId( linkDto.getId() );
        link.setName( linkDto.getName() );
        link.setUrl( linkDto.getUrl() );
        link.setStatus( linkDto.getStatus() );
        link.setTags( tagDtoSetToTagSet( linkDto.getTags() ) );
        link.setCountOfLikes( linkDto.getCountOfLikes() );
        link.setCountOfDislikes( linkDto.getCountOfDislikes() );
        link.setUser( userDtoToUser( linkDto.getUser() ) );
        link.setDateOfCreation( linkDto.getDateOfCreation() );
        link.setDateOfChange( linkDto.getDateOfChange() );

        return link;
    }

    protected TagDto tagToTagDto(Tag tag) {
        if ( tag == null ) {
            return null;
        }

        TagDto tagDto = new TagDto();

        tagDto.setId( tag.getId() );
        tagDto.setName( tag.getName() );

        return tagDto;
    }

    protected Set<TagDto> tagSetToTagDtoSet(Set<Tag> set) {
        if ( set == null ) {
            return null;
        }

        Set<TagDto> set1 = new HashSet<TagDto>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( Tag tag : set ) {
            set1.add( tagToTagDto( tag ) );
        }

        return set1;
    }

    protected UserDto userToUserDto(User user) {
        if ( user == null ) {
            return null;
        }

        UserDto userDto = new UserDto();

        userDto.setId( user.getId() );
        userDto.setLastName( user.getLastName() );
        userDto.setName( user.getName() );
        userDto.setUsername( user.getUsername() );
        userDto.setEmail( user.getEmail() );
        userDto.setPhotoUrl( user.getPhotoUrl() );
        userDto.setActivate( user.isActivate() );
        userDto.setActivationCode( user.getActivationCode() );
        userDto.setPassword( user.getPassword() );

        return userDto;
    }

    protected Tag tagDtoToTag(TagDto tagDto) {
        if ( tagDto == null ) {
            return null;
        }

        Tag tag = new Tag();

        tag.setId( tagDto.getId() );
        tag.setName( tagDto.getName() );

        return tag;
    }

    protected Set<Tag> tagDtoSetToTagSet(Set<TagDto> set) {
        if ( set == null ) {
            return null;
        }

        Set<Tag> set1 = new HashSet<Tag>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( TagDto tagDto : set ) {
            set1.add( tagDtoToTag( tagDto ) );
        }

        return set1;
    }

    protected User userDtoToUser(UserDto userDto) {
        if ( userDto == null ) {
            return null;
        }

        User user = new User();

        user.setId( userDto.getId() );
        user.setLastName( userDto.getLastName() );
        user.setName( userDto.getName() );
        user.setUsername( userDto.getUsername() );
        user.setPassword( userDto.getPassword() );
        user.setEmail( userDto.getEmail() );
        user.setPhotoUrl( userDto.getPhotoUrl() );
        user.setActivate( userDto.isActivate() );
        user.setActivationCode( userDto.getActivationCode() );

        return user;
    }
}
