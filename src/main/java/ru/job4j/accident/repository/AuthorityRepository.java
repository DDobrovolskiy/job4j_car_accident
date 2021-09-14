package ru.job4j.accident.repository;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.accident.models.Authorities;

public interface AuthorityRepository extends CrudRepository<Authorities, Integer> {

    Authorities findByAuthority(String authority);
}
