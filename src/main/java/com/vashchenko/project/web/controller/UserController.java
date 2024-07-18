package com.vashchenko.project.web.controller;

import com.vashchenko.project.model.dto.response.ApiResponse;
import com.vashchenko.project.model.dto.response.projection.user.UserAdvancedProjection;
import com.vashchenko.project.model.dto.response.projection.user.UserDefaultProjection;
import com.vashchenko.project.model.entity.User;
import com.vashchenko.project.service.UserService;
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

@RestController
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    UserService userService;
    @DeleteMapping()
    ResponseEntity<ApiResponse> deleteAccount(){
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        userService.deleteAccount(currentUser);
        HttpStatus status = HttpStatus.OK;
        return new ResponseEntity<>(new ApiResponse<>(status),status);
    }

    @GetMapping("/{userName}")
    ResponseEntity<ApiResponse> getUserInfo(@PathVariable(name = "userName") String username){
        HttpStatus status = HttpStatus.OK;
        return new ResponseEntity<>(
                new ApiResponse<>(status,userService.getUserInfo(username, UserDefaultProjection.class)),
                status);
    }

    @GetMapping("/me")
    ResponseEntity<ApiResponse> getMyInfo(){
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        HttpStatus status = HttpStatus.OK;
        return new ResponseEntity<>(
                new ApiResponse<>(status,userService.getUserInfo(currentUser.getUsername(), UserAdvancedProjection.class)),
                status);
    }

    @GetMapping()
    ResponseEntity<ApiResponse> getUsers(@RequestParam(defaultValue = "0") @Min(0) Integer page,
                                         @RequestParam(defaultValue = "10") @Min(5) @Max(100) Integer size,
                                         @RequestParam(name = "str",defaultValue = "") String str){
        str=str+"%";
        Page<UserDefaultProjection> users = userService.findUsersByNameContains(page,size,str);
        HttpStatus status = HttpStatus.OK;
        return new ResponseEntity<>(
                new ApiResponse<>(status,users),
                status);
    }
}
