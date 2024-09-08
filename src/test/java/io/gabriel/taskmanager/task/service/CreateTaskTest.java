package io.gabriel.taskmanager.task.service;

import io.gabriel.taskmanager.utils.SetUp;
import io.gabriel.taskmanager.exception.NotPerformedException;
import io.gabriel.taskmanager.exception.task.TaskConflictException;
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
@DisplayName("Create task test")
public class CreateTaskTest {
    @InjectMocks
    private TaskService taskService;

    @Mock
    private ITaskRepository taskRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should be create a task with previous task 0")
    void testCreateTask_Success() {
        Task task = SetUp.task();

        when(taskRepository.findById(task.getId())).thenReturn(Optional.empty());
        when(taskRepository.existTasks(task.getTitle())).thenReturn(List.of());
        when(taskRepository.save(task)).thenReturn(task);

        Task result = taskService.add(task);

        assertNotNull(result);
        assertEquals(task.getId(), result.getId());
        assertEquals(task.getPreviousTask(), result.getPreviousTask());
    }

    @Test
    @DisplayName("Should be return not performed exception when id already exist")
    void testCreateTask_IdAlreadyExist() {
        Task task = SetUp.task();

        when(taskRepository.findById(task.getId())).thenReturn(Optional.of(task));

        assertThrows(NotPerformedException.class, () -> {
            taskService.add(task);
        });
    }

    @Test
    @DisplayName("Should be return task conflict exception when title already exist")
    void testCreateTask_TitleAlreadyExist() {

        Task task = SetUp.task();
        Task task2 = SetUp.task();
        task2.setId(UUID.randomUUID());

        when(taskRepository.existTasks(task.getTitle())).thenReturn(List.of(task2));

        assertThrows(TaskConflictException.class, () -> {
            taskService.add(task);
        });
    }
}
