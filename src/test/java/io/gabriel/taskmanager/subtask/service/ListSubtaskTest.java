package io.gabriel.taskmanager.subtask.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import io.gabriel.taskmanager.exception.subtask.SubtaskNotFoundException;
import io.gabriel.taskmanager.model.dto.subtask.SubtaskResponse;
import io.gabriel.taskmanager.model.entity.Subtask;
import io.gabriel.taskmanager.model.entity.Task;
import io.gabriel.taskmanager.repository.ISubtaskRepository;
import io.gabriel.taskmanager.service.SubtaskService;
import io.gabriel.taskmanager.utils.SetUp;
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

@Tag("UnitTest")
@DisplayName("List subtask test")
public class ListSubtaskTest {
    @InjectMocks
    private SubtaskService subtaskService;

    @Mock
    private ISubtaskRepository subtaskRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should be get a subtask by id")
    void testFindSubtaskById_Success() {
        Subtask subtask = SetUp.subtask(UUID.randomUUID());

        when(subtaskRepository.findById(subtask.getId())).thenReturn(Optional.of(subtask));

        SubtaskResponse result = subtaskService.findSubtaskById(subtask.getId());

        assertNotNull(result);
        assertEquals(subtask.getId(), result.getId());
    }

    @Test
    @DisplayName("Should be return subtask not found exception when not exist")
    void testFindSubtaskById_NotFound() {
        UUID subtaskId = UUID.randomUUID();

        assertThrows(SubtaskNotFoundException.class, () -> {
            subtaskService.findSubtaskById(subtaskId);
        });
    }

    @Test
    @DisplayName("Should be get a subtask list by task")
    void testFindSubtaskByTask_Success() {
        Task task = SetUp.task();
        Subtask subtask = SetUp.subtask(UUID.randomUUID());

        when(subtaskRepository.findActiveSubtasksFromTask(task)).thenReturn(List.of(subtask));

        List<SubtaskResponse> result = subtaskService.listSubtaskByTask(task);

        assertNotNull(result);
        assertEquals(SubtaskResponse.transform(subtask).getId(), result.get(0).getId());
    }
}
