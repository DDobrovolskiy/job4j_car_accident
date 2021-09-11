package ru.job4j.accident.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class IndexControl {
    @GetMapping("/")
    public String index(Model model) {
        List<String> list = List.of("Element 1", "Element 2");
        model.addAttribute("list", list);
        return "index";
    }
}
