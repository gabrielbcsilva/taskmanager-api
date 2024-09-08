package io.gabriel.taskmanager.repository.inMemory;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.stream.Collectors;

import io.gabriel.taskmanager.model.entity.User;
import io.gabriel.taskmanager.repository.IUserRepository;
import org.springframework.data.domain.*;
import org.springframework.data.repository.query.FluentQuery;

public class UserRepositoryInMemory implements IUserRepository {
    private final Map<UUID, User> userMap = new ConcurrentHashMap<>();

    @Override
    public Optional<User> findById(UUID id) {
        return Optional.ofNullable(userMap.get(id));
    }

    @Override
    public List<User> listAll() {
        return userMap.values().stream()
//                .filter(user -> !user.getProfile().equals(UserProfile.MASTER.getValue()))
                .toList();
    }

    @Override
    public void flush() {}

    @Override
    public <S extends User> S saveAndFlush(S entity) {
        userMap.put(entity.getId(), entity);
        return entity;
    }

    @Override
    public <S extends User> List<S> saveAllAndFlush(Iterable<S> entities) {
        List<S> result = new ArrayList<>();
        for (S entity : entities) {
            userMap.put(entity.getId(), entity);
            result.add(entity);
        }
        return result;
    }

    @Override
    public void deleteAllInBatch(Iterable<User> entities) {
        entities.forEach(entity -> userMap.remove(entity.getId()));
    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> ids) {
        ids.forEach(userMap::remove);
    }

    @Override
    public void deleteAllInBatch() {
        userMap.clear();
    }

    @Override
    public User getOne(Long id) {
        return userMap.get(id);
    }

    @Override
    public User getById(Long id) {
        return userMap.get(id);
    }

    @Override
    public User getReferenceById(Long id) {
        return userMap.get(id);
    }

    @Override
    public <S extends User> Optional<S> findOne(Example<S> example) {
        return userMap.values().stream()
                .filter(example.getProbe()::equals)
                .map(example.getProbeType()::cast)
                .findFirst();
    }

    @Override
    public <S extends User> List<S> findAll(Example<S> example) {
        return userMap.values().stream()
                .filter(example.getProbe()::equals)
                .map(example.getProbeType()::cast)
                .collect(Collectors.toList());
    }

    @Override
    public <S extends User> List<S> findAll(Example<S> example, Sort sort) {
        return List.of();
    }

    @Override
    public <S extends User> Page<S> findAll(Example<S> example, Pageable pageable) {
        List<S> results = findAll(example);
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), results.size());
        List<S> subList = results.subList(start, end);
        return new PageImpl<>(subList, pageable, results.size());
    }

    @Override
    public <S extends User> long count(Example<S> example) {
        return findAll(example).size();
    }

    @Override
    public <S extends User> boolean exists(Example<S> example) {
        return findOne(example).isPresent();
    }

    @Override
    public <S extends User, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null; // queryFunction.apply(QueryByExampleExecutor.super.findBy(example, queryFunction));
    }

    @Override
    public <S extends User> S save(S entity) {
        userMap.put(entity.getId(), entity);
        return entity;
    }

    @Override
    public <S extends User> List<S> saveAll(Iterable<S> entities) {
        List<S> result = new ArrayList<>();
        for (S entity : entities) {
            userMap.put(entity.getId(), entity);
            result.add(entity);
        }
        return result;
    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.ofNullable(userMap.get(id));
    }

    @Override
    public boolean existsById(Long id) {
        return userMap.containsKey(id);
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(userMap.values());
    }

    @Override
    public List<User> findAllById(Iterable<Long> ids) {
        List<User> result = new ArrayList<>();
        for (Long id : ids) {
            User user = userMap.get(id);
            if (user != null) {
                result.add(user);
            }
        }
        return result;
    }

    @Override
    public long count() {
        return userMap.size();
    }

    @Override
    public void deleteById(Long id) {
        userMap.remove(id);
    }

    @Override
    public void delete(User entity) {
        userMap.remove(entity.getId());
    }

    @Override
    public void deleteAllById(Iterable<? extends Long> ids) {
        for (Long id : ids) {
            userMap.remove(id);
        }
    }

    @Override
    public void deleteAll(Iterable<? extends User> entities) {
        for (User entity : entities) {
            userMap.remove(entity.getId());
        }
    }

    @Override
    public void deleteAll() {
        userMap.clear();
    }

    @Override
    public Page<User> findAll(Pageable pageable) {
        List<User> results = findAll();
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), results.size());
        List<User> subList = results.subList(start, end);
        return new PageImpl<>(subList, pageable, results.size());
    }

    @Override
    public List<User> findAll(Sort sort) {
        return List.of();
    }
}
