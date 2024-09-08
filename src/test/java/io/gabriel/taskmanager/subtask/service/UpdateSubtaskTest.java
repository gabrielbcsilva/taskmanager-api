package io.gabriel.taskmanager.subtask.service;

import io.gabriel.taskmanager.exception.subtask.SubtaskConflictException;
import io.gabriel.taskmanager.exception.subtask.SubtaskNotFoundException;
import io.gabriel.taskmanager.model.dto.subtask.SubtaskResponse;
import io.gabriel.taskmanager.model.dto.subtask.UpdateSubtaskDto;
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
@DisplayName("Update subtask test")
public class UpdateSubtaskTest {
    @InjectMocks
    private SubtaskService subtaskService;

    @Mock
    private ISubtaskRepository subtaskRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should be update a subtask")
    void testUpdateSubtask_Success() {
        Subtask subtask = SetUp.subtask(UUID.randomUUID());

        UpdateSubtaskDto subtaskData = new UpdateSubtaskDto();
        subtaskData.setTitle("New subtask title");
        subtaskData.setSubtaskId(subtask.getId());

        subtask.setTitle(subtaskData.getTitle());

        when(subtaskRepository.findById(subtaskData.getSubtaskId())).thenReturn(Optional.of(subtask));
        when(subtaskRepository.existSubtasks(subtask.getTask(), subtaskData.getTitle())).thenReturn(List.of());
        when(subtaskRepository.save(subtask)).thenReturn(subtask);

        SubtaskResponse result = subtaskService.update(subtaskData);

        assertNotNull(result);
        assertEquals(subtask.getId(), result.getId());
        assertEquals(subtask.getTitle(), result.getTitle());
    }

    @Test
    @DisplayName("Should be return not performed exception when subtask not exist")
    void testUpdateSubtask_IdNotExist() {
        UUID idAleatory = UUID.randomUUID();

        UpdateSubtaskDto subtaskData = new UpdateSubtaskDto();
        subtaskData.setTitle("New subtask title");
        subtaskData.setSubtaskId(idAleatory);

        when(subtaskRepository.findById(subtaskData.getSubtaskId())).thenReturn(Optional.empty());

        assertThrows(SubtaskNotFoundException.class, () -> {
            subtaskService.update(subtaskData);
        });
    }

    @Test
    @DisplayName("Should be return subtask conflict exception when title already exist")
    void testUpdateSubtask_TitleAlreadyExist() {

        Subtask subtask = SetUp.subtask(UUID.randomUUID());
        Subtask subtask2 = SetUp.subtask(UUID.randomUUID());

        UpdateSubtaskDto subtaskData = new UpdateSubtaskDto();
        subtaskData.setTitle(subtask.getTitle());
        subtaskData.setSubtaskId(subtask.getId());

        when(subtaskRepository.findById(subtaskData.getSubtaskId())).thenReturn(Optional.of(subtask));
        when(subtaskRepository.existSubtasks(subtask.getTask(), subtaskData.getTitle())).thenReturn(List.of(subtask2));

        assertThrows(SubtaskConflictException.class, () -> {
            subtaskService.update(subtaskData);
        });
    }
}
