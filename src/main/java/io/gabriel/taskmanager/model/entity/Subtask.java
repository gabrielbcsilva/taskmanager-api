package io.gabriel.taskmanager.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "subtasks")
@Schema(description = "Subtask entity")
public class Subtask {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "subtask_id", nullable = false)
    @Schema(description = "Subtask id", example = "3fa85f64-5717-4562-b3fc-2c963f66afa6")
    private UUID id;

    @Schema(description = "Subtask title", example = "Criar gerenciador de tarefas Konday")
    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "status", nullable = false)
    @Schema(description = "Subtask status", example = "In progress")
    private String status;

    @Column(name = "deadline")
    @Schema(description = "Deadline to subtask", example = "2024-07-23 14:54:18.004")
    private Timestamp deadline;

    @Column(name = "priority", nullable = false)
    @Schema(description = "Subtask priority", example = "Medium")
    private String priority;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    @Schema(description = "subtask creation date", example = "2024-07-23 14:54:18.004")
    private Timestamp createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    @Schema(description = "Subtask updated date", example = "2024-07-23 14:54:18.004")
    private Timestamp updatedAt;

    @Column(name = "deleted_at")
    @Schema(description = "Subtask deletion log", example = "null")
    private Timestamp deletedAt;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_id", nullable = false, columnDefinition = "UUID")
    @Schema(description = "Referência a tarefa que esta subtarefa pertence", example = "3fa85f64-5717-4562-b3fc-2c963f66afa6")
    private Task task;

    public Timestamp getDeadline() {
        return deadline;
    }

    public String getStatus() {
        return status;
    }

    public Task getTask() {
        return task;
    }

    public Timestamp getDeletedAt() {
        return deletedAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public String getPriority() {
        return priority;
    }

    public String getTitle() {
        return title;
    }

    public UUID getId() {
        return id;
    }


    public void setDeadline(Timestamp deadline) {
        this.deadline = deadline;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void setDeletedAt(Timestamp deletedAt) {
        this.deletedAt = deletedAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }


    public void setPriority(String priority) {
        this.priority = priority;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
