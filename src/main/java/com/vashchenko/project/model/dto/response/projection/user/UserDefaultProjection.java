package com.vashchenko.project.model.dto.response.projection.user;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public interface UserDefaultProjection {
    String getUsername();

    @JsonProperty("birth_date")
    Date getBirthDate();
}
