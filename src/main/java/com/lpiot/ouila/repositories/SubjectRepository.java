package com.lpiot.ouila.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lpiot.ouila.domain.Subject;

public interface SubjectRepository extends JpaRepository<Subject, Long> {
}
