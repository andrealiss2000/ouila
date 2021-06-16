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

    public User addUser(User user) {
        return repository.save(user);
    }

    public User getUserById(Long userId) {
        return repository.findById(userId).get();
    }

    public User updateUser(Long id, User user) {
        return repository.findById(id).map(c -> {
            c.setAddress(user.getAddress());
            c.setCardNumber(user.getCardNumber());
            c.setEmail(user.getEmail());
            c.setFirstName(user.getFirstName());
            c.setLastName(user.getLastName());
            c.setUsername(user.getUsername());
            c.setPassword(user.getPassword());
            c.setPhone(user.getPhone());
            return repository.save(c);
        }).orElseGet(() -> {
            return repository.save(user);
        });
    }

    public void deleteUserById(Long userId) {
        repository.deleteById(userId);
    }

    public List<User> getAllUsers() {
        return repository.findAll();
    }

}
