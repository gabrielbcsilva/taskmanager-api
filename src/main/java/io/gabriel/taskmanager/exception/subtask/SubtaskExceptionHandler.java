package io.gabriel.taskmanager.exception.subtask;

import io.gabriel.taskmanager.infra.CustomErrorResponse;
import io.gabriel.taskmanager.infra.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class SubtaskExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(SubtaskNotFoundException.class)
    private ResponseEntity<CustomErrorResponse> subtaskNotFound(Exception exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(new CustomErrorResponse(
                404,
                ErrorMessage.SUBTASK_NOT_FOUND.getMessage(),
                "Não foi possível encontrar a subtarefa, verifique as informações e tente novamente."
            )
        );
    }

    @ExceptionHandler(SubtaskConflictException.class)
    private ResponseEntity<CustomErrorResponse> subtaskHasExist(Exception exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
            .body(new CustomErrorResponse(
                409,
                ErrorMessage.SUBTASK_CONFLICT.getMessage(),
                "Já existe uma tarefa com o mesmo título, verifique as informações e tente novamente."
            )
        );
    }
}
