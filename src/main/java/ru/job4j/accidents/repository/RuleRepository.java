package ru.job4j.accidents.repository;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.accidents.model.Rule;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public interface RuleRepository extends CrudRepository<Rule, Integer> {
    default Collection<Rule> findAllRules() {
        List<Rule> rules = new ArrayList<>();
        findAll().forEach(rules::add);
        return rules;
    }
}