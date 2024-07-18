package com.vashchenko.project.model.dto.request;

import jakarta.validation.constraints.*;

public record CreatePostRequest(@NotBlank @NotEmpty @Size(min = 1,max = 280) String data) {
}
