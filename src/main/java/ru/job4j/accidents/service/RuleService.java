package ru.job4j.accidents.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.Rule;
import ru.job4j.accidents.repository.RuleRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@AllArgsConstructor
public class RuleService {

    private final RuleRepository ruleRepository;

    public Collection<Rule> findAll() {
        List<Rule> rules = new ArrayList<>();
        ruleRepository.findAll().forEach(rules::add);
        return rules;
    }
}
