package io.gabriel.taskmanager.exception.user;

import io.gabriel.taskmanager.exception.CustomException;

public class UserConflictException extends CustomException {
    public UserConflictException() {
        super("Email já cadastrado!");
    }
}
