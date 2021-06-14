package com.lpiot.ouila.services;

import java.util.List;

import com.lpiot.ouila.domain.Course;
import com.lpiot.ouila.repositories.CourseRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseService {

    @Autowired
    CourseRepository courseRepository;

    Course addCourse(Course course) {
        return courseRepository.save(course);
    }

    Course getCourseById(Long courseId) {
        return courseRepository.findById(courseId).get();
    }

    void updateCourse(Course course) {
        Course newCourse = courseRepository.findById(course.getId()).orElseThrow();
        courseRepository.save(newCourse);
    }

    void deleteCourseById(Long courseId) {
        courseRepository.deleteById(courseId);
    }

    List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

}
