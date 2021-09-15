package ru.job4j.accident.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.job4j.accident.models.User;
import ru.job4j.accident.services.UserServices;

@Controller
public class RegControl {

    private final UserServices userServices;

    public RegControl(UserServices userServices) {
        this.userServices = userServices;
    }

    @PostMapping("/reg")
    public String save(@ModelAttribute User user) {
        if (userServices.registration(user)) {
            return "redirect:/login";
        } else {
            return reg();
        }
    }

    @GetMapping("/reg")
    public String reg() {
        return "reg";
    }
}
