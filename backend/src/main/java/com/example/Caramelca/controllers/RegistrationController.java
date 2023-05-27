package com.example.Caramelca.controllers;

import com.example.Caramelca.models.Auth.User;
import com.example.Caramelca.services.RegistrationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
// TODO: ебануть такое @RequestMapping("/registration")
public class RegistrationController {

    private final RegistrationService registrationService;

    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(User user,
                          Model model) {
        if (registrationService.checkUserByUsernameInDB(user.getUsername())) {
            model.addAttribute("message", "Такой логин уже зарегистрирован");
            return "registration";
        }

        registrationService.addRoleAndSaveUser(user);
        return "redirect:/login";
    }
}
