package com.vashchenko.project.model.mapping;

import com.vashchenko.project.model.dto.request.RegistrationRequest;
import com.vashchenko.project.model.entity.User;
import org.instancio.Instancio;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserMapperTest {

    @Autowired
    UserMapper userMapper;

    @Test
    void registrationToEntity() {
        RegistrationRequest request = Instancio.of(RegistrationRequest.class).create();
        User user = userMapper.registrationToEntity(request);
        assertNotEquals(user.getPasswordHash(),request.password());
        assertNotNull(user.getRegistrationDate());
    }
}