package io.gabriel.taskmanager.infra.validator;

import io.gabriel.taskmanager.exception.InvalidFieldException;
import io.gabriel.taskmanager.infra.notation.StatusValidator;
import io.gabriel.taskmanager.model.enums.Status;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StatusValidatorConstraint implements ConstraintValidator<StatusValidator, String> {

    Set<String> values;

    @Override
    public void initialize(StatusValidator constraintAnnotation) {
        values = Stream.of(constraintAnnotation.enumClass().getEnumConstants())
                .map(enumConstant -> ((Status) enumConstant).getValue())
                .collect(Collectors.toSet());
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
            if(values.contains(value)) {
                return values.contains(value);
            }
            throw new InvalidFieldException(String.format("O valor '%s' não é um status válido.", value));
    }
}
