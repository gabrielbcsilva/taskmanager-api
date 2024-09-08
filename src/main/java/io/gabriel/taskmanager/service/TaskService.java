package io.gabriel.taskmanager.service;

import io.gabriel.taskmanager.exception.NotPerformedException;
import io.gabriel.taskmanager.exception.task.TaskConflictException;
import io.gabriel.taskmanager.exception.task.TaskNotFoundException;
import io.gabriel.taskmanager.model.dto.task.*;
import io.gabriel.taskmanager.model.entity.Task;
import io.gabriel.taskmanager.model.entity.User;
import io.gabriel.taskmanager.repository.ITaskRepository;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TaskService {

    private final ITaskRepository taskRepository;

    public TaskService(ITaskRepository taskRepository) { this.taskRepository = taskRepository; }

    public Task findTaskById(UUID taskId) {
        return taskRepository.findById(taskId).orElseThrow(TaskNotFoundException::new);
    }

    public Long getLastNuTask() {
        return taskRepository.findMaxNuTask().orElse(0L);
    }

    public List<Task> list() {
        return taskRepository.findAll();
    }

    public List<Task> listTasks(UUID userId) {
        return taskRepository.findAllByUserId(userId);
    }

    public List<Task> listTasksByUserid(UUID userId ) {
        return taskRepository.findActiveTasksOrderedByPreviousTask(userId);
    }

    public Task add(Task newTask) {
        boolean findTaskById = taskRepository.findById(newTask.getId()).isPresent();
        List<Task> taskWithSameTitle = taskRepository.existTasks(newTask.getTitle());

        if (findTaskById) throw new NotPerformedException();
        if (!taskWithSameTitle.isEmpty()) throw new TaskConflictException();

        List<Task> tasks = taskRepository.findActiveTasksOrderedByPreviousTask(UUID.randomUUID());

        if (tasks.isEmpty()) {
            newTask.setPreviousTask(0L);
        } else {
            Task lastTask = tasks.get(tasks.size() - 1);

            newTask.setPreviousTask(lastTask.getNuTask());
        }

        try {
            return taskRepository.save(newTask);
        } catch (DataAccessException exception) {
            throw new NotPerformedException();
        }
    }

    public Task update(UpdateTaskDto taskData) {
        Task task = taskRepository.findById(taskData.getTaskId()).orElseThrow(TaskNotFoundException::new);
        List<Task> taskWithSameTitle = taskRepository.existTasks(taskData.getTitle());

        if (!taskWithSameTitle.isEmpty() && taskWithSameTitle.get(0).getId() != task.getId()) throw new TaskConflictException();
        task.setTitle(taskData.getTitle());
        task.setDescription(taskData.getDescription());

        return taskRepository.save(task);
    }

    public Task changePriority(ChangePriorityDto taskData) {
        Task task = taskRepository.findById(taskData.getTaskId()).orElseThrow(TaskNotFoundException::new);
        task.setPriority(taskData.getPriority());

        return taskRepository.save(task);
    }

    public Task changeStatus(ChangeStatusDto taskData) {
        Task task = taskRepository.findById(taskData.getTaskId()).orElseThrow(TaskNotFoundException::new);
        task.setStatus(taskData.getStatus());

        return taskRepository.save(task);
    }

    public Task changeResponsible(ChangeResponsibleDto taskData, User responsible) {
        Task task = taskRepository.findById(taskData.getTaskId()).orElseThrow(TaskNotFoundException::new);

        User actualResponsible =  task.getResponsible();
        boolean alreadyAdded = actualResponsible.getId().equals(responsible.getId()) ;
        if (!alreadyAdded) {
            task.setResponsible(responsible);
        }

        return taskRepository.save(task);
    }

    public void delete(UUID id) {
        Task task = taskRepository.findById(id).orElseThrow(TaskNotFoundException::new);

        try {
             taskRepository.delete(task);
        } catch (DataAccessException exception) {
            throw new NotPerformedException();
        }
    }


    public List<Task> filterTasksByStatus(String status,User user) {
        return taskRepository.findAllByStatus(status,user.getId());
    }

    public List<Task> filterTasksByPriority(String priority,User user) {
        return taskRepository.findAllByPriority(priority,user.getId());
    }

    public List<Task> filterTasksByStatusAndPriority(String status, String priority,User user) {
        return taskRepository.findAllByStatusAndPriority(status, priority,user.getId());
    }
}
