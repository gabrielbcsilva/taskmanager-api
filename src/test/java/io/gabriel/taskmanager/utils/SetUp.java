package io.gabriel.taskmanager.utils;

import io.gabriel.taskmanager.model.entity.*;
import io.gabriel.taskmanager.model.enums.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class SetUp {
    public static User user() {
        User user = new User();

        user.setId(UUID.randomUUID());
        user.setName("Gabriel");
        user.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        user.setUpdatedAt(new Timestamp(System.currentTimeMillis()));

        return user;
    }



    public static Task task() {
        Task task = new Task();
        User responsible = SetUp.user();

        task.setId(UUID.randomUUID());
        task.setDescription("task description");
        task.setTitle("task title");
        task.setPreviousTask(0L);
        task.setNuTask(1L);
        task.setStatus(Status.AWAITING.getValue());
        task.setDeletedAt(null);
        task.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        task.setPriority(Priority.LOW.getValue());
        task.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        task.setResponsible(responsible);

        return task;
    }

    public static Subtask subtask(UUID subtaskId) {
        Subtask subtask = new Subtask();
        Task task = SetUp.task();

        subtask.setId(subtaskId);
        subtask.setTitle("subtask title");
        subtask.setStatus(Status.AWAITING.getValue());
        subtask.setPriority(Priority.LOW.getValue());
        subtask.setTask(task);
        subtask.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        subtask.setUpdatedAt(new Timestamp(System.currentTimeMillis()));

        return subtask;
    }
}
