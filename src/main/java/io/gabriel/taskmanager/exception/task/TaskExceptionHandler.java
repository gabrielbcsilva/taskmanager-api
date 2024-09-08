package io.gabriel.taskmanager.exception.task;

import io.gabriel.taskmanager.infra.CustomErrorResponse;
import io.gabriel.taskmanager.infra.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class TaskExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(TaskNotFoundException.class)
    private ResponseEntity<CustomErrorResponse> taskNotFound(Exception exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(new CustomErrorResponse(
                404,
                ErrorMessage.TASK_NOT_FOUND.getMessage(),
                "Não foi possível encontrar a tarefa, verifique as informações e tente novamente."
            )
        );
    }

    @ExceptionHandler(TaskConflictException.class)
    private ResponseEntity<CustomErrorResponse> taskHasExist(Exception exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
            .body(new CustomErrorResponse(
                409,
                ErrorMessage.TASK_CONFLICT.getMessage(),
                "Já existe uma tarefa com o mesmo título, verifique as informações e tente novamente."
            )
        );
    }
}
