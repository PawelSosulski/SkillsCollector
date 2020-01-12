package com.github.pawelsosulski.skillscollector.dao;

import com.github.pawelsosulski.skillscollector.entity.Skill;
import com.github.pawelsosulski.skillscollector.entity.Source;
import com.github.pawelsosulski.skillscollector.entity.User;
import org.hibernate.SessionFactory;

import java.util.List;

public class UserDao extends BaseDao {

    public UserDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public User get(Long id) {
        return super.produceInTransaction(session -> session.get(User.class, id));
    }

    public void save(User user) {
        super.executeInTransaction(session -> session.save(user));
    }

    public void update(User user) {
        super.executeInTransaction(session -> session.update(user));
    }

    public void delete(User user) {
        super.executeInTransaction(session -> session.delete(user));
    }

    public Boolean isUsernameAvailable(String username) {
        return super.produceInTransaction(
                session -> session.createQuery("SELECT count(u) FROM User u WHERE u.username = :username", Long.class)
                        .setParameter("username", username)
                        .getSingleResult() <= 0
        );
    }

    public List<User> getAll() {
        return super.produceInTransaction(
                session -> session.createQuery("SELECT u FROM User u", User.class)
                        .getResultList());
    }

    public List<User> getAllByUsername(String username) {
        return super.produceInTransaction(
                session -> session.createQuery("SELECT u FROM User u WHERE u.username = :username", User.class)
                        .setParameter("username", username)
                        .getResultList());
    }

    public List<User> getAllByUsernameAndPassword(String username, String password) {
        return super.produceInTransaction(
                session -> session.createQuery("SELECT u FROM User u WHERE u.username = :username AND u.password = :password", User.class)
                        .setParameter("username", username)
                        .setParameter("password", password)
                        .getResultList());
    }

    public void confirmSource(User user, Source source) {
        super.executeInTransaction(
                session -> {
                    User merged = (User) session.merge(user);
                    merged.getKnownSource().add(source);
                    session.save(merged);
                }
        );
    }

    public List<Skill> getUserSkills(User user) {
        return super.produceInTransaction(session ->
                session.createQuery("SELECT s FROM User u JOIN u.knownSource kS JOIN kS.attachedSkills s WHERE u.id=:id", Skill.class)
                        .setParameter("id", user.getId())
                        .getResultList());
    }

    public List<Source> getUserSource(User user) {
        return super.produceInTransaction(session ->
                session.createQuery("SELECT kS FROM User u JOIN u.knownSource kS WHERE u.id=:id", Source.class)
                        .setParameter("id", user.getId())
                        .getResultList());
    }

    public List<Source> getUserSourceWithSkills(User user) {
        return super.produceInTransaction(session -> session.createQuery("SELECT DISTINCT ks FROM User u Join u.knownSource ks JOIN FETCH ks.attachedSkills WHERE u.id=:id", Source.class)
                .setParameter("id", user.getId())
                .getResultList());
    }


}
