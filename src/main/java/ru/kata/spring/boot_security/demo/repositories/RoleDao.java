package ru.kata.spring.boot_security.demo.repositories;

import ru.kata.spring.boot_security.demo.model.Role;

import java.util.List;

public interface RoleDao {
    List<Role> getAllRoles();

    Role getRole(Integer id);

    void addRole(Role role);

    void deleteRole(Integer id);

    void editRole(Role role);
}
