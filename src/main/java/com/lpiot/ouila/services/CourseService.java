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

    public Course addCourse(Course course) {
        return courseRepository.save(course);
    }

    public Course getCourseById(Long courseId) {
        return courseRepository.findById(courseId).get();
    }

    public Course updateCourse(Long id, Course course) {
        return courseRepository.findById(id).map(c -> {
            c.setName(course.getName());
            c.setSpeciality(course.getSpeciality());
            return courseRepository.save(c);
        }).orElseGet(() -> {
            return courseRepository.save(course);
        });
    }

    public void deleteCourseById(Long courseId) {
        courseRepository.deleteById(courseId);
    }

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

}
