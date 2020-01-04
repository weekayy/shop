package com.ofilm.yk.demo1.mapper;

import com.ofilm.yk.demo1.entity.Comment;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CommentMapper {
    void addComment(Comment comment);
}
