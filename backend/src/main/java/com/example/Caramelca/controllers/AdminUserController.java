package com.example.Caramelca.controllers;

import com.example.Caramelca.models.Role;
import com.example.Caramelca.models.User;
import com.example.Caramelca.repositories.UserRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminUserController {

    private final UserRepository userRepository;

    public AdminUserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/user")
    public String user(Model model) {
        Iterable<User> users = userRepository.findByRoles(Role.USER);
        Iterable<User> Allusers = userRepository.findByRoles(Role.USER);
        model.addAttribute("users", users);
        model.addAttribute("Allusers", Allusers);
        return "user";
    }

    @GetMapping("/user/{id}")
    public String userOne(@PathVariable(value = "id") Long id,
                          Model model) {
        User user = userRepository.findById(id).orElseThrow();
        model.addAttribute("user", user);
        return "user-desc";
    }

    @GetMapping("/user/filter")
    public String userFilter(@RequestParam(required = false) User user,
                             Model model) {
        Iterable<User> users = userRepository.findByRoles(Role.USER);
        Iterable<User> Allusers = userRepository.findByRoles(Role.USER);

        if (user != null) {
            users = userRepository.findByNumber(user.getNumber());
        }

        model.addAttribute("users", users);
        model.addAttribute("Allusers", Allusers);

        return "user";
    }

    @PostMapping("/user/{id}/edit")
    public String userEdit(@PathVariable(value = "id") Long id,
                           @RequestParam String description) {
        User user = userRepository.findById(id).orElseThrow();
        user.setDescription(description);
        userRepository.save(user);
        return "redirect:/user";
    }
}
