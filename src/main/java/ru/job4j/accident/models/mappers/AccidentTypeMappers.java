package ru.job4j.accident.models.mappers;

import org.springframework.jdbc.core.RowMapper;
import ru.job4j.accident.models.AccidentType;
import ru.job4j.accident.models.Rule;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AccidentTypeMappers implements RowMapper<AccidentType> {
    @Override
    public AccidentType mapRow(ResultSet resultSet, int i) throws SQLException {
        AccidentType accidentType = new AccidentType();
        accidentType.setId(resultSet.getInt("type_id"));
        accidentType.setName(resultSet.getString("type_name"));
        return accidentType;
    }
}
