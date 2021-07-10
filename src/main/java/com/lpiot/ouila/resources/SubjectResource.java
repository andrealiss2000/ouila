package com.lpiot.ouila.resources;

import java.net.URI;
import java.util.List;

import com.lpiot.ouila.domain.Subject;
import com.lpiot.ouila.services.SubjectService;

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
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/subjects")
public class SubjectResource {
    @Autowired
    SubjectService subjectService;

    @GetMapping
    public ResponseEntity<List<Subject>> getAllSubjects() {
        return ResponseEntity.ok().body(subjectService.getAllSubjects());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Subject> getSubjectById(@PathVariable(value = "id") Long id) {
        try {
            Subject subject = subjectService.getSubjectById(id);
            return ResponseEntity.ok().body(subject);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public ResponseEntity<Subject> createSubject(@RequestBody Subject subject) {
        try {
            Subject newSubject = subjectService.addSubject(subject);
            return ResponseEntity.created(new URI("/subjects/" + newSubject.getId())).body(subject);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{id}")
    ResponseEntity<Subject> replaceSubject(@RequestBody Subject newSubject, @PathVariable Long id) {
        try {
            return ResponseEntity.ok().body(subjectService.updateSubject(id, newSubject));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    ResponseEntity<String> deleteSubject(@PathVariable Long id) {
        try {
            subjectService.deleteSubjectById(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}