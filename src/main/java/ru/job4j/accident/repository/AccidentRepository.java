package ru.job4j.accident.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.models.Accident;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccidentRepository extends CrudRepository<Accident, Integer> {
    @Query(value = "SELECT DISTINCT a FROM Accident a LEFT JOIN FETCH a.rules")
    List<Accident> findAll();

    @Query(value = "SELECT DISTINCT a FROM Accident a LEFT JOIN FETCH a.rules WHERE a.id = :id")
    Optional<Accident> findById(@Param("id") int id);
}
