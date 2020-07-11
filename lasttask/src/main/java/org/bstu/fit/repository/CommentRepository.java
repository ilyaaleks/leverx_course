package org.bstu.fit.repository;

import org.bstu.fit.model.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface CommentRepository extends CrudRepository<Comment, Long> {
    Page<Comment> findByLink_Id(long linkId, Pageable page);
    Comment findById(long commentId);
}
