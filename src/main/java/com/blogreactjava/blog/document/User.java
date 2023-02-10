package com.blogreactjava.blog.document;

import lombok.*;

import org.bson.types.ObjectId;

import org.springframework.boot.jackson.JsonComponent;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.Instant;
import java.util.Collection;
import java.util.Collections;

@Document(collection = "users")
@Data
@Builder
@NoArgsConstructor
@JsonComponent
@AllArgsConstructor
public class User implements UserDetails {
    @Id
    private ObjectId id;
    @NonNull
    @Indexed(unique = true)
    private String email;
    @NonNull
    private String fullName;
    @NonNull
    private String passwordHash;
    private String avatarUrl;

    public User(ObjectId id) {
        this.id = id;
    }

    @CreatedDate
    private Instant createdAt;
    @LastModifiedDate
    private Instant updatedAt;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.EMPTY_LIST;
    }

    @Override
    public String getPassword() {
        return passwordHash;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


}
