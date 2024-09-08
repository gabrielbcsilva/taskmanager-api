package io.gabriel.taskmanager.exception.subtask;

import io.gabriel.taskmanager.exception.CustomException;

public class SubtaskNotFoundException extends CustomException {
    public SubtaskNotFoundException() {
        super("Subtarefa não encontrada!");
    }
}
