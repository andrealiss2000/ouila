package com.lpiot.ouila.repositories;

import java.util.List;
import java.util.Optional;

import com.lpiot.ouila.domain.Role;
import com.lpiot.ouila.domain.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByRole(Role role);

    Optional<User> findByRoleAndId(Role role, Long id);

    Optional<User> findByUsername(String username);
}
