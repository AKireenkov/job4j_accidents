package ru.job4j.accidents.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Rule;

import java.util.List;

@Repository
public class RuleMem {

    List<Rule> rules = List.of(
            new Rule(1, "Статья. 1"),
            new Rule(2, "Статья. 2"),
            new Rule(3, "Статья. 3")
    );

    public List<Rule> findAllRules() {
        return rules;
    }
}
