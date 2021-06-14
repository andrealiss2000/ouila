package com.lpiot.ouila.services;

import java.util.List;

import com.lpiot.ouila.domain.Role;
import com.lpiot.ouila.repositories.RoleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    @Autowired
    RoleRepository repository;

    Role addCourse(Role role) {
        return repository.save(role);
    }

    Role getRoleById(Long roleId) {
        return repository.findById(roleId).get();
    }

    void updateRole(Role role) {
        Role newRole = repository.findById(role.getId()).orElseThrow();
        repository.save(newRole);
    }

    void deleteRoleById(Long roleId) {
        repository.deleteById(roleId);
    }

    List<Role> getAllRoles() {
        return repository.findAll();
    }
}
