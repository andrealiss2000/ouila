package com.lpiot.ouila.resources;

import java.net.URI;
import java.util.List;

import com.lpiot.ouila.domain.Justification;
import com.lpiot.ouila.services.JustificationService;

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
@RequestMapping("/justifications")
public class JustificationResource {
    @Autowired
    JustificationService justificationService;

    @GetMapping
    public ResponseEntity<List<Justification>> getAllJustifications() {
        return ResponseEntity.ok().body(justificationService.getAllJustifications());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Justification> getJustificationById(@PathVariable(value = "id") Long id) {
        try {
            Justification justification = justificationService.getJustificationById(id);
            return ResponseEntity.ok().body(justification);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Justification> createJustification(@RequestBody Justification justification) {
        try {
            Justification newJustification = justificationService.addJustification(justification);
            return ResponseEntity.created(new URI("/justifications/" + newJustification.getId())).body(justification);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    ResponseEntity<Justification> replaceJustification(@RequestBody Justification newJustification,
            @PathVariable Long id) {
        try {
            return ResponseEntity.ok().body(justificationService.updateJustification(id, newJustification));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    ResponseEntity<String> deleteJustification(@PathVariable Long id) {
        try {
            justificationService.deleteJustificationById(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}