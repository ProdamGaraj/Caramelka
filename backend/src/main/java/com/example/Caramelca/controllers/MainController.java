package com.example.Caramelca.controllers;

import com.example.Caramelca.models.Admin.Role;
import com.example.Caramelca.models.Auth.User;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

    //TODO УДАЛИТЬ НАХУЙ ЭТО ГОВНО ЁБАНОЕ БЛЕАТЬ!

    @GetMapping("/")
    public String home() {
        return "redirect:index";
    }
    @GetMapping("/index")
//    public String index(@AuthenticationPrincipal User user,
    public String index(@RequestParam User user,
                        Model model) {
        if (user != null && user.getRoles().contains(Role.ADMIN)) {
            model.addAttribute("user", user);
        }
        return "index";
    }
}