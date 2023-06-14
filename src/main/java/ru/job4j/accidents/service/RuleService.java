package ru.job4j.accidents.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.Rule;
import ru.job4j.accidents.repository.RuleMem;

import java.util.Collection;

@Service
@AllArgsConstructor
public class RuleService {

    private RuleMem ruleMem;

    public Collection<Rule> findAll() {
        return ruleMem.findAll();
    }
}
