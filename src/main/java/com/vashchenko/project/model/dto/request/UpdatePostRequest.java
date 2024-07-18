package com.vashchenko.project.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record UpdatePostRequest(@NotBlank @NotEmpty @Size(min = 1,max = 280) String data){}
