package io.gabriel.taskmanager.task.service;

import io.gabriel.taskmanager.utils.SetUp;
import io.gabriel.taskmanager.exception.task.TaskNotFoundException;
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
@DisplayName("List task test")
public class ListTaskTest {
    @InjectMocks
    private TaskService taskService;

    @Mock
    private ITaskRepository taskRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should be get a task by id")
    void testFindTaskById_Success() {
        Task task = SetUp.task();

        when(taskRepository.findById(task.getId())).thenReturn(Optional.of(task));

        Task result = taskService.findTaskById(task.getId());

        assertNotNull(result);
        assertEquals(task.getId(), result.getId());
    }

    @Test
    @DisplayName("Should be return task not found exception when not exist")
    void testFindTaskById_NotFound() {
        UUID taskId = UUID.randomUUID();

        assertThrows(TaskNotFoundException.class, () -> {
            taskService.findTaskById(taskId);
        });
    }

    @Test
    @DisplayName("Should be get a task list")
    void testFindTask_Success() {
        Task task = SetUp.task();

        when(taskRepository.findActiveTasksOrderedByPreviousTask(task.getResponsible().getId())).thenReturn(List.of(task));

        List<Task> result = taskService.listTasks(task.getResponsible().getId());

        assertNotNull(result);
        assertEquals(task.getId(), result.get(0).getId());
    }
}
