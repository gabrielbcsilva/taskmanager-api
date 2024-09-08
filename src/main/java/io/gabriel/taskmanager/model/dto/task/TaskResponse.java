package io.gabriel.taskmanager.model.dto.task;

import com.fasterxml.jackson.annotation.JsonProperty;
//import io.gabriel.konday.model.dto.subtask.SubtaskResponse;
import io.gabriel.taskmanager.model.dto.subtask.SubtaskResponse;
import io.gabriel.taskmanager.model.entity.Task;
import io.gabriel.taskmanager.model.entity.User;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TaskResponse {
    @JsonProperty("id")
    private UUID id;

    @JsonProperty("nuTask")
    private Long nuTask;

    @JsonProperty("title")
    private String title;

    @JsonProperty("description")
    private String description;

    @JsonProperty("status")
    private String status;

    @JsonProperty("previousTask")
    private Long previousTask;

    @JsonProperty("responsible")
    private ResponsibleResponse responsible;

    @JsonProperty("priority")
    private String priority;

    @JsonProperty("createdAt")
    private Timestamp createdAt;

    @JsonProperty("updatedAt")
    private Timestamp updatedAt;

    @JsonProperty("deletedAt")
    private Timestamp deletedAt;


    @JsonProperty("subtasks")
    private List<SubtaskResponse> subtasks;

    public static TaskResponse transform(Task task, List<SubtaskResponse> subtasks) {
        TaskResponse taskResponse = new TaskResponse();

        ResponsibleResponse responsible = new ResponsibleResponse(task.getResponsible());

        taskResponse.setId(task.getId());
        taskResponse.setNuTask(task.getNuTask());
        taskResponse.setTitle(task.getTitle());
        taskResponse.setDescription(task.getDescription());
        taskResponse.setStatus(task.getStatus());
        taskResponse.setPreviousTask(task.getPreviousTask());
        taskResponse.setPriority(task.getPriority());
        taskResponse.setResponsible(responsible);
        taskResponse.setSubtasks(subtasks);
        taskResponse.setCreatedAt(task.getCreatedAt());
        taskResponse.setUpdatedAt(task.getUpdatedAt());
        taskResponse.setDeletedAt(task.getDeletedAt());

        return taskResponse;
    }

    public UUID getId() {
        return id;
    }


    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getStatus() {
        return status;
    }

    public Long getPreviousTask() {
        return previousTask;
    }

    public String getPriority() {
        return priority;
    }

    public ResponsibleResponse getResponsible() {
        return responsible;
    }


//    public List<SubtaskResponse> getSubtasks() {
//        return subtasks;
//    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public Timestamp getDeletedAt() {
        return deletedAt;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setNuTask(Long nuTask) {
        this.nuTask = nuTask;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setPreviousTask(Long previousTask) {
        this.previousTask = previousTask;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public void setResponsible(ResponsibleResponse responsible) {
        this.responsible = responsible;
    }

    public void setSubtasks(List<SubtaskResponse> subtasks) {
        this.subtasks = subtasks;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void setDeletedAt(Timestamp deletedAt) {
        this.deletedAt = deletedAt;
    }
}
