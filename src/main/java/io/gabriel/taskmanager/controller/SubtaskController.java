package io.gabriel.taskmanager.controller;

import io.gabriel.taskmanager.config.swagger.DefaultApiResponse;
import io.gabriel.taskmanager.model.dto.subtask.NewSubtaskDto;
import io.gabriel.taskmanager.model.dto.subtask.SubtaskResponse;
import io.gabriel.taskmanager.model.dto.subtask.*;
import io.gabriel.taskmanager.model.dto.task.ResponsibleResponse;
import io.gabriel.taskmanager.model.dto.task.TaskResponse;
import io.gabriel.taskmanager.model.entity.*;
import io.gabriel.taskmanager.model.enums.Priority;
import io.gabriel.taskmanager.model.enums.Status;
import io.gabriel.taskmanager.service.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@DefaultApiResponse
@RequestMapping("/subtask")
@Tag(name = "Subtask", description = "Operations related to subtasks")
public class SubtaskController {

    private final UserService userService;
    private final TaskService taskService;
    private final SubtaskService subtaskService;

    public SubtaskController(
            TaskService taskService,
            UserService userService,
            SubtaskService subtaskService
    ) {
        this.taskService = taskService;
        this.userService = userService;
        this.subtaskService = subtaskService;
    }

    private Task getTask(UUID subtaskId) {
        SubtaskResponse subtask = subtaskService.findSubtaskById(subtaskId);
        return taskService.findTaskById(subtask.getTaskId());
    };


    private TaskResponse taskResponse(Task task) {
        List<SubtaskResponse> subtasks = subtaskService.listSubtaskByTask(task);
        return TaskResponse.transform(task, subtasks);
    }

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    @Operation(summary = "Get subtask by id")
    public SubtaskResponse getById(@RequestParam("subtaskId") UUID subtaskId) {
        return subtaskService.findSubtaskById(subtaskId);
    }

    @GetMapping("/from-task")
    @ResponseStatus(value = HttpStatus.OK)
    @Operation(summary = "List subtasks by task id")
    public List<SubtaskResponse> listByTaskId(@RequestParam("taskId") UUID taskId) {
        Task task = taskService.findTaskById(taskId);
        return subtaskService.listSubtaskByTask(task);
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    @Operation(summary = "Create a new subtask")
    public SubtaskResponse create(@Valid @RequestBody NewSubtaskDto newSubtaskData) {
        Task task = taskService.findTaskById(newSubtaskData.getTaskId());

        Subtask newSubtask = new Subtask();

        newSubtask.setTitle(newSubtaskData.getTitle());
        newSubtask.setPriority(Priority.LOW.getValue());
        newSubtask.setStatus(Status.AWAITING.getValue());
        newSubtask.setTask(task);

        return subtaskService.add(newSubtask);
    }

    @PutMapping()
    @ResponseStatus(value = HttpStatus.OK)
    @Operation(summary = "Update subtask")
    public SubtaskResponse updateSubtask(@Valid @RequestBody UpdateSubtaskDto subtaskData) {
        Task task = getTask(subtaskData.getSubtaskId());
        SubtaskResponse subtask = subtaskService.findSubtaskById(subtaskData.getSubtaskId());
        return subtaskService.update(subtaskData);
    }

    @PutMapping("/priority")
    @ResponseStatus(value = HttpStatus.OK)
    @Operation(summary = "Change subtask priority")
    public SubtaskResponse changePriority(@Valid @RequestBody ChangePriorityDto subtaskData) {
        Task task = getTask(subtaskData.getSubtaskId());
        return subtaskService.changePriority(subtaskData);
    }

    @PutMapping("/status")
    @ResponseStatus(value = HttpStatus.OK)
    @Operation(summary = "Change subtask status")
    public SubtaskResponse changeStatus(@Valid @RequestBody ChangeStatusDto subtaskData) {
        Task task = getTask(subtaskData.getSubtaskId());
        SubtaskResponse subtask = subtaskService.findSubtaskById(subtaskData.getSubtaskId());
        return subtaskService.changeStatus(subtaskData);
    }
    @DeleteMapping
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @Operation(summary = "Remove a subtask")
    public void markAsRemoved(@RequestParam(value = "subtaskId") UUID subtaskId) {
        Task task = getTask(subtaskId);
        subtaskService.delete(subtaskId);
    }
}
