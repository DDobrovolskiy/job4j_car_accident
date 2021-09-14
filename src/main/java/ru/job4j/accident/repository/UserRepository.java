package ru.job4j.accident.repository;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.accident.models.User;

public interface UserRepository extends CrudRepository<User, Integer> {
}