package com.vashchenko.project.web.exceptions.notFound;

public class BaseNotFoundException extends RuntimeException{
    public BaseNotFoundException(String message) {
        super(message);
    }

    public BaseNotFoundException() {
        super("Resource is not found");
    }
}
