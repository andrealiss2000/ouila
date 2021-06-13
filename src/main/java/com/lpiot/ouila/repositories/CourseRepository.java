package com.lpiot.ouila.repositories;

import com.lpiot.ouila.domain.Course;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {

}
