package com.lpiot.ouila.resources;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import com.lpiot.ouila.domain.Course;
import com.lpiot.ouila.services.CourseService;

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
@RequestMapping("/courses")
public class CourseResource {

    @Autowired
    CourseService courseService;

    @GetMapping
    public ResponseEntity<List<Course>> getAllCourses() {
        return ResponseEntity.ok().body(courseService.getAllCourses());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Course> getCoursesById(@PathVariable(value = "id") Long courseId) {
        try {
            Optional<Course> course = courseService.getCourseById(courseId);
            if (course.isPresent()) {
                return ResponseEntity.ok().body(course.get());
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public ResponseEntity<Course> createCourse(@RequestBody Course course) {
        try {
            Course newCourse = courseService.addCourse(course);
            return ResponseEntity.created(new URI("/courses/" + newCourse.getId())).body(course);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{id}")
    ResponseEntity<Course> replaceCourse(@RequestBody Course newCourse, @PathVariable Long id) {
        try {
            return ResponseEntity.ok().body(courseService.updateCourse(id, newCourse));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    ResponseEntity<String> deleteCourse(@PathVariable Long id) {
        try {
            courseService.deleteCourseById(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
