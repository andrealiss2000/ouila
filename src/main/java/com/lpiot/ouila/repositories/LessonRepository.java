package com.lpiot.ouila.repositories;

import com.lpiot.ouila.domain.Lesson;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LessonRepository extends JpaRepository<Lesson, Long> {

}
