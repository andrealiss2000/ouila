package com.lpiot.ouila.repositories;

import java.util.List;

import com.lpiot.ouila.domain.ERole;
import com.lpiot.ouila.domain.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findAllByRole(ERole role);
}