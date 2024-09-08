package io.gabriel.taskmanager.task.service;

import io.gabriel.taskmanager.utils.SetUp;
import io.gabriel.taskmanager.exception.task.TaskNotFoundException;
import io.gabriel.taskmanager.model.dto.task.ChangePriorityDto;
import io.gabriel.taskmanager.model.entity.Task;
import io.gabriel.taskmanager.model.enums.Priority;
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
@DisplayName("Change task priority test")
public class ChangeTaskPriorityTest {
    @InjectMocks
    private TaskService taskService;

    @Mock
    private ITaskRepository taskRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should be change a task priority")
    void testChangeTaskPriority_Success() {
        Task task = SetUp.task();

        io.gabriel.taskmanager.model.dto.task.ChangePriorityDto priorityData = new ChangePriorityDto();
        priorityData.setPriority(Priority.HIGH.getValue());
        priorityData.setTaskId(task.getId());

        when(taskRepository.findById(priorityData.getTaskId())).thenReturn(Optional.of(task));
        task.setPriority(priorityData.getPriority());
        when(taskRepository.save(task)).thenReturn(task);

        Task result = taskService.changePriority(priorityData);

        assertNotNull(result);
        assertEquals(task.getId(), result.getId());
        assertEquals(priorityData.getPriority(), result.getPriority());
    }

    @Test
    @DisplayName("Should be return not performed exception when task not exist")
    void testChangeTaskPriority_IdNotExist() {
        UUID idAleatory = UUID.randomUUID();

        ChangePriorityDto priorityData = new ChangePriorityDto();
        priorityData.setPriority(Priority.HIGH.getValue());
        priorityData.setTaskId(idAleatory);

        when(taskRepository.findById(priorityData.getTaskId())).thenReturn(Optional.empty());

        assertThrows(TaskNotFoundException.class, () -> {
            taskService.changePriority(priorityData);
        });
    }
}
