package ru.job4j.accident.repository;

import lombok.NonNull;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.models.Accident;
import ru.job4j.accident.models.AccidentType;
import ru.job4j.accident.models.Rule;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

//@Repository
public class AccidentMem implements AccidentDAO {
    private final Map<Integer, Accident> accidents = new ConcurrentHashMap<>();
    private final Map<Integer, AccidentType> types = new ConcurrentHashMap<>();
    private final Map<Integer, Rule> rules = new ConcurrentHashMap<>();
    private final AtomicInteger countAccident = new AtomicInteger();
    private final AtomicInteger countType = new AtomicInteger();
    private final AtomicInteger countRule = new AtomicInteger();

    public AccidentMem() {
        initData();
    }

    public void initData() {
        //Ruler
        List<Rule> rules = new ArrayList<>();
        rules.add(Rule.of(1, "Статья. 1"));
        rules.add(Rule.of(2, "Статья. 2"));
        rules.add(Rule.of(3, "Статья. 3"));
        rules.forEach(this::addRuler);
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
        accident1.setRules(Set.of(getRule(1).get(), getRule(2).get()));
        this.addAccident(accident1);
        Accident accident2 = new Accident();
        accident2.setAddress("Mira, st. 32");
        accident2.setName("Alex");
        accident2.setText("Cross stop line");
        accident2.setType(getType(4).get());
        accident2.setRules(Set.of(getRule(2).get(), getRule(3).get()));
        this.addAccident(accident2);
    }

    public void addAccident(@NonNull Accident accident) {
        if ((accident.getId() <= 0) || (accident.getId() > countAccident.get())) {
            accident.setId(countAccident.incrementAndGet());
        }
        accidents.put(accident.getId(), accident);
    }

    public List<Accident> getAccidents() {
        return List.copyOf(accidents.values());
    }

    public Optional<Accident> getAccident(int id) {
        return Optional.ofNullable(accidents.get(id));
    }

    public void addType(@NonNull AccidentType type) {
        if ((type.getId() <= 0) || (type.getId() > countType.get())) {
            type.setId(countType.incrementAndGet());
        }
        types.put(type.getId(), type);
    }

    public List<AccidentType> getTypes() {
        return List.copyOf(types.values());
    }

    public Optional<AccidentType> getType(int id) {
        return Optional.ofNullable(types.get(id));
    }

    @Override
    public List<Rule> getRules() {
        return List.copyOf(rules.values());
    }

    @Override
    public void addRuler(@NonNull Rule rule) {
        if ((rule.getId() <= 0) || (rule.getId() > countRule.get())) {
            rule.setId(countRule.incrementAndGet());
        }
        rules.put(rule.getId(), rule);
    }

    @Override
    public Optional<Rule> getRule(int id) {
        return Optional.ofNullable(rules.get(id));
    }
}
