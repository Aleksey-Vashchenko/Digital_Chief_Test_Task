package com.vashchenko.project.web.exceptions.conflict;

public class InsufficientPermissionsException extends BaseConflictException{
    public InsufficientPermissionsException() {
        super("Lack of permissions to perform an action");
    }
}
