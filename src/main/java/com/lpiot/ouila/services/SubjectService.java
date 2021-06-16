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

    public Subject addSubject(Subject subject) {
        return repository.save(subject);
    }

    public Subject getSubjectById(Long subjectId) {
        return repository.findById(subjectId).get();
    }

    public Subject updateSubject(Long id, Subject subject) {
        return repository.findById(id).map(c -> {
            c.setName(subject.getName());
            return repository.save(c);
        }).orElseGet(() -> {
            return repository.save(subject);
        });
    }

    public void deleteSubjectById(Long subjectId) {
        repository.deleteById(subjectId);
    }

    public List<Subject> getAllSubjects() {
        return repository.findAll();
    }
}
