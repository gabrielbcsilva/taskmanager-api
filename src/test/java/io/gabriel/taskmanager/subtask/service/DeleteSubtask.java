package io.gabriel.taskmanager.subtask.service;

import io.gabriel.taskmanager.exception.subtask.SubtaskNotFoundException;
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

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@Tag("UnitTest")
@DisplayName("Mark subtask as deleted test")
class DeleteSubtask {

    @InjectMocks
    private SubtaskService subtaskService;

    @Mock
    private ISubtaskRepository subtaskRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should be returned subtask not found exception when subtask not exist")
    void testDeleteSubtaskWhenIdNotFound() {
        UUID idAleatory = UUID.randomUUID();
        when(subtaskRepository.findById(idAleatory)).thenReturn(Optional.empty());

        assertThrows(SubtaskNotFoundException.class, () -> subtaskService.delete(idAleatory));
    }

    @Test
    @DisplayName("Should be mark as deleted subtask successfully")
    void testDeleteSubtaskSuccess() {
        UUID projectId = UUID.randomUUID();
        Subtask subtask = SetUp.subtask(UUID.randomUUID());

        when(subtaskRepository.findById(projectId)).thenReturn(Optional.of(subtask));

        subtaskService.delete(projectId);

        verify(subtaskRepository).save(subtask);
    }
}
