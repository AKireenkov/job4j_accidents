package ru.job4j.accidents.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.model.AccidentType;
import ru.job4j.accidents.service.AccidentService;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Controller
public class AccidentController {
    private final AccidentService accidents;

    @GetMapping("/createAccident")
    public String viewCreateAccident(Model model) {
        List<AccidentType> types = new ArrayList<>();
        types.add(new AccidentType(1, "Две машины"));
        types.add(new AccidentType(2, "Машина и человек"));
        types.add(new AccidentType(3, "Машина и велосипед"));
        model.addAttribute("types", types);
        model.addAttribute("user", "Andrey Kireenkov");
        return "createAccident";
    }

    @PostMapping({"/saveAccident", "/updateAccident"})
    public String save(@ModelAttribute Accident accident, @RequestParam(value = "type.id") int typeId) {
        AccidentType type = accidents.findAllTypes().stream().filter(t -> t.id == typeId).findFirst().get();
        accident.setType(type);
        accidents.create(accident);
        return "redirect:/#";
    }

    @GetMapping("/editAccident/{id}")
    public String viewEditAccident(Model model, @PathVariable int id) {
        model.addAttribute("accident", accidents.findById(id));
        model.addAttribute("types", accidents.findAllTypes());
        model.addAttribute("user", "Andrey Kireenkov");
        return "editAccident";
    }
}
