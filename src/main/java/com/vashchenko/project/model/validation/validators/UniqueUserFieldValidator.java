package com.vashchenko.project.model.validation.validators;

import com.vashchenko.project.model.validation.annotation.UniqueUserField;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UniqueUserFieldValidator implements ConstraintValidator<UniqueUserField, String> {

    @PersistenceContext
    private EntityManager entityManager;

    private String fieldName;
    private Class<?> domainClass;

    @Override
    public void initialize(UniqueUserField constraintAnnotation) {
        this.fieldName = constraintAnnotation.fieldName();
        this.domainClass = constraintAnnotation.domainClass();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return true; // null values are considered valid, use @NotBlank or similar for required fields
        }

        String qlString = String.format("SELECT COUNT(e) FROM %s e WHERE e.%s = :value", domainClass.getSimpleName(), fieldName);
        TypedQuery<Long> query = entityManager.createQuery(qlString, Long.class);
        query.setParameter("value", value);
        return query.getSingleResult() == 0;
    }
}
