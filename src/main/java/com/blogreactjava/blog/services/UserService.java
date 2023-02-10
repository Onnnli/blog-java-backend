package com.blogreactjava.blog.services;

import com.blogreactjava.blog.document.User;
import com.blogreactjava.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService  {
    @Autowired
    UserRepository userRepository;

    public User loadUserByEmail(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("username not found"));
    }

    public User loadUserById(String id) throws UsernameNotFoundException {
        return userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("user not found"));
    }

}
