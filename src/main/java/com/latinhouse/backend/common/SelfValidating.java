package com.latinhouse.backend.common;

import com.latinhouse.backend.common.exception.BadRequestException;
import jakarta.validation.*;

import java.util.Set;
import java.util.stream.Collectors;

public abstract class SelfValidating<T> {

    private Validator validator;

    public SelfValidating() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    /**
     * Evaluates all Bean Validations on the attributes of this
     * instance.
     */
    protected void validateSelf() {
        Set<ConstraintViolation<T>> violations = validator.validate((T) this);
        if (!violations.isEmpty()) {
            // throw new ConstraintViolationException(violations);
            String errorMessage = violations.stream().map(x -> x.getMessageTemplate()).collect(Collectors.toList()).get(0);
            throw new BadRequestException(errorMessage);
        }
    }
}
