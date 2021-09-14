package ru.job4j.accident.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "authorities")
@Setter
@Getter
@NoArgsConstructor
@ToString(of = {"authority"})
public class Authorities {
    @Id
    private long id;
    private String authority;
    @OneToOne(mappedBy = "authorities")
    private User user;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Authorities authority = (Authorities) o;
        return id == authority.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
