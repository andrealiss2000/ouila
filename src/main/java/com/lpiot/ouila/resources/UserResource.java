package com.lpiot.ouila.resources;

import java.net.URI;
import java.util.List;

import com.lpiot.ouila.domain.User;
import com.lpiot.ouila.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserResource {
    @Autowired
    UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok().body(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable(value = "id") Long id) {
        try {
            User user = userService.getUserById(id);
            return ResponseEntity.ok().body(user);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    // @GetMapping("/students")
    // public ResponseEntity<User> getAllStudents() {
    // try {
    // User user = userService.getUsersOfRole(getStudentrole here);
    // return ResponseEntity.ok().body(user);
    // } catch (Exception e) {
    // return ResponseEntity.notFound().build();
    // }
    // }

    // @GetMapping("/teachers")
    // public ResponseEntity<User> getAllTeachers() {
    // try {
    // User user = userService.getUsersOfRole(get teacher role here);
    // return ResponseEntity.ok().body(user);
    // } catch (Exception e) {
    // return ResponseEntity.notFound().build();
    // }
    // }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        try {
            User newUser = userService.addUser(user);
            return ResponseEntity.created(new URI("/users/" + newUser.getId())).body(user);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    ResponseEntity<User> replaceUser(@RequestBody User newUser, @PathVariable Long id) {
        try {
            return ResponseEntity.ok().body(userService.updateUser(id, newUser));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    ResponseEntity<String> deleteUser(@PathVariable Long id) {
        try {
            userService.deleteUserById(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}