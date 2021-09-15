package ru.job4j.accident.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.accident.models.User;
import ru.job4j.accident.repository.AuthorityRepository;
import ru.job4j.accident.repository.UserRepository;

@Service
@Slf4j
public class UserServices {
    private final PasswordEncoder encoder;
    private final UserRepository users;
    private final AuthorityRepository authorities;

    public UserServices(PasswordEncoder encoder,
                      UserRepository users,
                      AuthorityRepository authorities) {
        this.encoder = encoder;
        this.users = users;
        this.authorities = authorities;
    }

    public boolean registration(User user) {
        try {
            user.setEnabled(true);
            user.setPassword(encoder.encode(user.getPassword()));
            user.setAuthorities(authorities.findByAuthority("ROLE_USER"));
            users.save(user);
            return true;
        } catch (Exception e) {
            log.error("Error registration", e);
            return false;
        }
    }
}
