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

    public Role addRole(Role role) {
        return repository.save(role);
    }

    public Role getRoleById(Long roleId) {
        return repository.findById(roleId).get();
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
