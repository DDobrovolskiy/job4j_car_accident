package ru.job4j.accident.models;

import lombok.*;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Accident {
    private int id;
    private String name;
    private String text;
    private String address;
    private AccidentType type;
}
