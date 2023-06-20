package ru.job4j.accidents.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.model.Rule;
import ru.job4j.accidents.repository.AccidentHibernate;
import ru.job4j.accidents.repository.RuleHibernate;
import ru.job4j.accidents.repository.TypeHibernate;

import java.util.*;

@Service
@AllArgsConstructor
public class AccidentService {

    private final AccidentHibernate accidentsRepostiory;
    private final TypeHibernate typeRepository;
    private final RuleHibernate ruleRepository;

    public Collection<Accident> findAll() {
        return accidentsRepostiory.getAll();
    }

    public Optional<Accident> findById(int id) {
        return accidentsRepostiory.findById(id);
    }

    public void create(Accident accident, int typeId, String[] ids) {
        accident.setType(typeRepository.findById(typeId).get());
        Set<Rule> rules = new HashSet<>();
        Arrays.stream(ids)
                .forEach(
                        id -> rules.add(
                                ruleRepository.findById(Integer.parseInt(id)).get()
                        ));
        accident.setRules(rules);
        accidentsRepostiory.save(accident);
    }

    public boolean update(Accident accident, int typeId) {
        accident.setType(typeRepository.findById(typeId).get());
        return accidentsRepostiory.update(accident);
    }
}
