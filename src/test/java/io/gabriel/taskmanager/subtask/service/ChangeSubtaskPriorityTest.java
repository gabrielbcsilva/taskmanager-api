package io.gabriel.taskmanager.subtask.service;

import io.gabriel.taskmanager.exception.subtask.SubtaskNotFoundException;
import io.gabriel.taskmanager.model.dto.subtask.ChangePriorityDto;
import io.gabriel.taskmanager.model.dto.subtask.SubtaskResponse;
import io.gabriel.taskmanager.model.entity.Subtask;
import io.gabriel.taskmanager.model.enums.Priority;
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
@DisplayName("Change subtask priority test")
public class ChangeSubtaskPriorityTest {
    @InjectMocks
    private SubtaskService subtaskService;

    @Mock
    private ISubtaskRepository subtaskRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should be to change a subtask priority")
    void testChangeSubtaskPriority_Success() {
        Subtask subtask = SetUp.subtask(UUID.randomUUID());

        ChangePriorityDto priorityData = new ChangePriorityDto();
        priorityData.setPriority(Priority.HIGH.getValue());
        priorityData.setSubtaskId(subtask.getId());

        when(subtaskRepository.findById(priorityData.getSubtaskId())).thenReturn(Optional.of(subtask));
        subtask.setPriority(priorityData.getPriority());
        when(subtaskRepository.save(subtask)).thenReturn(subtask);

        SubtaskResponse result = subtaskService.changePriority(priorityData);

        assertNotNull(result);
        assertEquals(subtask.getId(), result.getId());
        assertEquals(priorityData.getPriority(), result.getPriority());
    }

    @Test
    @DisplayName("Should be return not performed exception when subtask not exist")
    void testChangeSubtaskPriority_IdNotExist() {
        UUID idAleatory = UUID.randomUUID();

        ChangePriorityDto priorityData = new ChangePriorityDto();
        priorityData.setPriority(Priority.HIGH.getValue());
        priorityData.setSubtaskId(idAleatory);

        when(subtaskRepository.findById(priorityData.getSubtaskId())).thenReturn(Optional.empty());

        assertThrows(SubtaskNotFoundException.class, () -> {
            subtaskService.changePriority(priorityData);
        });
    }
}
