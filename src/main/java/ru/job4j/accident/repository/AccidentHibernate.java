package ru.job4j.accident.repository;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.models.Accident;
import ru.job4j.accident.models.AccidentType;
import ru.job4j.accident.models.Rule;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Repository
@Slf4j
public class AccidentHibernate implements AccidentDAO {
    private final SessionFactory sf;

    public AccidentHibernate(SessionFactory sf) {
        this.sf = sf;
    }

    @Override
    public void addAccident(@NonNull Accident accident) {
        log.info(accident.toString());
        try (Session session = sf.openSession()) {
            session.getTransaction().begin();
            if (accident.getId() == 0) {
                session.save(accident);
            } else {
                session.merge(accident);
            }
            session.getTransaction().commit();
        } catch (Exception e) {
            log.error("Ошибка сохранения Accident", e);
        }
    }

    @Override
    public List<Accident> getAccidents() {
        try (Session session = sf.openSession()) {
            log.debug("Get accidents");
            var rsl = session
                    .createQuery(
                            "from Accident",
                            Accident.class)
                    .list();
            //log.debug(rsl.toString());
            return rsl;
        }  catch (Exception e) {
            log.error("Ошибка получения Accidents#List", e);
            return new LinkedList<>();
        }
    }

    @Override
    public Optional<Accident> getAccident(int id) {
        try (Session session = sf.openSession()) {
            return  Optional.ofNullable(session
                    .createQuery(
                            "from Accident a WHERE a.id = :idAcc",
                            Accident.class)
                    .setParameter("idAcc", id)
                    .getSingleResult());
        }  catch (Exception e) {
            log.error("Ошибка получения Accident", e);
            return Optional.empty();
        }
    }

    @Override
    public void addType(@NonNull AccidentType type) {
        try (Session session = sf.openSession()) {
            session.persist(type);
        } catch (Exception e) {
            log.error("Ошибка сохранения Rule", e);
        }
    }

    @Override
    public List<AccidentType> getTypes() {
        try (Session session = sf.openSession()) {
            return session
                    .createQuery(
                            "from AccidentType",
                            AccidentType.class)
                    .list();
        }  catch (Exception e) {
            log.error("Ошибка получения AccidentTypes#List", e);
            return new LinkedList<>();
        }
    }

    @Override
    public Optional<AccidentType> getType(int id) {
        try (Session session = sf.openSession()) {
            return Optional.ofNullable(session.get(AccidentType.class, id));
        } catch (Exception e) {
            log.error("Ошибка получения Rule", e);
            return Optional.empty();
        }
    }

    @Override
    public List<Rule> getRules() {
        try (Session session = sf.openSession()) {
            return session
                    .createQuery(
                            "from Rule",
                            Rule.class)
                    .list();
        }  catch (Exception e) {
            log.error("Ошибка получения Rules#List", e);
            return new LinkedList<>();
        }
    }

    @Override
    public void addRuler(@NonNull Rule rule) {
        try (Session session = sf.openSession()) {
            session.persist(rule);
        } catch (Exception e) {
            log.error("Ошибка сохранения Rule", e);
        }
    }

    @Override
    public Optional<Rule> getRule(int id) {
        try (Session session = sf.openSession()) {
            return Optional.ofNullable(session.get(Rule.class, id));
        } catch (Exception e) {
            log.error("Ошибка получения Rule", e);
            return Optional.empty();
        }
    }
}
