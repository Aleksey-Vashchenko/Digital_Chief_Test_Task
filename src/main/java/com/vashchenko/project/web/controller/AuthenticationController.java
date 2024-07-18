package com.vashchenko.project.web.controller;

import com.vashchenko.project.model.dto.request.RegistrationRequest;
import com.vashchenko.project.model.dto.response.ApiResponse;
import com.vashchenko.project.model.mapping.UserMapper;
import com.vashchenko.project.service.UserService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@RequiredArgsConstructor
public class AuthenticationController {

    UserService userService;
    UserMapper userMapper;

    @PostMapping("/registration")
    ResponseEntity<ApiResponse> registration(@Valid @RequestBody RegistrationRequest request){
        userService.createUser(userMapper.registrationToEntity(request));
        HttpStatus status = HttpStatus.CREATED;
        return new ResponseEntity<>(new ApiResponse<>(status),status);
    }
}
