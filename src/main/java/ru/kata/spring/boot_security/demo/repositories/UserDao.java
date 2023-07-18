package ru.kata.spring.boot_security.demo.repositories;

import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserDao {
    void addUser(User user);

    void updateUser(User user);

    void deleteUser(Integer id);

    User getUserById(Integer id);

    List<User> getAllUsers();

    User findByUsername(String username);
}
