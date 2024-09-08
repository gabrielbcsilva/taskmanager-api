package io.gabriel.taskmanager.subtask.service;

import io.gabriel.taskmanager.exception.NotPerformedException;
import io.gabriel.taskmanager.exception.subtask.SubtaskConflictException;
import io.gabriel.taskmanager.model.dto.subtask.SubtaskResponse;
import io.gabriel.taskmanager.model.entity.Subtask;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@Tag("UnitTest")
@DisplayName("Create subtask test")
public class CreateSubtaskTest {
    @InjectMocks
    private SubtaskService subtaskService;

    @Mock
    private ISubtaskRepository subtaskRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should be create a subtask")
    void testCreateSubtask_Success() {
        Subtask subtask = SetUp.subtask(UUID.randomUUID());

        when(subtaskRepository.findById(subtask.getId())).thenReturn(Optional.empty());
        when(subtaskRepository.existSubtasks(subtask.getTask(), subtask.getTitle())).thenReturn(List.of());
        when(subtaskRepository.save(subtask)).thenReturn(subtask);

        SubtaskResponse result = subtaskService.add(subtask);

        assertNotNull(result);
        assertEquals(subtask.getId(), result.getId());
    }

    @Test
    @DisplayName("Should be return not performed exception when id already exist")
    void testCreateSubtask_IdAlreadyExist() {
        Subtask subtask = SetUp.subtask(UUID.randomUUID());

        when(subtaskRepository.findById(subtask.getId())).thenReturn(Optional.of(subtask));

        assertThrows(NotPerformedException.class, () -> {
            subtaskService.add(subtask);
        });
    }

    @Test
    @DisplayName("Should be return subtask conflict exception when title already exist")
    void testCreateSubtask_TitleAlreadyExist() {

        Subtask subtask = SetUp.subtask(UUID.randomUUID());
        Subtask subtask2 = SetUp.subtask(UUID.randomUUID());

        when(subtaskRepository.existSubtasks(subtask.getTask(), subtask.getTitle())).thenReturn(List.of(subtask2));

        assertThrows(SubtaskConflictException.class, () -> {
            subtaskService.add(subtask);
        });
    }
}
