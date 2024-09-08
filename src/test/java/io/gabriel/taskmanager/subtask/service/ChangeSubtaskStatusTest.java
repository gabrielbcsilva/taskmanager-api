package io.gabriel.taskmanager.subtask.service;

import io.gabriel.taskmanager.exception.subtask.SubtaskNotFoundException;
import io.gabriel.taskmanager.model.dto.subtask.ChangeStatusDto;
import io.gabriel.taskmanager.model.dto.subtask.SubtaskResponse;
import io.gabriel.taskmanager.model.entity.Subtask;
import io.gabriel.taskmanager.model.enums.Status;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@Tag("UnitTest")
@DisplayName("Change subtask status test")
public class ChangeSubtaskStatusTest {
    @InjectMocks
    private SubtaskService subtaskService;

    @Mock
    private ISubtaskRepository subtaskRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should be to change a subtask status")
    void testChangeSubtaskStatus_Success() {
        Subtask subtask = SetUp.subtask(UUID.randomUUID());

        ChangeStatusDto statusData = new ChangeStatusDto();
        statusData.setStatus(Status.DONE.getValue());
        statusData.setSubtaskId(subtask.getId());

        when(subtaskRepository.findById(statusData.getSubtaskId())).thenReturn(Optional.of(subtask));
        subtask.setStatus(statusData.getStatus());
        when(subtaskRepository.save(subtask)).thenReturn(subtask);

        SubtaskResponse result = subtaskService.changeStatus(statusData);

        assertNotNull(result);
        assertEquals(subtask.getId(), result.getId());
        assertEquals(statusData.getStatus(), result.getStatus());
    }

    @Test
    @DisplayName("Should be return not performed exception when subtask not exist")
    void testChangeSubtaskStatus_IdNotExist() {
        UUID idAleatory = UUID.randomUUID();

        ChangeStatusDto statusData = new ChangeStatusDto();
        statusData.setStatus(Status.DONE.getValue());
        statusData.setSubtaskId(idAleatory);

        when(subtaskRepository.findById(statusData.getSubtaskId())).thenReturn(Optional.empty());

        assertThrows(SubtaskNotFoundException.class, () -> {
            subtaskService.changeStatus(statusData);
        });
    }
}
