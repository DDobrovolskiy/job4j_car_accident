package ru.job4j.accident.services;

import org.springframework.stereotype.Service;
import ru.job4j.accident.models.Accident;
import ru.job4j.accident.repository.AccidentMem;

import java.util.List;

@Service
public class AccidentService {
    private final AccidentMem accidentMem;

    private AccidentService(AccidentMem accidentMem) {
        this.accidentMem = accidentMem;
    }

    public List<Accident> getAccidents() {
        return accidentMem.getAccidents();
    }

    public void add(Accident accident) {
        accidentMem.add(accident);
    }

    public Accident get(int id) {
        return accidentMem.get(id);
    }
}
