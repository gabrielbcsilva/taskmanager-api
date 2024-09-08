package io.gabriel.taskmanager.user.service;

import io.gabriel.taskmanager.exception.user.UserNotFoundException;
import io.gabriel.taskmanager.model.dto.user.UpdateUserDto;
import io.gabriel.taskmanager.model.entity.User;
import io.gabriel.taskmanager.repository.inMemory.UserRepositoryInMemory;
import io.gabriel.taskmanager.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@Tag("UnitTest")
@DisplayName("UserServiceUpdate")
public class UpdateUserTest {
    private UserService userService;

    @BeforeEach
    void setUp() {
        UserRepositoryInMemory userRepository = new UserRepositoryInMemory();
        userService = new UserService(userRepository);
    }

    @Test
    @DisplayName("Should be updated an user")
    void updateUser(){
        User userToAdd = new User();

        userToAdd.setId(UUID.randomUUID());
        userToAdd.setName("Gabriel");

        User existingUser = userService.add(userToAdd);

        UpdateUserDto userToUpdate = new UpdateUserDto();
        userToUpdate.setId(existingUser.getId());
        userToUpdate.setName(existingUser.getName());

        userToUpdate.setName("Gabriel Editado");
        User userNameUpdated = userService.updateUser(userToUpdate);
        assertEquals(userToUpdate.getName(), userNameUpdated.getName());

        User userProfileUpdated = userService.updateUser(userToUpdate);
        assertEquals(userToUpdate.getName(), userProfileUpdated.getName());
    }

    @Test
    @DisplayName("Should not update a user when id not exist")
    void updateUserWhenIdNotExist() {
        UpdateUserDto userData = new UpdateUserDto();

        userData.setId(UUID.randomUUID());
        userData.setName("Gabriel");

        assertThrows(UserNotFoundException.class, () -> {
            userService.updateUser(userData);
        });
    }
}
