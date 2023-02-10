package com.blogreactjava.blog.controllers;

import com.blogreactjava.blog.document.Comment;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequestCreatePost {
    private String title;
    private String text;
    private int viewsCount;
    private String imageUrl;
    private List<String> tags;
    private List<Comment> comments;
    @JsonSerialize
    private String user;
}