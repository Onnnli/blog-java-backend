package com.blogreactjava.blog.document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.time.Instant;
import java.util.List;

@Document(collection = "posts")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Post {


    @Id
    private ObjectId id;
    private String title;
    private String text;
    private List<String> tags;
    private int viewsCount;
    private String imageUrl;
    @DocumentReference(collection = "comments")
    private List<Comment> comments;
    @DocumentReference(collection = "users")
    private User user;
    @CreatedDate
    private Instant createdAt;
    @LastModifiedDate
    private Instant updatedAt;

    public Post(String title, String text, List<String> tags, int viewsCount, String imageUrl, List<Comment> comments, User user) {
        this.title = title;
        this.text = text;
        this.tags = tags;
        this.viewsCount = viewsCount;
        this.imageUrl = imageUrl;
        this.comments = comments;
        this.user = user;
    }

    public ObjectId getId() {
        return id;
    }
}
