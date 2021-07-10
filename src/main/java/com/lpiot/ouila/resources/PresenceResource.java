package com.lpiot.ouila.resources;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import com.lpiot.ouila.domain.Lesson;
import com.lpiot.ouila.domain.Presence;
import com.lpiot.ouila.domain.User;
import com.lpiot.ouila.exceptions.LessonNotFoundException;
import com.lpiot.ouila.exceptions.StudentNotFoundException;
import com.lpiot.ouila.exceptions.PresenceNotFoundException;
import com.lpiot.ouila.services.LessonService;
import com.lpiot.ouila.services.PresenceService;
import com.lpiot.ouila.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
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
        Presence presence = presenceService.getPresenceById(id).orElseThrow(() -> new PresenceNotFoundException(id));
        return ResponseEntity.ok().body(presence);
    }

    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('TEACHER')")
    @PostMapping
    public ResponseEntity<Presence> createPresence(@RequestBody Presence presence) throws URISyntaxException {

        Lesson lesson = lessonService.getLessonById(presence.getLesson().getId())
                .orElseThrow(() -> new LessonNotFoundException(presence.getLesson().getId()));
        User student = userService.getStudentById(presence.getStudent().getId())
                .orElseThrow(() -> new StudentNotFoundException(presence.getStudent().getId()));

        presence.setStudent(student);
        presence.setLesson(lesson);
        Presence newPresence = presenceService.addPresence(presence);
        return ResponseEntity.created(new URI("/presences/" + newPresence.getId())).body(presence);

    }

    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('TEACHER')")
    @PutMapping("/{id}")
    ResponseEntity<Presence> updateAttendanceStatus(@RequestParam("status") Boolean status, @PathVariable Long id) {
        presenceService.updateAttendance(id, status).orElseThrow(() -> new PresenceNotFoundException(id));
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('TEACHER')")
    @DeleteMapping("/{id}")
    ResponseEntity<String> deletePresence(@PathVariable Long id) {
        try {
            presenceService.deletePresenceById(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            throw new PresenceNotFoundException(id);
        }
    }

    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('TEACHER')")
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
