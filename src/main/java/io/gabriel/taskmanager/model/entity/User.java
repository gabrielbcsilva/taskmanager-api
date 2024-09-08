package io.gabriel.taskmanager.model.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Table(name = "users")
@Schema(description = "User entity")
public class User  {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id", nullable = false)
    @Schema(description = "User id", example = "3fa85f64-5717-4562-b3fc-2c963f66afa6")
    private UUID id;

    @Column(name = "name", nullable = false)
    @Schema(description = "User name", example = "Gabriel")
    private String name;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    @Schema(description = "User Creation date", example = "2024-07-23 14:54:18.004")
    private Timestamp createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    @Schema(description = "User Updated date", example = "2024-07-23 14:54:18.004")
    private Timestamp updatedAt;

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setName(String fullName) {
        this.name = fullName;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

}
