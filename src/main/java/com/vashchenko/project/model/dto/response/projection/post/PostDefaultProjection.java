package com.vashchenko.project.model.dto.response.projection.post;

import com.vashchenko.project.model.dto.response.projection.user.UserInPostProjection;

import java.util.Date;
import java.util.UUID;

public interface PostDefaultProjection {
    UUID getUuid();
    String getData();
    Date getCreationDate();
    Date getUpdateDate();
    UserInPostProjection getAuthor();

}
