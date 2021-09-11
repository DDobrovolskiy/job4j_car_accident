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
        addAccidents();
    }

    private void addAccidents() {
        Accident accident1 = new Accident();
        accident1.setAddress("Lenina, st. 2");
        accident1.setName("Georgy");
        accident1.setText("Car on grass");
        accidentMem.add(accident1);
        Accident accident2 = new Accident();
        accident2.setAddress("Mira, st. 32");
        accident2.setName("Alex");
        accident2.setText("Cross stop line");
        accidentMem.add(accident2);
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
