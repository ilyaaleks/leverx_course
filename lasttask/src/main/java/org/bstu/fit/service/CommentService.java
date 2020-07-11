package org.bstu.fit.service;

import org.bstu.fit.dto.CommentDto;
import org.bstu.fit.dto.CommentPageDto;
import org.bstu.fit.model.Comment;
import org.springframework.data.domain.Pageable;

public interface CommentService {
    CommentPageDto getLinkComments(long linkId, Pageable pageable);
    Comment saveComment(CommentDto commentDto);
    Comment updateComment(CommentDto commentDto);
    void delete(long commentId);
}
