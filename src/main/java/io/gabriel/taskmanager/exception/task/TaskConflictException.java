package io.gabriel.taskmanager.exception.task;

import io.gabriel.taskmanager.exception.CustomException;

public class TaskConflictException extends CustomException {
    public TaskConflictException() {
        super("Tarefa já existe!");
    }
}
