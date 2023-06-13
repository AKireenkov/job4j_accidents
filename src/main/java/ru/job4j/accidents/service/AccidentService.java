package ru.job4j.accidents.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.repository.AccidentJdbcTemplate;
import ru.job4j.accidents.repository.TypeMem;

import java.util.Collection;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AccidentService {

    private final AccidentJdbcTemplate accidentsRepostiory;

    private TypeMem typeMem;

    public Collection<Accident> findAll() {
        return accidentsRepostiory.findAll();
    }

    public Optional<Accident> findById(int id) {
        return accidentsRepostiory.findById(id);
    }

    public Accident create(Accident accident, int typeId) {
        accident.setType(typeMem.findById(typeId));
        return accidentsRepostiory.create(accident);
    }

    public boolean update(Accident accident, int typeId) {
        accident.setType(typeMem.findById(typeId));
        return accidentsRepostiory.update(accident);
    }
}
