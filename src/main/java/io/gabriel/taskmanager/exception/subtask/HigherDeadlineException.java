package io.gabriel.taskmanager.exception.subtask;

import io.gabriel.taskmanager.exception.CustomException;

public class HigherDeadlineException extends CustomException {
    public HigherDeadlineException() {
        super("Vencimento invalido!");
    }
}
