package io.gabriel.taskmanager.user.service;

import io.gabriel.taskmanager.model.entity.User;
import io.gabriel.taskmanager.repository.inMemory.UserRepositoryInMemory;
import io.gabriel.taskmanager.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Tag("UnitTest")
@DisplayName("User service list")
public class ListUserTest {
    private UserService userService;

    @BeforeEach
    void setUp() {
        UserRepositoryInMemory userRepository = new UserRepositoryInMemory();
        userService = new UserService(userRepository);
    }

    @Test
    @DisplayName("Should be returned a empty user list")
    void emptyUserList(){
        List<User> list = userService.list();
        assertEquals(0, list.size());
    }

    @Test
    @DisplayName("Should be returned an user list")
    void userList(){
        User user = new User();
        User user2 = new User();

        user.setId(UUID.randomUUID());
        user.setName("Gabriel");

        user2.setId(UUID.randomUUID());
        user2.setName("Gabriel 2");

        userService.add(user);
        userService.add(user2);

        List<User> list = userService.list();
        assertEquals(2, list.size());
        assertTrue(list.contains(user));
        assertTrue(list.contains(user2));
    }
}
