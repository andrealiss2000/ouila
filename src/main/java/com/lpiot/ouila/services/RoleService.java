package com.lpiot.ouila.services;

import java.util.List;
import java.util.Optional;

import com.lpiot.ouila.domain.ERole;
import com.lpiot.ouila.domain.Role;
import com.lpiot.ouila.repositories.RoleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    @Autowired
    RoleRepository repository;

    public Role addRole(Role role) {
        return repository.save(role);
    }

    public Optional<Role> getRoleById(Long roleId) {
        return repository.findById(roleId);
    }

    public List<Role> getRoleByName(ERole roleName) {
        return repository.findByName(roleName);
    }

    public Role updateRole(Long id, Role role) {
        return repository.findById(id).map(c -> {
            c.setName(role.getName());
            return repository.save(c);
        }).orElseGet(() -> {
            return repository.save(role);
        });
    }

    public void deleteRoleById(Long roleId) {
        repository.deleteById(roleId);
    }

    public List<Role> getAllRoles() {
        return repository.findAll();
    }
}
