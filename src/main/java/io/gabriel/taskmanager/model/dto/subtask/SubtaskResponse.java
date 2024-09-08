package io.gabriel.taskmanager.model.dto.subtask;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.gabriel.taskmanager.model.dto.task.ResponsibleResponse;
import io.gabriel.taskmanager.model.entity.Subtask;
import io.gabriel.taskmanager.model.entity.User;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class SubtaskResponse {
    @JsonProperty("id")
    private UUID id;

    @JsonProperty("title")
    private String title;

    @JsonProperty("status")
    private String status;

    @JsonProperty("responsible")
    private List<ResponsibleResponse> responsible;

    @JsonProperty("priority")
    private String priority;

    @JsonProperty("deadline")
    private Timestamp deadline;

    @JsonProperty("createdAt")
    private Timestamp createdAt;

    @JsonProperty("updatedAt")
    private Timestamp updatedAt;

    @JsonProperty("deletedAt")
    private Timestamp deletedAt;

    @JsonProperty("taskId")
    private UUID taskId;

    public static SubtaskResponse transform(Subtask subtask) {
        SubtaskResponse subtaskResponse = new SubtaskResponse();

//        ResponsibleResponse responsible = new ResponsibleResponse(subtask.getResponsible());

        subtaskResponse.setId(subtask.getId());
        subtaskResponse.setTitle(subtask.getTitle());
        subtaskResponse.setStatus(subtask.getStatus());
        subtaskResponse.setPriority(subtask.getPriority());
        subtaskResponse.setDeadline(subtask.getDeadline());
//        subtaskResponse.setResponsible(responsible);
        subtaskResponse.setTaskId(subtask.getTask().getId());
        subtaskResponse.setCreatedAt(subtask.getCreatedAt());
        subtaskResponse.setUpdatedAt(subtask.getUpdatedAt());
        subtaskResponse.setDeletedAt(subtask.getDeletedAt());

        return subtaskResponse;
    }

    public UUID getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getStatus() {
        return status;
    }

    public String getPriority() {
        return priority;
    }

    public Timestamp getDeadline() {
        return deadline;
    }

    public List<ResponsibleResponse> getResponsible() {
        return responsible;
    }

    public UUID getTaskId() {
        return taskId;
    }

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

    public void setTitle(String title) {
        this.title = title;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public void setDeadline(Timestamp deadline) {
        this.deadline = deadline;
    }

    public void setResponsible(List<ResponsibleResponse> responsible) {
        this.responsible = responsible;
    }

    public void setTaskId(UUID taskId) {
        this.taskId = taskId;
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
