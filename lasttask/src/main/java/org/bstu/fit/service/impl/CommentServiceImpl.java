package org.bstu.fit.service.impl;

import org.bstu.fit.dto.CommentDto;
import org.bstu.fit.dto.CommentPageDto;
import org.bstu.fit.service.CommentService;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class CommentServiceImpl implements CommentService {

    @Override
    public CommentPageDto getLinkComment(long linkId, Pageable pageable) {
        return null;
    }

    @Override
    public CommentDto saveComment(CommentDto commentDto) {
        return null;
    }

    @Override
    public CommentDto updateComment(CommentDto commentDto) {
        return null;
    }

    @Override
    public void delete(long commentId) {

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
