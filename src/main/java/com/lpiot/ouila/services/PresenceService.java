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

    public Presence addPresence(Presence presence) {
        return repository.save(presence);
    }

    public Presence getPresenceById(Long presenceId) {
        return repository.findById(presenceId).get();
    }

    public Presence updatePresence(Long id, Presence presence) {
        return repository.findById(id).map(c -> {
            c.setStatus(presence.getStatus());
            return repository.save(c);
        }).orElseGet(() -> {
            return repository.save(presence);
        });
    }

    public void deletePresenceById(Long presenceId) {
        repository.deleteById(presenceId);
    }

    public List<Presence> getAllPresences() {
        return repository.findAll();
    }
}
