package com.vashchenko.project.web.controller;

import com.vashchenko.project.model.dto.request.CreatePostRequest;
import com.vashchenko.project.model.dto.request.UpdatePostRequest;
import com.vashchenko.project.model.dto.response.ApiResponse;
import com.vashchenko.project.model.dto.response.projection.post.PostDefaultProjection;
import com.vashchenko.project.model.entity.Post;
import com.vashchenko.project.model.entity.User;
import com.vashchenko.project.model.mapping.PostMapper;
import com.vashchenko.project.service.PostService;
import com.vashchenko.project.web.exceptions.notFound.PostIsNotFoundException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.UUID;

@RestController
@RequestMapping("/users")
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@RequiredArgsConstructor
public class PostController {

    PostService postService;
    PostMapper postMapper;

    @PostMapping("/posts")
    ResponseEntity<ApiResponse> addPost(@Valid @RequestBody CreatePostRequest request){
        Post createdPost = postService.createPost(postMapper.toEntity(request),(User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        HttpStatus status = HttpStatus.CREATED;
        return new ResponseEntity<>(new ApiResponse<>(status,postMapper.toCreatedResponseDto(createdPost)),status);
    }

    @DeleteMapping("/posts/{uuid}")
    ResponseEntity<ApiResponse> deletePost(@PathVariable(name = "uuid") String uuid,
                                           Principal principal){
        try {
            postService.deletePost(UUID.fromString(uuid), principal);
            HttpStatus status = HttpStatus.OK;
            return new ResponseEntity<>(new ApiResponse<>(status),status);
        }
        catch (IllegalArgumentException e){
            throw new PostIsNotFoundException();
        }
    }

    @GetMapping("/{user}/posts")
    ResponseEntity<ApiResponse> getPosts(
                                         @PathVariable(name = "user") String username,
                                         @RequestParam(defaultValue = "0") @Min(0) Integer page,
                                         @RequestParam(defaultValue = "10") @Min(5) @Max(100) Integer size){

        HttpStatus status = HttpStatus.CREATED;
        Page<PostDefaultProjection> posts = postService.findAuthorsPosts(username, page,size, PostDefaultProjection.class);
        return new ResponseEntity<>(new ApiResponse<>(status,posts),status);
    }

    @GetMapping("/me/posts")
    ResponseEntity<ApiResponse> getMyPosts(
            @RequestParam(defaultValue = "0") @Min(0) Integer page,
            @RequestParam(defaultValue = "10") @Min(5) @Max(100) Integer size){
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Page<PostDefaultProjection> posts = postService.findAuthorsPosts(currentUser.getUsername(), page,size, PostDefaultProjection.class);
        HttpStatus status = HttpStatus.OK;
        return new ResponseEntity<>(new ApiResponse<>(status,posts),status);
    }
    @PatchMapping("/posts/{uuid}")
    ResponseEntity<ApiResponse> updatePost(@Valid @RequestBody UpdatePostRequest request,
                                           @PathVariable(name = "uuid") String uuid){
        try {
            Post updatedPost = postService.updatePost(UUID.fromString(uuid),request);
            HttpStatus status = HttpStatus.OK;
            return new ResponseEntity<>(new ApiResponse<>(status,postMapper.toUpdatedResponseDto(updatedPost)),status);
        }
        catch (IllegalArgumentException e){
            throw new PostIsNotFoundException();
        }

    }
}
