package com.vashchenko.project.repository;

import com.vashchenko.project.model.dto.response.projection.user.UserDefaultProjection;
import com.vashchenko.project.model.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository<T> extends JpaRepository<User, UUID>, JpaSpecificationExecutor<UserDefaultProjection>{
    User findByEmailIgnoreCase(@Nullable String email);
    Optional<T> findByUsername(String username, Class<T> type);
    @Query("select u.username as username , u.birthDate as birthDate from User u")
    Page<UserDefaultProjection> findAll(Specification<UserDefaultProjection> spec, Pageable pageable);

}
