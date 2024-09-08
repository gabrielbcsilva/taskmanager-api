package io.gabriel.taskmanager.model.dto.task;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

@Schema(description = "New task dto schema")
public class NewTaskDto {

    @NotBlank(message = "O título da tarefa deve ser informado.")
    @Schema(description = "Task title", example = "Titulo da Tarefa", nullable = false)
    private String title;

    @Schema(description = "Task description", example = "Descrição da Tarefa", nullable = false)
    private String description;

    @NotNull(message = "O id do responsável pela tarefa deve ser informado.")
    @Schema(description = "Responsible id", example = "3fa85f64-5717-4562-b3fc-2c963f66afa6", nullable = false)
    private UUID responsibleId;

    public String getTitle() {
        return title;
    }

    public UUID getResponsibleId() {
        return responsibleId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void seResponsibleId(UUID responsibleId) {
        this.responsibleId = responsibleId;
    }

    public String getDescription() { return description;}

    public void setDescription(String description) { this.description = description;  }
}
