package ru.job4j.accidents.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor
public class IndexController {

    @GetMapping("/")
    public String getAllAccidents(Model model) {
        model.addAttribute("user", "Andrey Kireenkov");
        return "index";
    }
}
