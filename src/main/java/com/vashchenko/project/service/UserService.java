package com.vashchenko.project.service;

import com.vashchenko.project.model.dto.response.projection.user.UserDefaultProjection;
import com.vashchenko.project.model.entity.User;
import com.vashchenko.project.repository.UserRepository;
import com.vashchenko.project.web.exceptions.notFound.UserIsNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public void createUser(User user){
        userRepository.save(user);
    }

    public void deleteAccount(User user){
        userRepository.delete(getUserInfo(user.getUsername(), User.class));
    }

    public <T> T getUserInfo(String username, Class<T> clazz){
        Optional<T> user = userRepository.findByUsername(username, clazz);
        return user.orElseThrow(UserIsNotFoundException::new);
    }

    public <T> Page<T> findUsersByNameContains(int page, int size, Specification<UserDefaultProjection> spec) {
        Pageable pageable = PageRequest.of(page, size);
        return userRepository.findAll(spec,pageable);
    }
}
