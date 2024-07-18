package com.vashchenko.project.model.mapping;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.vashchenko.project.model.dto.request.CreatePostRequest;
import com.vashchenko.project.model.dto.request.RegistrationRequest;
import com.vashchenko.project.model.entity.Post;
import com.vashchenko.project.model.entity.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Date;
import java.util.UUID;

@Mapper(componentModel = "spring")
@Component
public abstract class PostMapper {

    @Mapping(source = "data", target = "data")
    @Mapping(target = "creationDate", expression = "java( java.util.Date.from(java.time.Instant.now()) )")
    @Mapping(target = "updateDate", expression = "java(null)")
    public abstract Post toEntity(CreatePostRequest request);

    @Named("getCurrentDate")
    protected Date getCurrentDate() {
        return Date.from(Instant.now());
    }

    @Mapping(target = "creationDate", source = "creationDate")
    @Mapping(target = "uuid", source = "uuid")
    public abstract CreatedPostResponse toCreatedResponseDto(Post post);
    @Mapping(target = "updateDate", source = "updateDate")
    public abstract UpdatedPostResponse toUpdatedResponseDto(Post post);


    @Getter
    @Setter
    @FieldDefaults(level = AccessLevel.PRIVATE)
    protected static class CreatedPostResponse{
        UUID uuid;
        @JsonProperty("creation_date")
        Date creationDate;
    }

    @Getter
    @Setter
    @FieldDefaults(level = AccessLevel.PRIVATE)
    protected static class UpdatedPostResponse{
        UUID uuid;
        @JsonProperty("update_date")
        Date updateDate;
    }
}
