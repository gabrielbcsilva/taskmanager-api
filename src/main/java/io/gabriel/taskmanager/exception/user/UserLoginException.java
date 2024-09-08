package io.gabriel.taskmanager.exception.user;

import io.gabriel.taskmanager.exception.CustomException;

public class UserLoginException extends CustomException {
    public UserLoginException() {
        super("Falha na autenticação!");
    }
}
