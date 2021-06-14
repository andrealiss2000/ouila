package com.lpiot.ouila.repositories;

import com.lpiot.ouila.domain.Presence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PresenceRepository extends JpaRepository<Presence, Long> {

}
