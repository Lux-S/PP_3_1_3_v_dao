package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.model.Role;

import java.util.List;

public interface RoleService {
    List<Role> getAllRoles();

    Role getRole(Integer id);

    void addRole(Role role);

    void deleteRole(Integer id);

    void editRole(Role role);

    List<Role> getRolesByIds(List<Integer> roleIds);
}

