package io.gabriel.taskmanager.repository;

import io.gabriel.taskmanager.model.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ITaskRepository extends JpaRepository<Task, UUID> {
    Optional<Task> findById(UUID id);
    Optional<Task> findByNuTask(Long nuTask);
    Optional<Task> findByTitleIgnoreCase(String title);

    @Query("SELECT t FROM Task t WHERE LOWER(t.title) = LOWER(:title)")
    List<Task> existTasks(@Param("title") String title);

    @Query("SELECT t FROM Task t WHERE t.responsible.id = :userId ")//WHERE t.user_id = :userId
    List<Task> findAllByUserId(@Param("userId") UUID userId);


    @Query("SELECT t FROM Task t WHERE t.previousTask = :previousTask AND  t.responsible.id = :userId ")
    Optional<Task> findByPreviousTaskFromUser(@Param("previousTask") Long previousTask, @Param("userId") UUID userId);

    @Query(value = "WITH RECURSIVE task_hierarchy AS (" +
            "    SELECT *" +
            "    FROM tasks" +
            "    WHERE previous_task = 0 AND  t.responsible.id = :userId" +
            "    UNION ALL" +
            "    SELECT t.*" +
            "    FROM tasks t" +
            "    JOIN task_hierarchy th ON t.previous_task = th.nu_task" +
            ")" +
            "SELECT * FROM task_hierarchy", nativeQuery = true)
    List<Task> findActiveTasksOrderedByPreviousTask(@Param("userId") UUID userId);

    @Query("SELECT COALESCE(MAX(t.nuTask), 0) + 1 FROM Task t")
    Optional<Long> findMaxNuTask();


    @Query("SELECT t FROM Task t WHERE t.status = :status AND  t.responsible.id = :userId")
    List<Task> findAllByStatus(@Param("status") String status,@Param("userId") UUID userId);

    @Query("SELECT t FROM Task t WHERE t.priority = :priority AND  t.responsible.id = :userId")
    List<Task> findAllByPriority(@Param("priority") String priority,@Param("userId") UUID userId);

    @Query("SELECT t FROM Task t WHERE t.status = :status AND t.priority = :priority AND  t.responsible.id = :userId")
    List<Task> findAllByStatusAndPriority(@Param("status") String status, @Param("priority") String priority,@Param("userId") UUID userId);
}
