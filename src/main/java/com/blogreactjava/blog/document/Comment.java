package com.blogreactjava.blog.document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

@Document(collection = "comments")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    @Id
    private ObjectId id;
    @DocumentReference
    private User user;
    private String text;
    @DocumentReference
    private Post post;
}
