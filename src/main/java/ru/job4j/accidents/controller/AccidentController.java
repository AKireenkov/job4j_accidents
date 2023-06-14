package ru.job4j.accidents.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.service.AccidentService;
import ru.job4j.accidents.service.RuleService;
import ru.job4j.accidents.service.TypeService;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@AllArgsConstructor
@Controller
public class AccidentController {
    private final AccidentService accidents;
    private final TypeService typeService;
    private final RuleService ruleService;

    @GetMapping("/createAccident")
    public String viewCreateAccident(Model model) {
        model.addAttribute("rules", ruleService.findAll());
        model.addAttribute("types", typeService.findAll());
        model.addAttribute("user", "Andrey Kireenkov");
        return "createAccident";
    }

    @PostMapping("/saveAccident")
    public String save(@ModelAttribute Accident accident,
                       @RequestParam(value = "type.id") int typeId,
                       HttpServletRequest req) {
        String[] ids = req.getParameterValues("rIds");
        accidents.create(accident, typeId);
        return "redirect:/#";
    }

    @PostMapping("/updateAccident")
    public String update(@ModelAttribute Accident accident, @RequestParam(value = "type.id") int typeId) {
        accidents.update(accident, typeId);
        return "redirect:/#";
    }

    @GetMapping("/editAccident/{id}")
    public String viewEditAccident(Model model, @PathVariable int id) {
        Optional<Accident> accident = accidents.findById(id);
        if (accident.isEmpty()) {
            model.addAttribute("message", "Не удалось найти инцедент");
            return "errors/404";
        }
        model.addAttribute("accident", accident.get());
        model.addAttribute("types", typeService.findAll());
        model.addAttribute("user", "Andrey Kireenkov");
        return "editAccident";
    }
}
