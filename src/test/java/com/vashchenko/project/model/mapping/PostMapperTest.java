package com.vashchenko.project.model.mapping;

import com.vashchenko.project.model.dto.request.CreatePostRequest;
import com.vashchenko.project.model.entity.Post;
import com.vashchenko.project.model.entity.User;
import org.instancio.Instancio;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PostMapperTest {

    @Autowired
    PostMapper postMapper;

    @Test
    void toEntity() {
        CreatePostRequest request = Instancio.of(CreatePostRequest.class).create();
        Post post = postMapper.toEntity(request);
        assertNotNull(post.getCreationDate());
        assertNull(post.getUpdateDate());
    }

    @Test
    void toCreatedResponseDto() {
    }

    @Test
    void toUpdatedResponseDto() {
    }
}