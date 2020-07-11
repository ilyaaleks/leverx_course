package org.bstu.fit.controller;

import org.bstu.fit.converter.CommentMapper;
import org.bstu.fit.dto.CommentDto;
import org.bstu.fit.dto.CommentPageDto;
import org.bstu.fit.model.Comment;
import org.bstu.fit.service.CommentService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/comment")
public class CommentController {
    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }
    @GetMapping("/{linkId}")
    public ResponseEntity<CommentPageDto> getComments(@PathVariable long linkId, Pageable pageable)
    {
        return ResponseEntity.ok(commentService.getLinkComments(linkId,pageable));
    }
    @PostMapping
    public ResponseEntity<CommentDto> save(@RequestBody CommentDto commentDto)
    {
       Comment comment= commentService.saveComment(commentDto);
       return ResponseEntity.ok(CommentMapper.INSTANCE.toDTO(comment));
    }
    @PutMapping
    public ResponseEntity<CommentDto> update(@RequestBody CommentDto commentDto)
    {
        Comment comment= commentService.updateComment(commentDto);
        return ResponseEntity.ok(CommentMapper.INSTANCE.toDTO(comment));
    }
    @DeleteMapping("/{commentId}")
    @ResponseStatus(value = HttpStatus.OK)
    public void delete(@PathVariable long commentId)
    {
        commentService.delete(commentId);
    }
}
