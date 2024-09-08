package io.gabriel.taskmanager.task.service;

import io.gabriel.taskmanager.utils.SetUp;
import io.gabriel.taskmanager.exception.task.TaskNotFoundException;
import io.gabriel.taskmanager.model.dto.task.ChangeStatusDto;
import io.gabriel.taskmanager.model.entity.Task;
import io.gabriel.taskmanager.model.enums.Status;
import io.gabriel.taskmanager.repository.ITaskRepository;
import io.gabriel.taskmanager.service.TaskService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@Tag("UnitTest")
@DisplayName("Change task status test")
public class ChangeTaskStatusTest {
    @InjectMocks
    private TaskService taskService;

    @Mock
    private ITaskRepository taskRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should be change a task status")
    void testChangeTaskStatus_Success() {
        Task task = SetUp.task();

        ChangeStatusDto statusData = new ChangeStatusDto();
        statusData.setStatus(Status.DONE.getValue());
        statusData.setTaskId(task.getId());

        when(taskRepository.findById(statusData.getTaskId())).thenReturn(Optional.of(task));
        task.setStatus(statusData.getStatus());
        when(taskRepository.save(task)).thenReturn(task);

        Task result = taskService.changeStatus(statusData);

        assertNotNull(result);
        assertEquals(task.getId(), result.getId());
        assertEquals(statusData.getStatus(), result.getStatus());
    }

    @Test
    @DisplayName("Should be return not performed exception when task not exist")
    void testChangeTaskStatus_IdNotExist() {
        UUID idAleatory = UUID.randomUUID();

        ChangeStatusDto statusData = new ChangeStatusDto();
        statusData.setStatus(Status.DONE.getValue());
        statusData.setTaskId(idAleatory);

        when(taskRepository.findById(statusData.getTaskId())).thenReturn(Optional.empty());

        assertThrows(TaskNotFoundException.class, () -> {
            taskService.changeStatus(statusData);
        });
    }
}
