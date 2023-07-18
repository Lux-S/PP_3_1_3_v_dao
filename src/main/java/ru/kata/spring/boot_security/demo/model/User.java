package ru.kata.spring.boot_security.demo.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Byte getAge() {
        return age;
    }

    public void setAge(Byte age) {
        this.age = age;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Collection<Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }

    @Column(name = "surname")
    private String surname;

    @Column(name = "age")
    private Byte age;

    @Column(unique = true, name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Collection<Role> roles;

    // получаем роли для юзера
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public List<Integer> getRoleIds() {
        return roles.stream()
                .map(Role::getId)
                .collect(Collectors.toList());
    }

    // проверяем, действительный ли аккаунт
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // проверяем, не заблокирован ли аккаунт
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    // проверяем, действительный ли пароль
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // проверяем, работает ли аккаунт
    @Override
    public boolean isEnabled() {
        return true;
    }
}
