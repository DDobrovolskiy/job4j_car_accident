package ru.job4j.accident.repository;

import lombok.NonNull;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.models.Accident;
import ru.job4j.accident.models.AccidentType;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class AccidentMem implements AccidentDAO {
    private static final Map<Integer, Accident> ACCIDENTS = new ConcurrentHashMap<>();
    private static final Map<Integer, AccidentType> TYPES = new ConcurrentHashMap<>();
    private static final AtomicInteger COUNT_ACCIDENT = new AtomicInteger();
    private static final AtomicInteger COUNT_TYPE = new AtomicInteger();

    public AccidentMem() {
        initData();
    }

    public void initData() {
        //Type
        List<AccidentType> types = new ArrayList<>();
        types.add(AccidentType.of(1, "Две машины"));
        types.add(AccidentType.of(2, "Машина и человек"));
        types.add(AccidentType.of(3, "Машина и велосипед"));
        types.add(AccidentType.of(4, "Машина"));
        types.forEach(this::addType);
        //Accident
        Accident accident1 = new Accident();
        accident1.setAddress("Lenina, st. 2");
        accident1.setName("Georgy");
        accident1.setText("Car on grass");
        accident1.setType(getType(4).get());
        this.addAccident(accident1);
        Accident accident2 = new Accident();
        accident2.setAddress("Mira, st. 32");
        accident2.setName("Alex");
        accident2.setText("Cross stop line");
        accident2.setType(getType(4).get());
        this.addAccident(accident2);
    }

    public void addAccident(@NonNull Accident accident) {
        if ((accident.getId() <= 0) || (accident.getId() > COUNT_ACCIDENT.get())) {
            accident.setId(COUNT_ACCIDENT.incrementAndGet());
        }
        ACCIDENTS.put(accident.getId(), accident);
    }

    public List<Accident> getAccidents() {
        return List.copyOf(ACCIDENTS.values());
    }

    public Optional<Accident> getAccident(int id) {
        return Optional.ofNullable(ACCIDENTS.get(id));
    }

    public void addType(@NonNull AccidentType type) {
        if ((type.getId() <= 0) || (type.getId() > COUNT_TYPE.get())) {
            type.setId(COUNT_TYPE.incrementAndGet());
        }
        TYPES.put(type.getId(), type);
    }

    public List<AccidentType> getTypes() {
        return List.copyOf(TYPES.values());
    }

    public Optional<AccidentType> getType(int id) {
        return Optional.ofNullable(TYPES.get(id));
    }
}
