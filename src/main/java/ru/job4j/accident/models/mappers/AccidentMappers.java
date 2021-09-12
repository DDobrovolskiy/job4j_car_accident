package ru.job4j.accident.models.mappers;

import org.springframework.jdbc.core.RowMapper;
import ru.job4j.accident.models.Accident;
import ru.job4j.accident.models.AccidentType;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AccidentMappers implements RowMapper<Accident> {
    @Override
    public Accident mapRow(ResultSet resultSet, int i) throws SQLException {
        Accident accident = new Accident();
        accident.setId(resultSet.getInt("accident_id"));
        accident.setName(resultSet.getString("accident_name"));
        accident.setText(resultSet.getString("accident_text"));
        accident.setAddress(resultSet.getString("accident_address"));
        accident.setType(AccidentType.of(
                resultSet.getInt("type_id"),
                resultSet.getString("type_name")));
        return accident;
    }
}
