package com.lpiot.ouila.repositories;

import java.util.List;

import com.lpiot.ouila.domain.Role;
import com.lpiot.ouila.domain.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByRole(Role role);
}
