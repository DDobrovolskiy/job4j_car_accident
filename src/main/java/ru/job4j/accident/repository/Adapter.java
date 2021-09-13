package ru.job4j.accident.repository;

import lombok.NonNull;
import org.springframework.stereotype.Component;
import ru.job4j.accident.models.Accident;
import ru.job4j.accident.models.AccidentType;
import ru.job4j.accident.models.Rule;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Component
public class Adapter implements AccidentDAO {
    private final AccidentRepository accidentRepository;
    private final RuleRepository ruleRepository;
    private final AccidentTypeRepository accidentTypeRepository;

    public Adapter(AccidentRepository accidentRepository,
                   RuleRepository ruleRepository,
                   AccidentTypeRepository accidentTypeRepository) {
        this.accidentRepository = accidentRepository;
        this.ruleRepository = ruleRepository;
        this.accidentTypeRepository = accidentTypeRepository;
    }

    @Override
    public void addAccident(@NonNull Accident accident) {
        accidentRepository.save(accident);
    }

    @Override
    public List<Accident> getAccidents() {
        List<Accident> rsl = new LinkedList<>();
        accidentRepository.findAll().forEach(rsl::add);
        return rsl;
    }

    @Override
    public Optional<Accident> getAccident(int id) {
        return accidentRepository.findById(id);
    }

    @Override
    public void addType(@NonNull AccidentType type) {
        accidentTypeRepository.save(type);
    }

    @Override
    public List<AccidentType> getTypes() {
        List<AccidentType> rsl = new LinkedList<>();
        accidentTypeRepository.findAll().forEach(rsl::add);
        return rsl;
    }

    @Override
    public Optional<AccidentType> getType(int id) {
        return accidentTypeRepository.findById(id);
    }

    @Override
    public List<Rule> getRules() {
        List<Rule> rsl = new LinkedList<>();
        ruleRepository.findAll().forEach(rsl::add);
        return rsl;
    }

    @Override
    public void addRuler(@NonNull Rule rule) {
        ruleRepository.save(rule);
    }

    @Override
    public Optional<Rule> getRule(int id) {
        return ruleRepository.findById(id);
    }
}
