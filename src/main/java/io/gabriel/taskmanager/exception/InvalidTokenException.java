package io.gabriel.taskmanager.exception;

public class InvalidTokenException extends CustomException {
    public InvalidTokenException() {
        super("Link inválido ou expirado!");
    }
}
