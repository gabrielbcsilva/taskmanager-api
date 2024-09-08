package io.gabriel.taskmanager.controller;

import io.gabriel.taskmanager.config.swagger.DefaultApiResponse;
import io.gabriel.taskmanager.model.dto.user.*;
import io.gabriel.taskmanager.model.entity.User;
import io.gabriel.taskmanager.service.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@DefaultApiResponse
@RequestMapping("/user")
@Tag(name = "User", description = "Operations related to users")
public class UserController {
    private final UserService userService;


    public UserController(
            UserService userService
    ) {
        this.userService = userService;
    }

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    @Operation(summary = "Get all users")
    public List<UserResponse> allUsers() {

        List<User> users = userService.list();
        List<UserResponse> userResponses = new ArrayList<>();

        for (User user : users) {

            userResponses.add(new UserResponse(user));
        }

        return userResponses;
    }

    @GetMapping("/by-id")
    @ResponseStatus(value = HttpStatus.OK)
    @Operation(summary = "Get user by id")
    public UserResponse userById(@RequestParam("userId") UUID userId) {

        User user = userService.findUserById(userId);
        return new UserResponse(user);
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    @Operation(summary = "Add a new user")
    public UserResponse addUser(@Valid @RequestBody NewUserDto userData) {

        User user = NewUserDto.transformToEntity(userData);
        User userAdded = userService.add(user);

        return new UserResponse(userAdded);
    }

    @PutMapping
    @ResponseStatus(value = HttpStatus.OK)
    @Operation(summary = "Update a specific user")
    public UserResponse updateUser(@Valid @RequestBody UpdateUserDto userData) {
        User user = userService.updateUser(userData);
        return new UserResponse(user);
    }
}
