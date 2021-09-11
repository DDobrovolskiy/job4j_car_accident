package ru.job4j.accident.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.job4j.accident.models.Accident;
import ru.job4j.accident.services.AccidentService;

@Controller
@Slf4j
public class AccidentControl {
    private final AccidentService accidentService;

    public AccidentControl(AccidentService accidentService) {
        this.accidentService = accidentService;
    }

    @GetMapping("/edit")
    public String edit(@ModelAttribute Accident accident, Model model) {
        log.debug("Id accident on edit: {}", accident.getId());
        if (accident.getId() == null) {
            return "redirect:/";
        } else {
            model.addAttribute("accident", accidentService.get(accident.getId()));
            return "accident/edit";
        }
    }

    @GetMapping("/create")
    public String create() {
        return "accident/create";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Accident accident) {
        accidentService.add(accident);
        return "redirect:/";
    }
}
