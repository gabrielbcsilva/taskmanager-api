package io.gabriel.taskmanager.task.service;

import io.gabriel.taskmanager.utils.SetUp;
import io.gabriel.taskmanager.exception.NotPerformedException;
import io.gabriel.taskmanager.exception.task.TaskNotFoundException;
import io.gabriel.taskmanager.model.dto.task.ChangeResponsibleDto;
import io.gabriel.taskmanager.model.entity.Task;
import io.gabriel.taskmanager.model.entity.User;
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
@DisplayName("Add task responsible test")
public class ChangeTaskResponsibleTest {
    @InjectMocks
    private TaskService taskService;

    @Mock
    private ITaskRepository taskRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should be return task not found exception when subtask not exist")
    void testChangeTaskDeadline_IdNotExist() {
        UUID idAleatory = UUID.randomUUID();
        User user = SetUp.user();

        ChangeResponsibleDto responsibleData = new ChangeResponsibleDto();
        responsibleData.setResponsibleId(user.getId());
        responsibleData.setTaskId(idAleatory);

        when(taskRepository.findById(responsibleData.getTaskId())).thenReturn(Optional.empty());

        assertThrows(TaskNotFoundException.class, () -> {
            taskService.changeResponsible(responsibleData, user);
        });
    }

    @Test
    @DisplayName("Should be not remove the unique task responsible")
    void testChangeRemoveUniqueTaskResponsible() {
        Task task = SetUp.task();
        User user = task.getResponsible();

        ChangeResponsibleDto responsibleData = new ChangeResponsibleDto();
        responsibleData.setResponsibleId(user.getId());
        responsibleData.setTaskId(task.getId());

        when(taskRepository.findById(responsibleData.getTaskId())).thenReturn(Optional.of(task));

        assertThrows(NotPerformedException.class, () -> taskService.changeResponsible(responsibleData, user));
    }

    @Test
    @DisplayName("Should be add a new task responsible")
    void testChangeAddTaskResponsible() {
        Task task = SetUp.task();
        User user = SetUp.user();

        ChangeResponsibleDto responsibleData = new ChangeResponsibleDto();
        responsibleData.setResponsibleId(user.getId());
        responsibleData.setTaskId(task.getId());

        when(taskRepository.findById(responsibleData.getTaskId())).thenReturn(Optional.of(task));
        when(taskRepository.save(task)).thenReturn(task);

        Task result = taskService.changeResponsible(responsibleData, user);

        assertTrue(result.getResponsible().getId().equals(user.getId()));
        assertEquals(2, result.getResponsible().getId());
    }

}
