package com.ofilm.yk.demo1.service.impl;

import com.ofilm.yk.demo1.entity.Comment;
import com.ofilm.yk.demo1.mapper.CommentMapper;
import com.ofilm.yk.demo1.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Override
    public void addComment(Comment comment) {
        commentMapper.addComment(comment);
    }
}
