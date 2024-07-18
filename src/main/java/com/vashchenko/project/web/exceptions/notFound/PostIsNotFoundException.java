package com.vashchenko.project.web.exceptions.notFound;

import com.fasterxml.jackson.databind.ser.Serializers;

public class PostIsNotFoundException extends BaseNotFoundException {
    public PostIsNotFoundException() {
        super("Post is not found");
    }
}
