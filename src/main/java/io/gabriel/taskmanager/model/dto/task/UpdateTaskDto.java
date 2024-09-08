package io.gabriel.taskmanager.model.dto.task;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

@Schema(description = "Update task dto schema")
public class UpdateTaskDto {

    @NotNull(message = "O id da tarefa deve ser informado.")
    @Schema(description = "Task id", example = "3fa85f64-5717-4562-b3fc-2c963f66afa6")
    private UUID taskId;

    @NotBlank(message = "O título da tarefa deve ser informado.")
    @Schema(description = "Task title", example = "Criar gerenciador de tarefas Konday")
    private String title;

    @Schema(description = "Task description", example = "Este será o possível substituto do Monday, para reduzir gastos com softwares de terceiros")
    private String description;

    public UUID getTaskId() {
        return this.taskId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public void setTaskId(UUID taskId) {
        this.taskId = taskId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
