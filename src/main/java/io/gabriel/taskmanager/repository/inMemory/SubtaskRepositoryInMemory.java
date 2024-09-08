package io.gabriel.taskmanager.repository.inMemory;

import io.gabriel.taskmanager.model.entity.Subtask;
import io.gabriel.taskmanager.model.entity.Task;
import io.gabriel.taskmanager.repository.ISubtaskRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.stream.Collectors;

public class SubtaskRepositoryInMemory implements ISubtaskRepository {
    private final Map<UUID, Subtask> subtasks = new ConcurrentHashMap<>();

    @Override
    public Optional<Subtask> findById(UUID id) {
        return Optional.ofNullable(subtasks.get(id));
    }

    @Override
    public Optional<Subtask> findByTitleIgnoreCase(String title) {
        return subtasks.values().stream()
                .filter(subtask -> subtask.getTitle().equalsIgnoreCase(title))
                .findFirst();
    }

    @Override
    public List<Subtask> existSubtasks(Task task, String title) {
        return subtasks.values().stream()
                .filter(subtask -> subtask.getTitle().equalsIgnoreCase(title) && subtask.getTask() == task)
                .collect(Collectors.toList());
    }

    @Override
    public List<Subtask> findActiveSubtasksFromTask(Task task) {
        return subtasks.values().stream()
                .filter(subtask -> subtask.getDeletedAt() == null && subtask.getTask() == task)
                .collect(Collectors.toList());
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends Subtask> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends Subtask> List<S> saveAllAndFlush(Iterable<S> entities) {
        return List.of();
    }

    @Override
    public void deleteAllInBatch(Iterable<Subtask> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> longs) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Subtask getOne(Long aLong) {
        return null;
    }

    @Override
    public Subtask getById(Long aLong) {
        return null;
    }

    @Override
    public Subtask getReferenceById(Long aLong) {
        return null;
    }

    @Override
    public <S extends Subtask> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Subtask> List<S> findAll(Example<S> example) {
        return List.of();
    }

    @Override
    public <S extends Subtask> List<S> findAll(Example<S> example, Sort sort) {
        return List.of();
    }

    @Override
    public <S extends Subtask> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Subtask> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Subtask> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends Subtask, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }

    @Override
    public <S extends Subtask> S save(S entity) {
        return null;
    }

    @Override
    public <S extends Subtask> List<S> saveAll(Iterable<S> entities) {
        return List.of();
    }

    @Override
    public Optional<Subtask> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public List<Subtask> findAll() {
        return List.of();
    }

    @Override
    public List<Subtask> findAllById(Iterable<Long> longs) {
        return List.of();
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(Subtask entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends Subtask> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List<Subtask> findAll(Sort sort) {
        return List.of();
    }

    @Override
    public Page<Subtask> findAll(Pageable pageable) {
        return null;
    }
}
