package org.bstu.fit.service.impl;

import org.bstu.fit.converter.CommentMapper;
import org.bstu.fit.converter.LinkMapper;
import org.bstu.fit.dto.CommentDto;
import org.bstu.fit.dto.CommentPageDto;
import org.bstu.fit.dto.LinkDto;
import org.bstu.fit.dto.LinkPageDto;
import org.bstu.fit.model.Comment;
import org.bstu.fit.model.Link;
import org.bstu.fit.model.User;
import org.bstu.fit.repository.CommentRepository;
import org.bstu.fit.repository.UserRepository;
import org.bstu.fit.service.CommentService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Service
public class CommentServiceImpl implements CommentService {
    private CommentRepository commentRepository;
    private UserRepository userRepository;

    public CommentServiceImpl(CommentRepository commentRepository, UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
    }

    @Override
    public CommentPageDto getLinkComments(long linkId, Pageable pageable) {
        Page<Comment> commentPage= commentRepository.findByLink_Id(linkId,pageable);
        if(commentPage==null)
        {
            throw new IllegalArgumentException("Wrong link id");
        }
        List<CommentDto> commentDtos=new ArrayList<>();
        for(Comment comment:commentPage.getContent())
        {
            CommentDto commentDto= CommentMapper.INSTANCE.toDTO(comment);
            commentDtos.add(commentDto);
        }
        CommentPageDto commentPageDto=new CommentPageDto(commentDtos,pageable.getPageNumber(),commentPage.getTotalPages());
        return commentPageDto;
    }

    @Override
    public Comment saveComment(CommentDto commentDto) {
        if(commentDto==null || !commentDto.getAuthor().getUsername().equals(getUsernameOfCurrentUser()))
        {
            throw new IllegalArgumentException("Save comment: wrong parameters");
        }
        User user=userRepository.findByUsername(commentDto.getAuthor().getUsername());
        Comment comment=CommentMapper.INSTANCE.fromDTO(commentDto);
        comment.setAuthor(user);
        comment.setDate(new Date());
        return commentRepository.save(comment);
    }

    @Override
    public Comment updateComment(CommentDto commentDto) {
        if(commentDto==null || !commentDto.getAuthor().getUsername().equals(getUsernameOfCurrentUser()))
        {
            throw new IllegalArgumentException("Update comment: wrong parameters");
        }
        Comment comment=commentRepository.findById(commentDto.getId());
        if(comment==null)
        {
            throw new IllegalArgumentException("Update comment: this comment does not exist");
        }
        comment.setDate(new Date());
        comment.setText(commentDto.getText());
        return commentRepository.save(comment);
    }

    @Override
    public void delete(long commentId) {
        commentRepository.deleteById(commentId);
    }
    private String getUsernameOfCurrentUser()
    {
        String username;
        Object principal= SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }
        return username;
    }
}
