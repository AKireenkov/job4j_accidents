package ru.job4j.accidents.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.model.Rule;
import ru.job4j.accidents.repository.AccidentJdbcTemplate;
import ru.job4j.accidents.repository.RuleMem;
import ru.job4j.accidents.repository.TypeMem;

import java.util.*;

@Service
@AllArgsConstructor
public class AccidentService {

    private final AccidentJdbcTemplate accidentsRepostiory;
    private final TypeMem typeMem;
    private final RuleMem ruleMem;

    public Collection<Accident> findAll() {
        return accidentsRepostiory.findAll();
    }

    public Optional<Accident> findById(int id) {
        return accidentsRepostiory.findById(id);
    }

    public Accident create(Accident accident, int typeId, String[] ids) {
        accident.setType(typeMem.findById(typeId));
        Set<Rule> rules = new HashSet<>();
        Arrays.stream(ids)
                .forEach(
                        id -> rules.add(
                                ruleMem.findById(Integer.parseInt(id)).get()
                        ));
        accident.setRules(rules);
        return accidentsRepostiory.create(accident);
    }

    public boolean update(Accident accident, int typeId) {
        accident.setType(typeMem.findById(typeId));
        return accidentsRepostiory.update(accident);
    }
}
