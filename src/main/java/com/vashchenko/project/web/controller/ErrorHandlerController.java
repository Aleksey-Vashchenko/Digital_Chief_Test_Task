package com.vashchenko.project.web.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.vashchenko.project.model.dto.response.ApiResponse;
import com.vashchenko.project.model.dto.response.ExtraErrorMessage;
import com.vashchenko.project.web.exceptions.conflict.BaseConflictException;
import com.vashchenko.project.web.exceptions.notFound.BaseNotFoundException;
import com.vashchenko.project.web.exceptions.notFound.UserIsNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ErrorHandlerController {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse> methodArgumentNotValidException(final MethodArgumentNotValidException e) {
        var beanClazz = e.getTarget().getClass();
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        ApiResponse errorResponse = new ApiResponse(HttpStatus.BAD_REQUEST, "Bad request. Validation is failed");
        errorResponse.addError(fieldErrors.stream()
                .map(fieldError -> new ExtraErrorMessage(fieldError.getDefaultMessage(),
                        getJsonName(fieldError,e), "condition"))
                .collect(Collectors.toList()));
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BaseNotFoundException.class)
    @ResponseBody
    public ResponseEntity<ApiResponse> notFoundException(final BaseNotFoundException e) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(new ApiResponse(status,e.getMessage()), status);
    }

    @ExceptionHandler(BaseConflictException.class)
    @ResponseBody
    public ResponseEntity<ApiResponse> conflictException(final BaseConflictException e) {
        HttpStatus status = HttpStatus.CONFLICT;
        return new ResponseEntity<>(new ApiResponse(status, e.getMessage()), status);
    }

    private String getJsonName(FieldError fieldError, MethodArgumentNotValidException e) {
        try {
            var beanClazz = e.getTarget().getClass();
            var field = beanClazz.getDeclaredField(fieldError.getField());
            var jsonPropertyAnnotation = field.getAnnotation(JsonProperty.class);
            if (jsonPropertyAnnotation != null) {
                return jsonPropertyAnnotation.value();
            } else {
               return fieldError.getField();
            }
        }
        catch (NoSuchFieldException ex) {
            return fieldError.getField();
        }
    }
}
