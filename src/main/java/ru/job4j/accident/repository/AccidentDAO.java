package ru.job4j.accident.repository;

import lombok.NonNull;
import ru.job4j.accident.models.Accident;
import ru.job4j.accident.models.AccidentType;
import ru.job4j.accident.models.Rule;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface AccidentDAO {
    void addAccident(@NonNull Accident accident);

    List<Accident> getAccidents();

    Optional<Accident> getAccident(int id);

    void addType(@NonNull AccidentType type);

    List<AccidentType> getTypes();

    Optional<AccidentType> getType(int id);

    List<Rule> getRules();

    void addRuler(@NonNull Rule rule);

    Optional<Rule> getRule(int id);
}
