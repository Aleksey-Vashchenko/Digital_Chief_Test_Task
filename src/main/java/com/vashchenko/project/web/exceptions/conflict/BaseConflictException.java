package com.vashchenko.project.web.exceptions.conflict;

public class BaseConflictException extends RuntimeException{
    public BaseConflictException(String message) {
        super(message);
    }
}
