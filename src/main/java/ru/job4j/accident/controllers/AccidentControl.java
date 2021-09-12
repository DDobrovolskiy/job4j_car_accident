package ru.job4j.accident.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.accident.models.Accident;
import ru.job4j.accident.models.Rule;
import ru.job4j.accident.services.AccidentService;

import javax.servlet.http.HttpServletRequest;

@Controller
@Slf4j
public class AccidentControl {
    private final AccidentService accidentService;

    public AccidentControl(AccidentService accidentService) {
        this.accidentService = accidentService;
    }

    @GetMapping("/edit")
    public String edit(@RequestParam("id") int id, Model model) {
        log.debug("Id accident on edit: {}", id);
        model.addAttribute("types", accidentService.getTypes());
        model.addAttribute("accident", accidentService.getAccident(id));
        log.debug(accidentService.getAccident(id).toString());
        model.addAttribute("rules", accidentService.getRules());
        for (Rule rule : accidentService.getRules()) {
            log.debug(accidentService.getAccident(id).getRules().contains(rule) ? "selected" : "");
        }
        return "accident/edit";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("types", accidentService.getTypes());
        model.addAttribute("rules", accidentService.getRules());
        return "accident/create";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Accident accident, HttpServletRequest req) {
        log.debug(accident.toString());
        String[] ids = req.getParameterValues("rIds");
        accidentService.addAccident(accident, ids);
        return "redirect:/";
    }
}
