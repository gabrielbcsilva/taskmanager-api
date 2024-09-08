package io.gabriel.taskmanager.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Table(name = "tasks")
@Schema(description = "Task entity")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "task_id", nullable = false)
    @Schema(description = "Task id", example = "3fa85f64-5717-4562-b3fc-2c963f66afa6")
    private UUID id;


    @Column(name = "nu_task", nullable = false)
    @Schema(description = "Task number", example = "1")
    private Long nuTask;

    @Schema(description = "Task title", example = "Criar gerenciador de tarefas Konday")
    @Column(name = "title", nullable = false)
    private String title;

    @Schema(description = "Task description", example = "Este será o possível substituto do Monday, para reduzir gastos com softwares de terceiros")
    @Column(name = "description")
    private String description;

    @Column(name = "status", nullable = false)
    @Schema(description = "Task status", example = "in progress")
    private String status;


    @Column(name = "previous_task", nullable = false)
    @Schema(description = "Previous task in list", example = "0")
    private Long previousTask;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "responsible_id", nullable = false)
    @Schema(description = "Task responsible")
    private User responsible;


    @Column(name = "priority", nullable = false)
    @Schema(description = "Task priority", example = "Medium")
    private String priority;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    @Schema(description = "Task creation date", example = "2024-07-23 14:54:18.004")
    private Timestamp createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    @Schema(description = "Task updated date", example = "2024-07-23 14:54:18.004")
    private Timestamp updatedAt;

    @Column(name = "deleted_at")
    @Schema(description = "Task deletion log", example = "null")
    private Timestamp deletedAt;


    public UUID getId() {
        return this.id;
    }

    public Long getNuTask() {
        return nuTask;
    }

    public String getStatus() {
        return status;
    }

    public Long getPreviousTask() {
        return previousTask;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getPriority() {
        return priority;
    }

    public User getResponsible() {
        return responsible;
    }

    public Timestamp getCreatedAt() { return createdAt; }

    public Timestamp getUpdatedAt() { return updatedAt; }

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

    public void setResponsible(User responsible) {
        this.responsible = responsible;
    }

    public void setCreatedAt(Timestamp createdAt) { this.createdAt = createdAt; }

    public void setUpdatedAt(Timestamp updatedAt) { this.updatedAt = updatedAt; }

    public void setDeletedAt(Timestamp deletedAt) {
        this.deletedAt = deletedAt;
    }

}
