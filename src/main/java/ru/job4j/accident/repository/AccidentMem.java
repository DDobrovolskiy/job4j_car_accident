package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.models.Accident;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class AccidentMem {
    private static final Map<Integer, Accident> ACCIDENTS = new ConcurrentHashMap<>();
    private static final AtomicInteger COUNT = new AtomicInteger();

    public Accident add(Accident accident) {
        if ((accident.getId() <= 0) || (accident.getId() > COUNT.get())) {
            accident.setId(COUNT.incrementAndGet());
        }
        ACCIDENTS.put(accident.getId(), accident);
        return accident;
    }

    public List<Accident> getAccidents() {
        return List.copyOf(ACCIDENTS.values());
    }

    public Accident get(int id) {
        return ACCIDENTS.get(id);
    }
}
