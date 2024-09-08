package io.gabriel.taskmanager.exception.user;

import io.gabriel.taskmanager.infra.CustomErrorResponse;
import io.gabriel.taskmanager.infra.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class UserExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(UserNotFoundException.class)
    private ResponseEntity<CustomErrorResponse> userNotFound(Exception exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(new CustomErrorResponse(
                404,
                ErrorMessage.USER_NOT_FOUND.getMessage(),
                "Não foi possível encontrar o usuário, verifique as informações e tente novamente."
            )
        );
    }

}
