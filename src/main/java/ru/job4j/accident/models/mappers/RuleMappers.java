package ru.job4j.accident.models.mappers;

import org.springframework.jdbc.core.RowMapper;
import ru.job4j.accident.models.Rule;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RuleMappers implements RowMapper<Rule> {
    @Override
    public Rule mapRow(ResultSet resultSet, int i) throws SQLException {
        Rule rule = new Rule();
        rule.setId(resultSet.getInt("rule_id"));
        rule.setName(resultSet.getString("rule_name"));
        return rule;
    }
}
