package com.zuehlke.securesoftwaredevelopment.controller;

import com.zuehlke.securesoftwaredevelopment.domain.Comment;
import com.zuehlke.securesoftwaredevelopment.repository.CommentRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class CommentController {
    private CommentRepository commentRepository;

    public CommentController(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @PostMapping(value = "/comments", consumes = "application/json")
    public ResponseEntity<Void> createComment(@RequestBody Comment comment) {
        comment.setUserId(1); // TODO: set currently logged in user
        commentRepository.create(comment);

        return ResponseEntity.noContent().build();
    }
}
