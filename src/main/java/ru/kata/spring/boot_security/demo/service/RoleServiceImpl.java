package ru.kata.spring.boot_security.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.repositories.RoleDao;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    private final RoleDao roleDao;

    public RoleServiceImpl(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    public List<Role> getAllRoles() {
        return roleDao.getAllRoles();
    }

    @Override
    public Role getRole(Integer id) {
        return roleDao.getRole(id);
    }

    @Override
    public void addRole(Role role) {
        roleDao.addRole(role);
    }

    @Override
    public void deleteRole(Integer id) {
        roleDao.deleteRole(id);
    }

    @Override
    public void editRole(Role role) {
        roleDao.editRole(role);
    }

    @Override
    public List<Role> getRolesByIds(List<Integer> roleIds) {
        List<Role> roles = new ArrayList<>();
        for (Integer id : roleIds) {
            Role role = getRole(id);
            if (role != null) {
                roles.add(role);
            }
        }
        return roles;
    }
}
