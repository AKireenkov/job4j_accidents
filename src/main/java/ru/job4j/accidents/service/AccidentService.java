package ru.job4j.accidents.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.model.Rule;
import ru.job4j.accidents.repository.AccidentJdbcTemplate;
import ru.job4j.accidents.repository.RuleJdbc;
import ru.job4j.accidents.repository.TypeJdbc;

import java.util.*;

@Service
@AllArgsConstructor
public class AccidentService {

    private final AccidentJdbcTemplate accidentsRepostiory;
    private final TypeJdbc typeRepository;
    private final RuleJdbc ruleRepository;

    public Collection<Accident> findAll() {
        return accidentsRepostiory.findAll();
    }

    public Optional<Accident> findById(int id) {
        return accidentsRepostiory.findById(id);
    }

    public Accident create(Accident accident, int typeId, String[] ids) {
        accident.setType(typeRepository.findById(typeId).get());
        Set<Rule> rules = new HashSet<>();
        Arrays.stream(ids)
                .forEach(
                        id -> rules.add(
                                ruleRepository.findById(Integer.parseInt(id)).get()
                        ));
        accident.setRules(rules);
        return accidentsRepostiory.create(accident);
    }

    public boolean update(Accident accident, int typeId) {
        accident.setType(typeRepository.findById(typeId).get());
        return accidentsRepostiory.update(accident);
    }
}
