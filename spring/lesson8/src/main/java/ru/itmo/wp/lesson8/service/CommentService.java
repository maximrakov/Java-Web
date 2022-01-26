package ru.itmo.wp.lesson8.service;

import org.springframework.stereotype.Service;
import ru.itmo.wp.lesson8.repository.CommentRepository;

@Service
public class CommentService {
    private final CommentRepository commentRepostitory;

    public CommentService(CommentRepository commentRepostitory) {
        this.commentRepostitory = commentRepostitory;
    }

}
