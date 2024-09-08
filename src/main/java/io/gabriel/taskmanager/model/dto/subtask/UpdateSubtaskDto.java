package io.gabriel.taskmanager.model.dto.subtask;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

@Schema(description = "Update task dto schema")
public class UpdateSubtaskDto {

    @NotNull(message = "O id da tarefa deve ser informado.")
    @Schema(description = "Task id", example = "3fa85f64-5717-4562-b3fc-2c963f66afa6")
    private UUID subtaskId;

    @NotBlank(message = "O título da tarefa deve ser informado.")
    @Schema(description = "Task title", example = "Criar gerenciador de tarefas Konday")
    private String title;

    public UUID getSubtaskId() {
        return this.subtaskId;
    }

    public String getTitle() {
        return title;
    }

    public void setSubtaskId(UUID subtaskId) {
        this.subtaskId = subtaskId;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
