package org.bstu.fit.converter;

import java.util.HashSet;
import java.util.Set;
import javax.annotation.Generated;
import org.bstu.fit.dto.CommentDto;
import org.bstu.fit.dto.LinkDto;
import org.bstu.fit.dto.TagDto;
import org.bstu.fit.dto.UserDto;
import org.bstu.fit.model.Comment;
import org.bstu.fit.model.Link;
import org.bstu.fit.model.Tag;
import org.bstu.fit.model.User;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-07-09T16:04:37+0300",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_251 (Oracle Corporation)"
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
        linkDto.setComments( commentSetToCommentDtoSet( link.getComments() ) );
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
        link.setComments( commentDtoSetToCommentSet( linkDto.getComments() ) );
        link.setCountOfLikes( linkDto.getCountOfLikes() );
        link.setCountOfDislikes( linkDto.getCountOfDislikes() );
        link.setUser( userDtoToUser( linkDto.getUser() ) );
        link.setDateOfCreation( linkDto.getDateOfCreation() );
        link.setDateOfChange( linkDto.getDateOfChange() );

        return link;
    }

    protected Set<LinkDto> linkSetToLinkDtoSet(Set<Link> set) {
        if ( set == null ) {
            return null;
        }

        Set<LinkDto> set1 = new HashSet<LinkDto>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( Link link : set ) {
            set1.add( toDTO( link ) );
        }

        return set1;
    }

    protected TagDto tagToTagDto(Tag tag) {
        if ( tag == null ) {
            return null;
        }

        TagDto tagDto = new TagDto();

        tagDto.setId( tag.getId() );
        tagDto.setName( tag.getName() );
        tagDto.setLinks( linkSetToLinkDtoSet( tag.getLinks() ) );

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

    protected Set<CommentDto> commentSetToCommentDtoSet(Set<Comment> set) {
        if ( set == null ) {
            return null;
        }

        Set<CommentDto> set1 = new HashSet<CommentDto>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( Comment comment : set ) {
            set1.add( commentToCommentDto( comment ) );
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
        userDto.setPassword( user.getPassword() );
        userDto.setEmail( user.getEmail() );
        userDto.setPhotoUrl( user.getPhotoUrl() );
        userDto.setLinks( linkSetToLinkDtoSet( user.getLinks() ) );
        userDto.setComments( commentSetToCommentDtoSet( user.getComments() ) );
        userDto.setActivate( user.isActivate() );
        userDto.setActivationCode( user.getActivationCode() );

        return userDto;
    }

    protected CommentDto commentToCommentDto(Comment comment) {
        if ( comment == null ) {
            return null;
        }

        CommentDto commentDto = new CommentDto();

        commentDto.setId( comment.getId() );
        commentDto.setAuthor( userToUserDto( comment.getAuthor() ) );
        commentDto.setText( comment.getText() );
        commentDto.setDate( comment.getDate() );
        commentDto.setLink( toDTO( comment.getLink() ) );

        return commentDto;
    }

    protected Set<Link> linkDtoSetToLinkSet(Set<LinkDto> set) {
        if ( set == null ) {
            return null;
        }

        Set<Link> set1 = new HashSet<Link>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( LinkDto linkDto : set ) {
            set1.add( fromDTO( linkDto ) );
        }

        return set1;
    }

    protected Tag tagDtoToTag(TagDto tagDto) {
        if ( tagDto == null ) {
            return null;
        }

        Tag tag = new Tag();

        tag.setId( tagDto.getId() );
        tag.setName( tagDto.getName() );
        tag.setLinks( linkDtoSetToLinkSet( tagDto.getLinks() ) );

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

    protected Set<Comment> commentDtoSetToCommentSet(Set<CommentDto> set) {
        if ( set == null ) {
            return null;
        }

        Set<Comment> set1 = new HashSet<Comment>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( CommentDto commentDto : set ) {
            set1.add( commentDtoToComment( commentDto ) );
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
        user.setLinks( linkDtoSetToLinkSet( userDto.getLinks() ) );
        user.setComments( commentDtoSetToCommentSet( userDto.getComments() ) );
        user.setActivate( userDto.isActivate() );
        user.setActivationCode( userDto.getActivationCode() );

        return user;
    }

    protected Comment commentDtoToComment(CommentDto commentDto) {
        if ( commentDto == null ) {
            return null;
        }

        Comment comment = new Comment();

        comment.setId( commentDto.getId() );
        comment.setAuthor( userDtoToUser( commentDto.getAuthor() ) );
        comment.setText( commentDto.getText() );
        comment.setDate( commentDto.getDate() );
        comment.setLink( fromDTO( commentDto.getLink() ) );

        return comment;
    }
}
