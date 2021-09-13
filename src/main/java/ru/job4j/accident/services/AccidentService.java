package ru.job4j.accident.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.accident.models.Accident;
import ru.job4j.accident.models.AccidentType;
import ru.job4j.accident.models.Rule;
import ru.job4j.accident.repository.AccidentDAO;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Slf4j
public class AccidentService {
    private final AccidentDAO accidentDAO;

    public AccidentService(AccidentDAO accidentDAO) {
        this.accidentDAO = accidentDAO;
    }

    public List<Accident> getAccidents() {
        List<Accident> rsl = accidentDAO.getAccidents();
        log.debug(rsl.toString());
        return rsl;
    }

    @Transactional
    public void addAccident(Accident accident, String[] ids) {
        try {
            Optional<AccidentType> type = accidentDAO.getType(accident.getType().getId());
            accident.setType(type.orElseThrow());
            Set<Rule> rules = new HashSet<>();
            for (String id : ids) {
                rules.add(accidentDAO.getRule(Integer.parseInt(id)).orElseThrow());
            }
            accident.setRules(rules);
            log.debug(accident.toString());
            accidentDAO.addAccident(accident);
        } catch (Exception e) {
            log.error("Ошибка индекса, не найден в базе", e);
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

    public List<Rule> getRules() {
        return accidentDAO.getRules();
    }
}
