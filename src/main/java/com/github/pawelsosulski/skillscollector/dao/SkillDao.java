package com.github.pawelsosulski.skillscollector.dao;

import com.github.pawelsosulski.skillscollector.entity.Skill;

import org.hibernate.SessionFactory;

import java.util.List;

public class SkillDao extends BaseDao {
    public SkillDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public Skill get(Long id) {
        return super.produceInTransaction(session -> session.get(Skill.class,id));
    }

    public void save(Skill skill) {
        super.executeInTransaction(session -> session.save(skill));
    }

    public void update(Skill skill) {
        super.executeInTransaction(session -> session.update(skill));
    }

    public void delete(Skill skill) {
        super.executeInTransaction(session -> session.delete(skill));
    }

    public List<Skill> getAll() {
        return super.produceInTransaction(session ->
                session.createQuery("Select s from Skill s",Skill.class).getResultList());
    }

    public List<Skill> getAllByIds(List<Long> skillsId) {
        return super.produceInTransaction(session ->
                session.createQuery("Select s from Skill s Where s.id in (:ids)",Skill.class)
                .setParameter("ids",skillsId)
                .getResultList());
    }
}
