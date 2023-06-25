package ru.job4j.accidents.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.model.Rule;
import ru.job4j.accidents.repository.AccidentRepository;
import ru.job4j.accidents.repository.RuleRepository;
import ru.job4j.accidents.repository.TypeRepository;

import java.util.*;

@Service
@AllArgsConstructor
public class AccidentService {

    private final AccidentRepository accidentsRepository;
    private final TypeRepository typeRepository;
    private final RuleRepository ruleRepository;

    public Collection<Accident> findAll() {
        return accidentsRepository.findAll();
    }

    public Optional<Accident> findById(int id) {
        return accidentsRepository.findById(id);
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
        accidentsRepository.save(accident);
    }

    public void update(Accident accident, int typeId) {
        accident.setType(typeRepository.findById(typeId).get());
        accidentsRepository.deleteById(typeId);
        accidentsRepository.save(accident);
    }
}
