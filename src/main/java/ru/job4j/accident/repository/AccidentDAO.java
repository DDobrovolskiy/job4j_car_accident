package ru.job4j.accident.repository;

import lombok.NonNull;
import ru.job4j.accident.models.Accident;
import ru.job4j.accident.models.AccidentType;

import java.util.List;
import java.util.Optional;

public interface AccidentDAO {
    void addAccident(@NonNull Accident accident);

    List<Accident> getAccidents();

    Optional<Accident> getAccident(int id);

    void addType(@NonNull AccidentType type);

    List<AccidentType> getTypes();

    Optional<AccidentType> getType(int id);
}
