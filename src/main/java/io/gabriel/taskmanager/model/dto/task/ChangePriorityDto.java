package io.gabriel.taskmanager.model.dto.task;

import io.gabriel.taskmanager.infra.notation.PriorityValidator;
import io.gabriel.taskmanager.model.enums.Priority;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

@Schema(description = "Change task priority dto schema")
public class ChangePriorityDto {

    @NotNull(message = "O id da tarefa deve ser informado.")
    @Schema(description = "Task id", example = "3fa85f64-5717-4562-b3fc-2c963f66afa6")
    private UUID taskId;

    @NotBlank(message = "O prioridade da tarefa deve ser informada.")
    @PriorityValidator(enumClass = Priority.class)
    @Schema(description = "Task priority", example = "low")
    private String priority;

    public UUID getTaskId() {
        return this.taskId;
    }

    public String getPriority() {
        return priority;
    }

    public void setTaskId(UUID taskId) {
        this.taskId = taskId;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }
}
