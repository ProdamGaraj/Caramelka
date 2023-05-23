package com.example.Caramelca.controllers;

import com.example.Caramelca.models.Role;
import com.example.Caramelca.models.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String home() {
        return "greeting";
    }
    @GetMapping("/index")
    public String index(@AuthenticationPrincipal User user,
                        Model model) {
        if (user != null && user.getRoles().contains(Role.ADMIN)) {
            model.addAttribute("user", user);
        }
        return "index";
    }
}