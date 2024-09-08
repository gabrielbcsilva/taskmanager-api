package io.gabriel.taskmanager.controller;

import io.gabriel.taskmanager.config.swagger.DefaultApiResponse;
//import io.gabriel.konday.model.dto.subtask.SubtaskResponse;
import io.gabriel.taskmanager.model.dto.subtask.SubtaskResponse;
import io.gabriel.taskmanager.model.dto.task.*;
import io.gabriel.taskmanager.model.entity.Task;
import io.gabriel.taskmanager.model.entity.User;
import io.gabriel.taskmanager.model.enums.Priority;
import io.gabriel.taskmanager.model.enums.Status;
import io.gabriel.taskmanager.service.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@DefaultApiResponse
@RequestMapping("/task")
@Tag(name = "Task", description = "Operations related to tasks")
public class TaskController {

    private final UserService userService;
    private final TaskService taskService;
    private final SubtaskService subtaskService;

    public TaskController(
            UserService userService,
            TaskService taskService,
            SubtaskService subtaskService
    ) {
        this.userService = userService;
        this.taskService = taskService;
        this.subtaskService = subtaskService;
    }

    private TaskResponse taskResponse(Task task) {
        List<SubtaskResponse> subtasks = subtaskService.listSubtaskByTask(task);
        return TaskResponse.transform(task, null);
    }

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    @Operation(summary = "List the tasks of a specific user")
    public List<TaskResponse> listByUser(@RequestParam("userId") UUID userId) {
        List<Task> tasks = taskService.listTasks(userId);
        List<TaskResponse> taskResponses = new ArrayList<>();

        for (Task task : tasks) {
            List<SubtaskResponse> subtasks = subtaskService.listSubtaskByTask(task);
            TaskResponse taskResponse = TaskResponse.transform(task, subtasks);
            taskResponses.add(taskResponse);
        }

        return taskResponses;
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    @Operation(summary = "Create a new task")
    public TaskResponse create(@Valid @RequestBody NewTaskDto newTaskData) {

        User user = userService.findUserById(newTaskData.getResponsibleId());


        Task newTask = new Task();
        newTask.setId(UUID.randomUUID());
        newTask.setTitle(newTaskData.getTitle());
        newTask.setDescription(newTaskData.getDescription());
        newTask.setResponsible(user);
        newTask.setPriority(Priority.LOW.getValue());
        newTask.setStatus(Status.AWAITING.getValue());
        newTask.setNuTask(taskService.getLastNuTask());
        Task createdTask = taskService.add(newTask);
        return taskResponse(createdTask);
    }

    @PutMapping()
    @ResponseStatus(value = HttpStatus.OK)
    @Operation(summary = "Update task")
    public TaskResponse updateTask(@Valid @RequestBody UpdateTaskDto taskData) {
        Task task = taskService.findTaskById(taskData.getTaskId());
        String oldTitle = task.getTitle();

        Task updatedTask = taskService.update(taskData);
        return taskResponse(updatedTask);
    }

    @PutMapping("/status")
    @ResponseStatus(value = HttpStatus.OK)
    @Operation(summary = "Change task status")
    public TaskResponse changeStatus(@Valid @RequestBody ChangeStatusDto taskData) {
        Task task = taskService.findTaskById(taskData.getTaskId());
        Task updatedTask = taskService.changeStatus(taskData);
        return taskResponse(updatedTask);
    }

    @PutMapping("/priority")
    @ResponseStatus(value = HttpStatus.OK)
    @Operation(summary = "Change task priority")
    public TaskResponse changePriority(@Valid @RequestBody ChangePriorityDto taskData) {
        Task task = taskService.findTaskById(taskData.getTaskId());

        Task updatedTask = taskService.changePriority(taskData);
        return taskResponse(updatedTask);
    }

    @DeleteMapping("/remove")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @Operation(summary = "Remove a task")
    public void markAsRemoved(@RequestParam UUID taskId) {

        Task task = taskService.findTaskById(taskId);
        taskService.delete(taskId);
    }

    @GetMapping("/filter")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Filter tasks by status, priority, or both")
    public List<TaskResponse> filterTasks(
            @RequestParam(value = "status", required = false) String status,
            @RequestParam(value = "priority", required = false) String priority,
            @RequestParam(value ="userId", required = true) UUID userId) {

        User user = userService.findUserById(userId);

        List<Task> tasks;
        if (status != null && priority != null) {
            tasks = taskService.filterTasksByStatusAndPriority(status, priority,user);
        } else if (status != null) {
            tasks = taskService.filterTasksByStatus(status,user);
        } else if (priority != null) {
            tasks = taskService.filterTasksByPriority(priority,user);
        } else {
            tasks = taskService.list(); // Or return all tasks if no filters are provided
        }

        // Transform tasks to TaskResponse DTOs
        return tasks.stream()
                .map(task -> TaskResponse.transform(task, subtaskService.listSubtaskByTask(task)))
                .toList();
    }
}
