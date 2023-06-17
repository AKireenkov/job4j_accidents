package ru.job4j.accidents.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.Rule;
import ru.job4j.accidents.repository.RuleJdbc;

import java.util.Collection;

@Service
@AllArgsConstructor
public class RuleService {

    private final RuleJdbc ruleRepository;

    public Collection<Rule> findAll() {
        return ruleRepository.findAll();
    }
}
