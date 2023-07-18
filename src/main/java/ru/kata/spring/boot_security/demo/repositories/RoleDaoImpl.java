package ru.kata.spring.boot_security.demo.repositories;

import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class RoleDaoImpl implements RoleDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Role> getAllRoles() {
        return entityManager.createQuery("SELECT r FROM Role r", Role.class)
                .getResultList();
    }

    @Override
    public Role getRole(Integer id) {
        return entityManager.find(Role.class, id);
    }

    @Override
    public void addRole(Role role) {
        entityManager.persist(role);
    }

    @Override
    public void deleteRole(Integer id) {
        Role role = entityManager.find(Role.class, id);
        if (role != null) {
            entityManager.remove(role);
        }
    }

    @Override
    public void editRole(Role role) {
        entityManager.merge(role);
    }
}
