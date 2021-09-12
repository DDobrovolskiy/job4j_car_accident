package ru.job4j.accident.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.job4j.accident.models.Accident;
import ru.job4j.accident.models.AccidentType;
import ru.job4j.accident.repository.AccidentDAO;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class AccidentService {
    private final AccidentDAO accidentDAO;

    private AccidentService(AccidentDAO accidentDAO) {
        this.accidentDAO = accidentDAO;
    }

    public List<Accident> getAccidents() {
        return accidentDAO.getAccidents();
    }

    public void addAccident(Accident accident) {
        try {
            Optional<AccidentType> type = accidentDAO.getType(accident.getType().getId());
            accident.setType(type.orElseThrow());
            accidentDAO.addAccident(accident);
        } catch (Exception e) {
            log.error("Ошибка индекса типа инцедента, не найден в базе", e);
        }
    }

    public Accident getAccident(int id) {
        try {
            return accidentDAO.getAccident(id).orElseThrow();
        } catch (Exception e) {
            log.error("Инцедент не найден по данному id", e);
            var accidentNew = new Accident();
            accidentNew.setType(new AccidentType());
            return accidentNew;
        }
    }

    public List<AccidentType> getTypes() {
        return accidentDAO.getTypes();
    }
}
