package com.vashchenko.project.repository;

import com.vashchenko.project.model.entity.User;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.UUID;

public interface UserRepository extends PagingAndSortingRepository<User, UUID> {
}
