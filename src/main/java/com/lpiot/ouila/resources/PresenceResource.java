package com.lpiot.ouila.resources;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import com.lpiot.ouila.domain.Lesson;
import com.lpiot.ouila.domain.Presence;
import com.lpiot.ouila.domain.User;
import com.lpiot.ouila.services.LessonService;
import com.lpiot.ouila.services.PresenceService;
import com.lpiot.ouila.services.UserService;

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
@RequestMapping("/presences")
public class PresenceResource {

    @Autowired
    PresenceService presenceService;
    @Autowired
    UserService userService;
    @Autowired
    LessonService lessonService;

    @GetMapping
    public ResponseEntity<List<Presence>> getAll() {
        return ResponseEntity.ok().body(presenceService.getAllPresences());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Presence> getPresenceById(@PathVariable(value = "id") Long id) {
        try {
            Presence presence = presenceService.getPresenceById(id);
            return ResponseEntity.ok().body(presence);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Presence> createPresence(@RequestBody Presence presence) {
        try {
            Optional<Lesson> lesson = lessonService.getLessonById(presence.getLesson().getId());
            Optional<User> user = userService.getUserById(presence.getStudent().getId());

            if (lesson.isPresent() && user.isPresent()) {
                Presence newPresence = presenceService.addPresence(presence);
                newPresence.setStudent(user.get());
                newPresence.setLesson(lesson.get());
                return ResponseEntity.created(new URI("/presences/" + newPresence.getId())).body(presence);
            } else {
                return ResponseEntity.badRequest().build();
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // @PostMapping("/{id}/{status}")
    // public ResponseEntity<Presence> markPresence(@PathVariable Long lessonId,
    // @PathVariable int status) {
    // try {

    // // find lesson from id

    // // check user trying to mark presence

    // // find presence with above params if exist

    // // mark present or absent with status param
    // return ResponseEntity.ok().body(presence);
    // } catch (Exception e) {
    // return ResponseEntity.badRequest().build();
    // }
    // }

    @PutMapping("/{id}")
    ResponseEntity<Presence> replacePresence(@RequestBody Presence newPresence, @PathVariable Long id) {
        try {
            return ResponseEntity.ok().body(presenceService.updatePresence(id, newPresence));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    ResponseEntity<String> deletePresence(@PathVariable Long id) {
        try {
            presenceService.deletePresenceById(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping(value = "/qrcode")
    public ResponseEntity<String> getQrCode() {
        try {
            String qrCodeStr = presenceService.generateQRCodeImage("hello");
            return ResponseEntity.ok(qrCodeStr);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
