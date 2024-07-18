package com.vashchenko.project.model.dto.response.projection.user;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDefaultDto {
    String username;
    Date birthDate;
}
