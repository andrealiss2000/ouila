package com.lpiot.ouila.resources;

import java.net.URI;
import java.util.List;

import com.lpiot.ouila.domain.Lesson;
import com.lpiot.ouila.services.LessonService;

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
@RequestMapping("/lessons")
public class LessonResource {
    @Autowired
    LessonService lessonService;

    @GetMapping
    public ResponseEntity<List<Lesson>> getAllLessons() {
        return ResponseEntity.ok().body(lessonService.getAllLessons());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Lesson> getLessonById(@PathVariable(value = "id") Long id) {
        try {
            Lesson lesson = lessonService.getLessonById(id);
            return ResponseEntity.ok().body(lesson);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Lesson> createLesson(@RequestBody Lesson lesson) {
        try {
            Lesson newLesson = lessonService.addLesson(lesson);
            return ResponseEntity.created(new URI("/lessons/" + newLesson.getId())).body(lesson);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    ResponseEntity<Lesson> replaceLesson(@RequestBody Lesson newLesson, @PathVariable Long id) {
        try {
            return ResponseEntity.ok().body(lessonService.updateLesson(id, newLesson));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    ResponseEntity<String> deleteLesson(@PathVariable Long id) {
        try {
            lessonService.deleteLessonById(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}