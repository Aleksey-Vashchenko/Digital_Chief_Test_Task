package com.vashchenko.project.model.mapping;

import com.vashchenko.project.model.dto.request.RegistrationRequest;
import com.vashchenko.project.model.dto.response.projection.user.UserDefaultDto;
import com.vashchenko.project.model.dto.response.projection.user.UserDefaultProjection;
import com.vashchenko.project.model.entity.User;
import lombok.AllArgsConstructor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import java.time.Instant;
import java.util.Date;
import java.util.List;

@Mapper(componentModel = "spring")
@Component
public abstract class UserMapper {

    private PasswordEncoder passwordEncoder;

    @Mapping(source = "username", target = "username")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "password", target = "passwordHash", qualifiedByName  = "passwordToHash" )
    @Mapping(source = "birthDate", target = "birthDate")
    @Mapping(target = "registrationDate", expression = "java( java.util.Date.from(java.time.Instant.now()) )")
    public abstract User registrationToEntity(RegistrationRequest request);

    @Named("passwordToHash")
    protected String passwordToHash(String password) {
        return passwordEncoder.encode(password);
    }
    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }
}
