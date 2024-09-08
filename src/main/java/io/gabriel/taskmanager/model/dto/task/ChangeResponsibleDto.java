package io.gabriel.taskmanager.model.dto.task;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

@Schema(description = "Change task responsible dto schema")
public class ChangeResponsibleDto {

    @NotNull(message = "O id da tarefa deve ser informado.")
    @Schema(description = "Task id", example = "3fa85f64-5717-4562-b3fc-2c963f66afa6")
    private UUID taskId;

    @NotNull(message = "O id do responsável deve ser informado.")
    @Schema(description = "Task responsible id", example = "2024-07-23 14:54:18.004")
    private UUID responsibleId;

    public UUID getTaskId() {
        return this.taskId;
    }

    public UUID getResponsibleId() {
        return responsibleId;
    }

    public void setTaskId(UUID taskId) {
        this.taskId = taskId;
    }

    public void setResponsibleId(UUID responsibleId) {
        this.responsibleId = responsibleId;
    }
}
