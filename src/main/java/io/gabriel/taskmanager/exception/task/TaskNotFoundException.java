package io.gabriel.taskmanager.exception.task;

import io.gabriel.taskmanager.exception.CustomException;

public class TaskNotFoundException extends CustomException {
    public TaskNotFoundException() {
        super("Tarefa não encontrada!");
    }
}
