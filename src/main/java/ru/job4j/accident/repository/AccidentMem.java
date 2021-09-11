package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.models.Accident;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class AccidentMem {
    private final Map<Integer, Accident> accidents = new HashMap<>();
    private int count = 0;

    public Accident add(Accident accident) {
        count++;
        accident.setId(count);
        accidents.put(count, accident);
        return accident;
    }

    public List<Accident> getAccidents() {
        return List.copyOf(accidents.values());
    }
}
