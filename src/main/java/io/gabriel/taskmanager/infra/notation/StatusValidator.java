package io.gabriel.taskmanager.infra.notation;

import io.gabriel.taskmanager.infra.validator.StatusValidatorConstraint;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = StatusValidatorConstraint.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface StatusValidator {

    Class<? extends Enum<?>> enumClass();
    String message() default "Informe um valor válido para o status";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
