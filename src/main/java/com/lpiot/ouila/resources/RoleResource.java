package com.lpiot.ouila.resources;

import java.net.URI;
import java.util.List;

import com.lpiot.ouila.domain.Role;
import com.lpiot.ouila.services.RoleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/roles")
public class RoleResource {
    @Autowired
    RoleService roleService;

    @GetMapping
    public ResponseEntity<List<Role>> getAllRoles() {
        return ResponseEntity.ok().body(roleService.getAllRoles());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Role> getRoleById(@PathVariable(value = "id") Long id) {
        try {
            Role role = roleService.getRoleById(id);
            return ResponseEntity.ok().body(role);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Role> createRole(@RequestBody Role role) {
        try {
            Role newRole = roleService.addRole(role);
            return ResponseEntity.created(new URI("/roles/" + newRole.getId())).body(role);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    ResponseEntity<Role> replaceRole(@RequestBody Role newRole, @PathVariable Long id) {
        try {
            return ResponseEntity.ok().body(roleService.updateRole(id, newRole));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    ResponseEntity<String> deleteRole(@PathVariable Long id) {
        try {
            roleService.deleteRoleById(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}