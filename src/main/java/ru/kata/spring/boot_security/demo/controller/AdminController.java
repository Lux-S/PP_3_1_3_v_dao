package ru.kata.spring.boot_security.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;

    private final RoleService roleService;

    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping
    public String adminPage(Model model) {
        model.addAttribute("users", userService.getUsersList());
        return "admin";
    }

    @GetMapping("/{id}/page")
    public String getUserById(Model model, @PathVariable("id") Integer id) {
        model.addAttribute("user", userService.getUser(id));
        return "page";
    }

    @GetMapping("/user_add")
    public String createNewUser(Model model, @ModelAttribute("user") User user) {
        model.addAttribute("rolesOfUser", roleService.getAllRoles());
        return "user_add";
    }

    @PostMapping
    public String addUser(@ModelAttribute("user") User user, @RequestParam("roleIds") List<Integer> roleIds) {
        List<Role> roles = roleService.getRolesByIds(roleIds);
        user.setRoles(roles);
        userService.addUser(user);
        return "redirect:/admin";
    }

    @PatchMapping("/{id}")
    public String updateUser(@ModelAttribute("user") User user, @RequestParam("roleIds") List<Integer> roleIds) {
        List<Role> roles = roleService.getRolesByIds(roleIds);
        user.setRoles(roles);
        userService.editUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/{id}/edit")
    public String editUser(Model model, @PathVariable("id") Integer id) {
        model.addAttribute("user", userService.getUser(id));
        model.addAttribute("rolesOfUser", roleService.getAllRoles());
        return "user_edit";
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable("id") Integer id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }
}
