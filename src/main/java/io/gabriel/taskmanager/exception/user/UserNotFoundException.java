package io.gabriel.taskmanager.exception.user;

import io.gabriel.taskmanager.exception.CustomException;

public class UserNotFoundException extends CustomException {
    public UserNotFoundException() {
        super("Usuário não encontrado!");
    }
}
