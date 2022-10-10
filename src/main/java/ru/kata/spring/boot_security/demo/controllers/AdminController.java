package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import java.security.Principal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;
    private final RoleRepository roleRepository;

    @Autowired
    public AdminController(UserService userService, RoleRepository roleRepository) {
        this.userService = userService;
        this.roleRepository = roleRepository;
    }

    @GetMapping("{id}")
    public String getAdmin(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userService.getUser(id));
        return "redirect:/admin/users";
    }

    @GetMapping("users")
    public String getTableUsers(Model model, Principal principal) {
        List<User> users = userService.findAll();
        model.addAttribute("user", userService.findByUsername(principal.getName()));
        model.addAttribute("users", users);
        model.addAttribute("newUser", new User());
        model.addAttribute("role", new Role());
        return "users";
    }

    @GetMapping("add")
    public String addUserTable(User user) {
        return "add";
    }

    @PostMapping("add")
    public String addUser(@ModelAttribute("newUser") User user,
                          @ModelAttribute("role") String role) {
        Set<Role> roles = new HashSet<>();
        roles.add(roleRepository.findRoleByRole(role));
        user.setRoles(roles);
        userService.saveUser(user);
        return "redirect:/admin/users";
    }

    @GetMapping("delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteById(id);
        return "redirect:/admin/users";
    }

    @GetMapping("update/{id}")
    public String updateUserTable(@PathVariable("id") Long id, Model model) {
        User user = userService.findById(id);
        model.addAttribute("user", user);
        return "update";
    }

    @PostMapping("update/{id}")
    public String updateUser(@ModelAttribute("updateUser") User user,
                             @ModelAttribute("role") String role) {
        Set<Role> roles = new HashSet<>();
        roles.add(roleRepository.findRoleByRole(role));
        user.setRoles(roles);
        userService.saveUser(user);
        return "redirect:/admin/users";
    }
}
