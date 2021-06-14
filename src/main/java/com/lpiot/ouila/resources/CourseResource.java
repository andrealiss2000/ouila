package com.lpiot.ouila.resources;

import java.util.List;

import com.lpiot.ouila.domain.Course;
import com.lpiot.ouila.services.CourseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/courses")
public class CourseResource {

    @Autowired
    CourseService courseService;

    @GetMapping
    List<Course> getAllCourses() {
        return courseService.getAllCourses();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Course> getCoursesById(@PathVariable(value = "id") Long courseId) {
        return ResponseEntity.ok().body(courseService.getCourseById(courseId));
    }

    @PostMapping
    public Course createCourse(@Validated @RequestBody Course course) {
        return courseService.addCourse(course);
    }

    // @PutMapping("/{id}")
    // ResponseEntity<?> replaceCourse(@RequestBody Course newCourse, @PathVariable
    // Long id) {

    // Course updatedCourse = courseService.updateCourse(newCourse); .findById(id)
    // //
    // .map(employee -> {
    // employee.setName(newEmployee.getName());
    // employee.setRole(newEmployee.getRole());
    // return repository.save(employee);
    // }) //
    // .orElseGet(() -> {
    // newEmployee.setId(id);
    // return repository.save(newEmployee);
    // });

    // EntityModel<Course> entityModel = assembler.toModel(updatedEmployee);

    // return ResponseEntity //
    // .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
    // .body(entityModel);
    // }

    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteCourse(@PathVariable Long id) {
        courseService.deleteCourseById(id);
        return ResponseEntity.noContent().build();
    }
}
