package io.gabriel.taskmanager.service;

import io.gabriel.taskmanager.exception.NotPerformedException;
import io.gabriel.taskmanager.exception.subtask.HigherDeadlineException;
import io.gabriel.taskmanager.exception.subtask.SubtaskConflictException;
import io.gabriel.taskmanager.exception.subtask.SubtaskNotFoundException;
import io.gabriel.taskmanager.model.dto.subtask.SubtaskResponse;
import io.gabriel.taskmanager.model.dto.subtask.UpdateSubtaskDto;
import io.gabriel.taskmanager.model.dto.subtask.*;
import io.gabriel.taskmanager.model.entity.Subtask;
import io.gabriel.taskmanager.model.entity.Task;
import io.gabriel.taskmanager.model.entity.User;
import io.gabriel.taskmanager.repository.ISubtaskRepository;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.*;

@Service
public class SubtaskService {

    private final ISubtaskRepository subtaskRepository;

    public SubtaskService(ISubtaskRepository subtaskRepository) { this.subtaskRepository = subtaskRepository; }

    public SubtaskResponse findSubtaskById(UUID subtaskId) {
        Subtask subtask = subtaskRepository.findById(subtaskId).orElseThrow(SubtaskNotFoundException::new);
        return SubtaskResponse.transform(subtask);
    }

    public List<SubtaskResponse> listSubtaskByTask(Task task) {
        List<Subtask> subtasks = subtaskRepository.findActiveSubtasksFromTask(task);
        List<SubtaskResponse> subtaskResponse = new ArrayList<>();

        for (Subtask subtask : subtasks) {
            SubtaskResponse subtaskModified = SubtaskResponse.transform(subtask);
            subtaskResponse.add(subtaskModified);
        }

        return subtaskResponse;
    }

    public SubtaskResponse add(Subtask newSubtask) {
        boolean findSubtaskById = subtaskRepository.findById(newSubtask.getId()).isPresent();
        List<Subtask> subtasksWithSameTitle = subtaskRepository.existSubtasks(newSubtask.getTask(), newSubtask.getTitle());

        if (findSubtaskById) throw new NotPerformedException();
        if (!subtasksWithSameTitle.isEmpty()) throw new SubtaskConflictException();

        try {
            Subtask subtask = subtaskRepository.save(newSubtask);
            return SubtaskResponse.transform(subtask);
        } catch (DataAccessException exception) {
            throw new NotPerformedException();
        }
    }

    public SubtaskResponse update(UpdateSubtaskDto subtaskData) {
        Subtask subtask = subtaskRepository.findById(subtaskData.getSubtaskId()).orElseThrow(SubtaskNotFoundException::new);

        List<Subtask> subtasksWithSameTitle = subtaskRepository.existSubtasks(subtask.getTask(), subtaskData.getTitle());

        if(!subtasksWithSameTitle.isEmpty() && subtasksWithSameTitle.get(0).getId() != subtask.getId()) throw new SubtaskConflictException();

        subtask.setTitle(subtaskData.getTitle());

        try {
            Subtask subtaskUpdated = subtaskRepository.save(subtask);
            return SubtaskResponse.transform(subtaskUpdated);
        } catch (DataAccessException exception) {
            throw new NotPerformedException();
        }
    }

    public SubtaskResponse changePriority(ChangePriorityDto subtaskData) {
        Subtask subtask = subtaskRepository.findById(subtaskData.getSubtaskId()).orElseThrow(SubtaskNotFoundException::new);
        subtask.setPriority(subtaskData.getPriority());

        try {
            Subtask subtaskUpdated = subtaskRepository.save(subtask);
            return SubtaskResponse.transform(subtaskUpdated);
        } catch (DataAccessException exception) {
            throw new NotPerformedException();
        }
    }

    public SubtaskResponse changeStatus(ChangeStatusDto subtaskData) {
        Subtask subtask = subtaskRepository.findById(subtaskData.getSubtaskId()).orElseThrow(SubtaskNotFoundException::new);
        subtask.setStatus(subtaskData.getStatus());

        try {
            Subtask subtaskUpdated = subtaskRepository.save(subtask);
            return SubtaskResponse.transform(subtaskUpdated);
        } catch (DataAccessException exception) {
            throw new NotPerformedException();
        }
    }

    public void delete(UUID id) {
        Subtask subtask = subtaskRepository.findById(id).orElseThrow(SubtaskNotFoundException::new);
        try {
            subtaskRepository.delete(subtask);
        } catch (DataAccessException exception) {
            throw new NotPerformedException();
        }
    }
}
