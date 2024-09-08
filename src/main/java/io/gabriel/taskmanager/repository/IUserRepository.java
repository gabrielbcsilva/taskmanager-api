package io.gabriel.taskmanager.repository;

import io.gabriel.taskmanager.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {
    Optional<User> findById(UUID id);

    @Query("SELECT u FROM User u ")
    List<User> listAll();
}
