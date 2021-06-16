package com.lpiot.ouila.resources;

import java.net.URI;
import java.util.List;

import com.lpiot.ouila.domain.Lesson;
import com.lpiot.ouila.services.LessonService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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
            URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                    .buildAndExpand(newLesson.getId()).toUri();
            return ResponseEntity.created(location).body(lesson);
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
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteLesson(@PathVariable Long id) {
        lessonService.deleteLessonById(id);
    }
}