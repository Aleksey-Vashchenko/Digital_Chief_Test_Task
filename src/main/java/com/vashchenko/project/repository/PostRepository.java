package com.vashchenko.project.repository;

import com.vashchenko.project.model.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PostRepository extends JpaRepository<Post, UUID> {
    <T> Page<T> findByAuthor_Username(String username, Pageable pageable, Class<T> tClass);

    Optional<Post> findByUuid(UUID uuid);

    void deleteByUuid(UUID uuid);
}
