package io.gabriel.taskmanager.exception;

import io.gabriel.taskmanager.infra.CustomErrorResponse;
import io.gabriel.taskmanager.infra.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

@RestControllerAdvice
public class GlobalsExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    private ResponseEntity<CustomErrorResponse> badRequest(MethodArgumentNotValidException exception) {

        List<String> errors = new java.util.ArrayList<>(List.of());

        exception.getBindingResult().getAllErrors().forEach((error) -> {
            String errorMessage = error.getDefaultMessage();
            errors.add(errorMessage);
        });

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(new CustomErrorResponse(
                400,
                ErrorMessage.BAD_REQUEST.getMessage(),
                errors.get(0)
            )
        );
    }

    @ExceptionHandler(InvalidFieldException.class)
    private ResponseEntity<CustomErrorResponse> invalidFields(InvalidFieldException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(new CustomErrorResponse(
                400,
                ErrorMessage.BAD_REQUEST.getMessage(),
                exception.getMessage()
            )
        );
    }

//    @ExceptionHandler(BadCredentialsException.class)
//    private ResponseEntity<CustomErrorResponse> invalidCredentials(Exception exception) {
//        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
//            .body(new CustomErrorResponse(
//                401,
//                "Falha ao entrar!",
//                "Não foi possível fazer login, email ou senha incorretas."
//            )
//        );
//    }

    @ExceptionHandler(HttpClientErrorException.Forbidden.class)
    private ResponseEntity<CustomErrorResponse> forbidden(Exception exception) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
            .body(new CustomErrorResponse(
                403,
                ErrorMessage.FORBIDDEN.getMessage(),
                "Não foi possível realizar ação, acesso não permitido."
            )
        );
    }

    @ExceptionHandler(InternalError.class)
    private ResponseEntity<CustomErrorResponse> internalError(Exception exception) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(new CustomErrorResponse(
                500,
                ErrorMessage.INTERNAL_SERVER_ERROR.getMessage(),
                "Não foi possível realizar ação, verifique as informações e tente novamente."
            )
        );
    }

    @ExceptionHandler(NotPerformedException.class)
    private ResponseEntity<CustomErrorResponse> notPerformed(NotPerformedException exception) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
            .body(new CustomErrorResponse(
                403,
                ErrorMessage.NOT_PERFORMED.getMessage(),
                "Não foi possível realizar ação, verifique as informações e tente novamente."
            )
        );
    }

    @ExceptionHandler(MailSendException.class)
    private ResponseEntity<CustomErrorResponse> mailSendError(MailSendException exception) {
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED)
            .body(new CustomErrorResponse(
                501,
                ErrorMessage.MAIL_SEND_ERROR.getMessage(),
                "Não foi possível enviar email, você pode solicitar alteração de senha."
            )
        );
    }

    @ExceptionHandler(InvalidTokenException.class)
    private ResponseEntity<CustomErrorResponse> invalidTokenError(InvalidTokenException exception) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
            .body(new CustomErrorResponse(
                403,
                "Link inválido ou expirado!",
                "Link inválido ou expirado, solicite um novo link de acesso."
            )
        );
    }
}
