package com.example.Caramelca.controllers;

import com.example.Caramelca.models.Role;
import com.example.Caramelca.models.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    //TODO Пофиксить редирект( "/" редиректит на индекс, так не должно быть) ps - подробности у Димы

    @GetMapping("/")
    public String home() {
        return "redirect:index";
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