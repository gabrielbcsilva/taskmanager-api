package io.gabriel.taskmanager.user.service;

import io.gabriel.taskmanager.exception.NotPerformedException;
import io.gabriel.taskmanager.exception.user.UserConflictException;
import io.gabriel.taskmanager.model.entity.User;
import io.gabriel.taskmanager.repository.inMemory.UserRepositoryInMemory;
import io.gabriel.taskmanager.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@Tag("UnitTest")
@DisplayName("User service create")
public class CreateUserTest {
    private UserService userService;

    @BeforeEach
    void setUp() {
        UserRepositoryInMemory userRepository = new UserRepositoryInMemory();
        userService = new UserService(userRepository);
    }

    @Test
    @DisplayName("Should be added a new user")
    void addUser(){
        User user = new User();

        user.setId(UUID.randomUUID());
        user.setName("Gabriel");

        User userAdded = userService.add(user);

        assertEquals(user.getName(), userAdded.getName());
    }

    @Test
    @DisplayName("Should not add a new user when userId already exist")
    void addUserWhenIdAlreadyExist() {
        UUID sameId = UUID.randomUUID();
        User user1 = new User();
        User user2 = new User();

        user1.setId(sameId);
        user1.setName("Gabriel");

        user2.setId(sameId);
        user2.setName("Gabriel 2");

        userService.add(user1);

        assertThrows(NotPerformedException.class, () -> {
            userService.add(user2);
        });
    }


}
