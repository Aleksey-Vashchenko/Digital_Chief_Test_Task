package com.vashchenko.project.service;

import com.vashchenko.project.model.dto.request.UpdatePostRequest;
import com.vashchenko.project.model.entity.Post;
import com.vashchenko.project.model.entity.User;
import com.vashchenko.project.repository.PostRepository;
import com.vashchenko.project.web.exceptions.conflict.InsufficientPermissionsException;
import com.vashchenko.project.web.exceptions.notFound.PostIsNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.Instant;
import java.util.Date;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    public Post createPost(Post post, User user){
        post.setAuthor(user);
        return postRepository.save(post);
    }

    public void deletePost(UUID uuid, Principal principal){
        Post deletedPost = findPostByUuid(uuid);
        if(deletedPost.getAuthor().getUsername().equals(principal.getName())){
            postRepository.deleteByUuid(uuid);
        }
        throw new InsufficientPermissionsException();
    }

    public <T> Page<T> findAuthorsPosts(String username, Integer page, Integer size, Class<T> clazz) {
        return postRepository.findByAuthor_Username(username, PageRequest.of(page, size), clazz);
    }

    public Post updatePost(UUID uuid, UpdatePostRequest request){
        Post updatedPost = findPostByUuid(uuid);
        updatedPost.setData(request.data());
        updatedPost.setUpdateDate(Date.from(Instant.now()));
        return postRepository.save(updatedPost);
    }
    private Post findPostByUuid(UUID uuid){
        return postRepository.findByUuid(uuid).orElseThrow(PostIsNotFoundException::new);
    }
}
