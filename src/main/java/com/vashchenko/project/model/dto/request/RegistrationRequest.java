package com.vashchenko.project.model.dto.request;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.vashchenko.project.model.entity.User;
import com.vashchenko.project.model.validation.annotation.UniqueUserField;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;

public record RegistrationRequest(
        @Pattern(regexp = "^[a-zA-Z0-9_-]{5,15}$",
                message = "Name must be between 5 and 15 characters, digits and can only contain letters, underscores, and hyphens."
        )
        @NotEmpty
        @UniqueUserField(fieldName = "username",domainClass = User.class)
        String username,
        @Email
        @NotEmpty
        @UniqueUserField(fieldName = "email",domainClass = User.class)
        String email,
        @Pattern(regexp = "^(?=.*[A-Z])(?=.*\\d)[A-Za-z\\d_]{8,20}$",
                message = "Password must be between 8 and 20 characters long, contain at least one uppercase letter, one digit, and can include letters, digits, and underscores."
        )
        @NotEmpty
        String password,
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
        @NotNull(message = "Birth date cannot be null")
        @JsonProperty("birth_date")
        Date birthDate) {
}
