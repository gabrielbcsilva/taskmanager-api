package io.gabriel.taskmanager.exception.user;

import io.gabriel.taskmanager.exception.CustomException;

public class UserBlockedException extends CustomException {
    public UserBlockedException() {
        super("Usuário sem acesso!");
    }
}
