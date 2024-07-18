package com.vashchenko.project.web.exceptions.notFound;

public class UserIsNotFoundException extends BaseNotFoundException{
    public UserIsNotFoundException() {
        super("User is not found");
    }
}
