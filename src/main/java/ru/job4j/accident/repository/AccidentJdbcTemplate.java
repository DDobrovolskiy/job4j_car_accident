package ru.job4j.accident.repository;

import lombok.NonNull;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.models.Accident;
import ru.job4j.accident.models.AccidentType;
import ru.job4j.accident.models.Rule;
import ru.job4j.accident.models.mappers.AccidentMappers;
import ru.job4j.accident.models.mappers.AccidentTypeMappers;
import ru.job4j.accident.models.mappers.RuleMappers;

import java.sql.PreparedStatement;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

//@Repository
public class AccidentJdbcTemplate implements AccidentDAO {

    private final JdbcTemplate jdbc;

    public AccidentJdbcTemplate(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public void addAccident(@NonNull Accident accident) {
        if (accident.getId() == 0) {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbc.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(
                        "INSERT INTO accidents "
                                + "(accident_name, accident_text, accident_address, type_id) "
                                + "values (?, ?, ?, ?)",
                        new String[] {"accident_id"});
                ps.setString(1, accident.getName());
                ps.setString(2, accident.getText());
                ps.setString(3, accident.getAddress());
                ps.setInt(4, accident.getType().getId());
                return ps;
            }, keyHolder);
            accident.setId(keyHolder.getKey().intValue());
        } else {
            jdbc.update(
                    "UPDATE accidents SET "
                            + "accident_name = ?, "
                            + "accident_text = ?, "
                            + "accident_address = ?, "
                            + "type_id = ? "
                            + "WHERE accident_id = ?",
                    accident.getName(),
                    accident.getText(),
                    accident.getAddress(),
                    accident.getType().getId(),
                    accident.getId());
            deleteAccidentsRules(accident.getId());
        }
        accident.getRules().forEach(rule -> addAccidentsRules(accident.getId(), rule.getId()));
    }

    @Override
    public List<Accident> getAccidents() {
        var accidents = jdbc.query(
                "SELECT "
                + "accident_id, accident_name, accident_text, accident_address, type_id, type_name "
                + "FROM accidents "
                + "JOIN accident_types USING(type_id) ",
                new AccidentMappers());
        accidents.forEach(accident -> accident.setRules(getAccidentsRules(accident.getId())));
        return accidents;
    }

    @Override
    public Optional<Accident> getAccident(int id) {
        var accident = jdbc.query(
                "SELECT "
                + "accident_id, accident_name, accident_text, accident_address, type_id, type_name "
                + "FROM accidents "
                + "JOIN accident_types USING(type_id) "
                + "WHERE accident_id = ?",
                new Object[]{id},
                new AccidentMappers())
                .stream().findAny();
        accident.ifPresent(value -> value.setRules(getAccidentsRules(value.getId())));
        return accident;
    }

    private void addAccidentsRules(int idAccident, int idRule) {
        jdbc.update(
                "INSERT INTO accidents_rules (accident_id, rule_id) values (?, ?)",
                idAccident,
                idRule);
    }

    private void deleteAccidentsRules(int idAccident) {
        jdbc.update(
                "DELETE FROM accidents_rules WHERE accident_id = ?",
                idAccident);
    }

    private Set<Rule> getAccidentsRules(int idAccident) {
        return new HashSet<>(jdbc.query(
                "SELECT rule_id, rule_name FROM rules "
                        + "JOIN accidents_rules USING(rule_id) "
                        + "WHERE accident_id = ?",
                new Object[]{idAccident},
                new RuleMappers()));
    }

    @Override
    public void addType(@NonNull AccidentType type) {
        jdbc.update("INSERT INTO accident_types (type_name) values (?)", type.getName());
    }

    @Override
    public List<AccidentType> getTypes() {
        return jdbc.query(
                "SELECT type_id, type_name FROM accident_types",
                new AccidentTypeMappers());
    }

    @Override
    public Optional<AccidentType> getType(int id) {
        return jdbc.query("SELECT type_id, type_name FROM accident_types WHERE type_id = ?",
                new Object[]{id},
                new AccidentTypeMappers())
                .stream().findAny();
    }

    @Override
    public List<Rule> getRules() {
        return jdbc.query("SELECT rule_id, rule_name FROM rules", new RuleMappers());
    }

    @Override
    public void addRuler(@NonNull Rule rule) {
        jdbc.update("INSERT INTO rules (rule_name) values (?)", rule.getName());
    }

    @Override
    public Optional<Rule> getRule(int id) {
        return jdbc.query("SELECT rule_id, rule_name FROM rules WHERE rule_id = ?",
                new Object[]{id},
                new RuleMappers())
                .stream().findAny();
    }
}
