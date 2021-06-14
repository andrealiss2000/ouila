package com.lpiot.ouila.services;

import java.util.List;

import com.lpiot.ouila.domain.Subject;
import com.lpiot.ouila.repositories.SubjectRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubjectService {
    @Autowired
    SubjectRepository repository;

    Subject addCourse(Subject subject) {
        return repository.save(subject);
    }

    Subject getSubjectById(Long subjectId) {
        return repository.findById(subjectId).get();
    }

    void updateSubject(Subject subject) {
        Subject newSubject = repository.findById(subject.getId()).orElseThrow();
        repository.save(newSubject);
    }

    void deleteSubjectById(Long subjectId) {
        repository.deleteById(subjectId);
    }

    List<Subject> getAllSubjects() {
        return repository.findAll();
    }
}
