package io.gabriel.taskmanager.service;

import io.gabriel.taskmanager.exception.NotPerformedException;
import io.gabriel.taskmanager.exception.user.UserNotFoundException;
import io.gabriel.taskmanager.model.dto.user.UpdateUserDto;
import io.gabriel.taskmanager.model.entity.User;
import io.gabriel.taskmanager.repository.IUserRepository;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService{

    private final IUserRepository userRepository;



    public UserService(IUserRepository userRepository) { this.userRepository = userRepository; }

    public User findUserById(UUID userId) {
        return userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
    }

     public List<User> list() {
        return userRepository.listAll();
    }

    public User add(User newUser) {
        boolean findUserById = userRepository.findById(newUser.getId()).isPresent();

        if (findUserById) throw new NotPerformedException();

        try {
            return userRepository.save(newUser);
        } catch (DataAccessException exception) {
            throw new NotPerformedException();
        }
    }


    public User updateUser(UpdateUserDto userUpdated) {
        User user = userRepository.findById(userUpdated.getId())
            .orElseThrow(UserNotFoundException::new);

        user.setName(userUpdated.getName());

        try {
            userRepository.save(user);
        } catch (DataAccessException exception) {
            throw new NotPerformedException();
        }

        return user;
    }



}
