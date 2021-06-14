package com.lpiot.ouila.services;

import java.util.List;

import com.lpiot.ouila.domain.Lesson;
import com.lpiot.ouila.repositories.LessonRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LessonService {

    @Autowired
    LessonRepository repository;

    Lesson addCourse(Lesson lesson) {
        return repository.save(lesson);
    }

    Lesson getLessonById(Long lessonId) {
        return repository.findById(lessonId).get();
    }

    void updateLesson(Lesson lesson) {
        Lesson newLesson = repository.findById(lesson.getId()).orElseThrow();
        repository.save(newLesson);
    }

    void deleteLessonById(Long lessonId) {
        repository.deleteById(lessonId);
    }

    List<Lesson> getAllLessons() {
        return repository.findAll();
    }
}
