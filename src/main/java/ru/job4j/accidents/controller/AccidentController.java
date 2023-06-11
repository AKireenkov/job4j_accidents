package ru.job4j.accidents.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.service.AccidentService;

@AllArgsConstructor
@Controller
public class AccidentController {
    private final AccidentService accidents;

    @GetMapping("/createAccident")
    public String viewCreateAccident() {
        return "createAccident";
    }

    @PostMapping({"/saveAccident", "/updateAccident"})
    public String save(@ModelAttribute Accident accident) {
        accidents.create(accident);
        return "redirect:/#";
    }

    @GetMapping("/editAccident/{id}")
    public String viewEditAccident(Model model, @PathVariable int id) {
        model.addAttribute("accident", accidents.findById(id));
        return "editAccident";
    }
}
