package ru.job4j.accidents.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.repository.AccidentMem;
import ru.job4j.accidents.repository.TypeMem;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
@AllArgsConstructor
public class AccidentService {
    private AccidentMem accidentMem;

    private TypeMem typeMem;

    private final Map<Integer, Accident> accidents = new ConcurrentHashMap<>() {
        {
            put(1, new Accident(1,
                    "Парковка в неположеном месте",
                    "Припарковался на газоне",
                    "Ул. Московская д.1",
                    typeMem.findAllTypes().get(0)));
            put(2, new Accident(2,
                    "Превышение скорости",
                    "Превышение от 20 до 40 км/ч",
                    "Ул. Демидова д.26",
                    typeMem.findAllTypes().get(2)));
        }
    };

    public Collection<Accident> findAll() {
        return accidentMem.findAll();
    }

    public Accident findById(int id) {
        return accidentMem.findById(id);
    }

    public Accident create(Accident accident, int typeId) {
        accident.setType(typeMem.findById(typeId));
        return accidentMem.create(accident);
    }
}
