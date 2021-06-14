package com.lpiot.ouila.services;

import java.util.List;

import com.lpiot.ouila.domain.Presence;
import com.lpiot.ouila.repositories.PresenceRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PresenceService {

    @Autowired
    PresenceRepository repository;

    Presence addCourse(Presence presence) {
        return repository.save(presence);
    }

    Presence getPresenceById(Long presenceId) {
        return repository.findById(presenceId).get();
    }

    void updatePresence(Presence presence) {
        Presence newPresence = repository.findById(presence.getId()).orElseThrow();
        repository.save(newPresence);
    }

    void deletePresenceById(Long presenceId) {
        repository.deleteById(presenceId);
    }

    List<Presence> getAllPresences() {
        return repository.findAll();
    }
}
