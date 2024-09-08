package io.gabriel.taskmanager.task.service;

import io.gabriel.taskmanager.utils.SetUp;
import io.gabriel.taskmanager.exception.task.TaskConflictException;
import io.gabriel.taskmanager.exception.task.TaskNotFoundException;
import io.gabriel.taskmanager.model.dto.task.UpdateTaskDto;
import io.gabriel.taskmanager.model.entity.Task;
import io.gabriel.taskmanager.repository.ITaskRepository;
import io.gabriel.taskmanager.service.TaskService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@Tag("UnitTest")
@DisplayName("Update subtask test")
public class UpdateTaskTest {
    @InjectMocks
    private TaskService taskService;

    @Mock
    private ITaskRepository taskRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should be update a task")
    void testUpdateTask_Success() {
        Task task = SetUp.task();

        UpdateTaskDto taskData = new UpdateTaskDto();
        taskData.setTitle("New task title");
        taskData.setDescription("New task description");
        taskData.setTaskId(task.getId());

        task.setTitle(taskData.getTitle());
        task.setDescription(taskData.getDescription());

        when(taskRepository.findById(taskData.getTaskId())).thenReturn(Optional.of(task));
        when(taskRepository.existTasks(taskData.getTitle())).thenReturn(List.of());
        when(taskRepository.save(task)).thenReturn(task);

        Task result = taskService.update(taskData);

        assertNotNull(result);
        assertEquals(task.getId(), result.getId());
        assertEquals(task.getTitle(), result.getTitle());
        assertEquals(task.getDescription(), result.getDescription());
    }

    @Test
    @DisplayName("Should be return not performed exception when task not exist")
    void testUpdateTask_IdNotExist() {
        UUID idAleatory = UUID.randomUUID();

        UpdateTaskDto taskData = new UpdateTaskDto();
        taskData.setTitle("New task title");
        taskData.setTaskId(idAleatory);

        when(taskRepository.findById(taskData.getTaskId())).thenReturn(Optional.empty());

        assertThrows(TaskNotFoundException.class, () -> {
            taskService.update(taskData);
        });
    }

    @Test
    @DisplayName("Should be return task conflict exception when title already exist")
    void testUpdateTask_TitleAlreadyExist() {

        Task task = SetUp.task();
        Task task2 = SetUp.task();

        UpdateTaskDto taskData = new UpdateTaskDto();
        taskData.setTitle(task.getTitle());
        taskData.setTaskId(task.getId());

        when(taskRepository.findById(taskData.getTaskId())).thenReturn(Optional.of(task));
        when(taskRepository.existTasks(taskData.getTitle())).thenReturn(List.of(task2));

        assertThrows(TaskConflictException.class, () -> {
            taskService.update(taskData);
        });
    }
}
