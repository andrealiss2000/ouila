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

    public Lesson addLesson(Lesson lesson) {
        return repository.save(lesson);
    }

    public Lesson getLessonById(Long lessonId) {
        return repository.findById(lessonId).get();
    }

    public Lesson updateLesson(Long id, Lesson lesson) {
        return repository.findById(id).map(c -> {
            c.setStartDateTime(lesson.getStartDateTime());
            c.setEndDateTime(lesson.getEndDateTime());
            return repository.save(c);
        }).orElseGet(() -> {
            return repository.save(lesson);
        });
    }

    public void deleteLessonById(Long lessonId) {
        repository.deleteById(lessonId);
    }

    public List<Lesson> getAllLessons() {
        return repository.findAll();
    }
}
