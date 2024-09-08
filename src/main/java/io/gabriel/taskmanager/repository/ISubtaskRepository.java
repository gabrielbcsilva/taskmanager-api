package io.gabriel.taskmanager.repository;

import io.gabriel.taskmanager.model.entity.Subtask;
import io.gabriel.taskmanager.model.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ISubtaskRepository extends JpaRepository<Subtask, Long> {
    Optional<Subtask> findById(UUID id);
    Optional<Subtask> findByTitleIgnoreCase(String title);

    @Query("SELECT s FROM Subtask s WHERE s.deletedAt IS NULL AND s.task = :task AND LOWER(s.title) = LOWER(:title)")
    List<Subtask> existSubtasks(@Param("task") Task task, @Param("title") String title);

    @Query("SELECT s FROM Subtask s WHERE s.deletedAt IS NULL AND s.task = :task ORDER BY s.createdAt ASC")
    List<Subtask> findActiveSubtasksFromTask(@Param("task") Task task);
}
