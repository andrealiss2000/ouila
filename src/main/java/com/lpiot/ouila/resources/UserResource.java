package com.lpiot.ouila.resources;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import com.lpiot.ouila.domain.Course;
import com.lpiot.ouila.domain.Presence;
import com.lpiot.ouila.domain.User;
import com.lpiot.ouila.exceptions.CourseNotFoundException;
import com.lpiot.ouila.exceptions.UserNotFoundException;
import com.lpiot.ouila.services.CourseService;
import com.lpiot.ouila.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/users")
public class UserResource {
    @Autowired
    UserService userService;

    @Autowired
    CourseService courseService;

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok().body(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable(value = "id") Long id) {
        User user = userService.getUserById(id).orElseThrow(() -> new UserNotFoundException(id));
        return ResponseEntity.ok().body(user);
    }

    @GetMapping("/{id}/presences")
    public ResponseEntity<List<Presence>> getUserPresencesById(@PathVariable(value = "id") Long id) {
        User user = userService.getUserById(id).orElseThrow(() -> new UserNotFoundException(id));
        return ResponseEntity.ok().body(user.getPresences());
    }

    @GetMapping("/students")
    public ResponseEntity<List<User>> getAllStudents() {
        return ResponseEntity.ok().body(userService.getStudents());
    }

    @GetMapping("/teachers")
    public ResponseEntity<List<User>> getAllTeachers() {
        return ResponseEntity.ok().body(userService.getTeachers());
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) throws URISyntaxException {
        Course course = courseService.getCourseById(user.getCourse().getId())
                .orElseThrow(() -> new CourseNotFoundException(user.getCourse().getId()));

        user.setCourse(course);
        User newUser = userService.addUser(user);
        return ResponseEntity.created(new URI("/users/" + newUser.getId())).body(user);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{id}")
    ResponseEntity<User> replaceUser(@RequestBody User newUser, @PathVariable Long id) {
        userService.updateUser(id, newUser).orElseThrow(() -> new UserNotFoundException(id));
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    ResponseEntity<String> deleteUser(@PathVariable Long id) {
        try {
            userService.deleteUserById(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            throw new UserNotFoundException(id);
        }
    }
}