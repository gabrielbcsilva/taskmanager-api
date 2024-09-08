package io.gabriel.taskmanager.exception;

public class CustomException extends RuntimeException {
    public CustomException() {super("Erro desconhecido!");}

    public CustomException(String message) {
        super(message);
    }
}
