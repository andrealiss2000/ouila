package com.lpiot.ouila.services;

import java.util.List;
import java.util.Optional;

import com.lpiot.ouila.domain.Role;
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

    public Optional<User> getUserById(Long userId) {
        return repository.findById(userId);
    }

    public List<User> getTeachers() {
        return repository.findByRole(Role.TEACHER);
    }

    public List<User> getStudents() {
        return repository.findByRole(Role.STUDENT);
    }

    public Optional<User> getStudentById(Long id) {
        return repository.findByRoleAndId(Role.STUDENT, id);
    }

    public Optional<User> getTeacherById(Long id) {
        return repository.findByRoleAndId(Role.TEACHER, id);
    }

    public Optional<User> updateUser(Long id, User user) {
        return repository.findById(id).map(c -> {
            c.setAddress(user.getAddress());
            c.setCardNumber(user.getCardNumber());
            c.setEmail(user.getEmail());
            c.setFirstName(user.getFirstName());
            c.setLastName(user.getLastName());
            c.setUsername(user.getUsername());
            c.setPassword(user.getPassword());
            c.setPhone(user.getPhone());
            // update course
            return repository.save(c);
        });
    }

    public void deleteUserById(Long userId) {
        repository.deleteById(userId);
    }

    public List<User> getAllUsers() {
        return repository.findAll();
    }

}
