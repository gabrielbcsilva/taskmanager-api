package io.gabriel.taskmanager.model.dto.subtask;

import io.gabriel.taskmanager.infra.notation.PriorityValidator;
import io.gabriel.taskmanager.model.enums.Priority;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

@Schema(description = "Change subtask priority dto schema")
public class ChangePriorityDto {

    @NotNull(message = "O id da subtarefa deve ser informado.")
    @Schema(description = "Subtask id", example = "3fa85f64-5717-4562-b3fc-2c963f66afa6")
    private UUID subtaskId;

    @NotBlank(message = "O prioridade da tarefa deve ser informada.")
    @PriorityValidator(enumClass = Priority.class)
    @Schema(description = "Subtask priority", example = "low")
    private String priority;

    public UUID getSubtaskId() {
        return this.subtaskId;
    }

    public String getPriority() {
        return priority;
    }

    public void setSubtaskId(UUID subtaskId) {
        this.subtaskId = subtaskId;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }
}
