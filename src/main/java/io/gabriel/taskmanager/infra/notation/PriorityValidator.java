package io.gabriel.taskmanager.infra.notation;

import io.gabriel.taskmanager.infra.validator.PriorityValidatorConstraint;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PriorityValidatorConstraint.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface PriorityValidator {

    Class<? extends Enum<?>> enumClass();
    String message() default "Informe um valor válido para a prioridade.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
