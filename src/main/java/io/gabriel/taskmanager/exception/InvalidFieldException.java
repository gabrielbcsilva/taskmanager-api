package io.gabriel.taskmanager.exception;

public class InvalidFieldException extends CustomException {
    public InvalidFieldException(String message) {
        super(message);
    }

    public InvalidFieldException() {
        super("Campo inválido!");
    }
}
