package org.bstu.fit.service;

import org.bstu.fit.dto.CommentDto;
import org.bstu.fit.dto.CommentPageDto;
import org.springframework.data.domain.Pageable;

public interface CommentService {
    CommentPageDto getLinkComment(long linkId, Pageable pageable);
    CommentDto saveComment(CommentDto commentDto);
    CommentDto updateComment(CommentDto commentDto);
    void delete(long commentId);
}
