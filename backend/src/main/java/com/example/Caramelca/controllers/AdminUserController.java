package com.example.Caramelca.controllers;

import com.example.Caramelca.models.Auth.User;
import com.example.Caramelca.services.AdminUserService;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
//TODO @RequestMapping("/user")
//@PreAuthorize("hasAuthority('ADMIN')")
public class AdminUserController {

    private final AdminUserService adminUserService;

    public AdminUserController(AdminUserService adminUserService) {
        this.adminUserService = adminUserService;
    }

    @GetMapping("/user")
    public String user(Model model) {
        Iterable<User> users = adminUserService.usersGetAll();
        Iterable<User> allUsers = adminUserService.usersGetAll();

        model.addAttribute("users", users);
        model.addAttribute("Allusers", allUsers);

        return "user";
    }

    @GetMapping("/user/{id}")
    public String userOne(@PathVariable(value = "id") Long id,
                          Model model) {
        User user = adminUserService.userById(id);

        model.addAttribute("user", user);

        return "user-desc";
    }

    @GetMapping("/user/filter")
    public String userFilter(@RequestParam(required = false) User user,
                             Model model) {
        Iterable<User> users = adminUserService.usersGetAll();
        Iterable<User> filteredUsers = adminUserService.userFiltred(user);

        model.addAttribute("users", filteredUsers);
        model.addAttribute("Allusers", users);

        return "user";
    }
}
