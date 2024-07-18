package com.vashchenko.project.repository.criteria.user;

import com.vashchenko.project.model.dto.response.projection.user.UserDefaultProjection;
import org.springframework.data.jpa.domain.Specification;

public class UserCriteria {

    public static Specification<UserDefaultProjection>  nameContains(String str) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("username"), "%" + str + "%");
    }

}