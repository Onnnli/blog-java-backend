package com.blogreactjava.blog.services;

import com.blogreactjava.blog.document.Comment;
import com.blogreactjava.blog.document.Post;
import com.blogreactjava.blog.document.User;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import com.blogreactjava.blog.repository.PostRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private MongoTemplate mongoTemplate;

    public List<Post> allPosts() {
        return postRepository.findAll();
    }


    public Post postById(ObjectId id) {
        Optional<Post> post = postRepository.findById(id);

        if(post.isPresent()) {
            Query query = new Query().addCriteria(Criteria.where("id").is(id));
            Update update = new Update().inc("viewsCount",1);
            FindAndModifyOptions findAndModifyOptions = new FindAndModifyOptions().returnNew(true).upsert(true);
            Post newPost = mongoTemplate.findAndModify(query, update, findAndModifyOptions, Post.class);

            return newPost;
        }

        return null;
    }

    public Post createPost(
            String title,
            String text,
            int viewsCount,
            String imageUrl,
            List<String> tags,
            List<Comment> comments,
            User user
    ) {
        Post post = postRepository.insert(new Post(title, text, tags, viewsCount, imageUrl, comments, user));

        return post;
    }
}
