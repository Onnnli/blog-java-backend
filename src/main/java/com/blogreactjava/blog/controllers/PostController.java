package com.blogreactjava.blog.controllers;

import com.blogreactjava.blog.document.Post;
import com.blogreactjava.blog.services.PostService;
import com.blogreactjava.blog.services.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;
    @GetMapping
    public ResponseEntity<List<Post>> getAllPosts() {
        return new ResponseEntity<List<Post>>(postService.allPosts(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Post> getPostById(@PathVariable ObjectId id) {
        Post newPost = postService.postById(id);

        return ResponseEntity.ok(newPost);
    }

    @PostMapping("/post")
    public ResponseEntity<Post> createPost(@RequestBody RequestCreatePost request) {

        System.out.println(request.getUser() + " ______________ GET USER");


        return new ResponseEntity<Post>(postService.createPost(
                request.getTitle(),
                request.getText(),
                request.getViewsCount(),
                request.getImageUrl(),
                request.getTags(),
                request.getComments(),
                userService.loadUserById(request.getUser())),
                HttpStatus.OK);
    }
}

