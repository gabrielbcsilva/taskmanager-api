package io.gabriel.taskmanager.exception.subtask;

import io.gabriel.taskmanager.exception.CustomException;

public class SubtaskConflictException extends CustomException {
    public SubtaskConflictException() {
        super("Subtarefa já existe!");
    }
}
