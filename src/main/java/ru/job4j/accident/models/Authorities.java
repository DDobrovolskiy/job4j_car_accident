package ru.job4j.accident.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "authorities")
@Setter
@Getter
@NoArgsConstructor
@ToString(of = {"authority"})
public class Authorities {
    @Id
    private String username;
    @Column(name = "authority")
    private String authority;
    @MapsId
    @OneToOne
    @JoinColumn(name = "username", nullable = false)
    private User user;
}
