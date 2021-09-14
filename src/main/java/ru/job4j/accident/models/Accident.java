package ru.job4j.accident.models;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "hbn_accidents")
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Accident {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String text;
    private String address;
    @ManyToOne(cascade = CascadeType.PERSIST)
    private AccidentType type;
    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.REFRESH,
            CascadeType.MERGE})
    private Set<Rule> rules = new HashSet<>();
}
