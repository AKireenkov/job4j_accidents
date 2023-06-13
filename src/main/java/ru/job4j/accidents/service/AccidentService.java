package ru.job4j.accidents.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.repository.AccidentMem;
import ru.job4j.accidents.repository.TypeMem;

import java.util.Collection;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AccidentService {
    private AccidentMem accidentMem;

    private TypeMem typeMem;

    public Collection<Accident> findAll() {
        return accidentMem.findAll();
    }

    public Optional<Accident> findById(int id) {
        return accidentMem.findById(id);
    }

    public Accident create(Accident accident, int typeId) {
        accident.setType(typeMem.findById(typeId));
        return accidentMem.create(accident);
    }
}
