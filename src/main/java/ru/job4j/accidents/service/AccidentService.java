package ru.job4j.accidents.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.repository.AccidentMem;

import java.util.Collection;

@Service
@AllArgsConstructor
public class AccidentService {
    private AccidentMem accidentMem;

    public Collection<Accident> findAll() {
        return accidentMem.findAll();
    }

    public Accident findById(int id) {
        return accidentMem.findById(id);
    }

    public Accident create(Accident accident) {
        return accidentMem.create(accident);
    }
}
