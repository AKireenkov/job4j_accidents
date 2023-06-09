package ru.job4j.accidents.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.Rule;
import ru.job4j.accidents.repository.RuleRepository;

import java.util.Collection;

@Service
@AllArgsConstructor
public class RuleService {

    private final RuleRepository ruleRepository;

    public Collection<Rule> findAll() {
        return ruleRepository.findAll();
    }
}
