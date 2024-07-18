package com.vashchenko.project.model.dto.response.projection.user;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public interface UserAdvancedProjection extends UserDefaultProjection {
    @JsonProperty("registration_date")
    Date getRegistrationDate();
    String getEmail();
}
