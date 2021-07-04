package com.lpiot.ouila.repositories;

import java.util.List;

import com.lpiot.ouila.domain.ERole;
import com.lpiot.ouila.domain.Role;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    List<Role> findByName(ERole role);
}
