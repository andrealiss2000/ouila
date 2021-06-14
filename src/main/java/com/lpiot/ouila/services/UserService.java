package com.lpiot.ouila.services;

import java.util.List;

import com.lpiot.ouila.domain.User;
import com.lpiot.ouila.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository repository;

    User addCourse(User user) {
        return repository.save(user);
    }

    User getUserById(Long userId) {
        return repository.findById(userId).get();
    }

    void updateUser(User user) {
        User newUser = repository.findById(user.getId()).orElseThrow();
        repository.save(newUser);
    }

    void deleteUserById(Long userId) {
        repository.deleteById(userId);
    }

    List<User> getAllUsers() {
        return repository.findAll();
    }
}
