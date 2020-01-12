package com.github.pawelsosulski.skillscollector.dao;

import com.github.pawelsosulski.skillscollector.entity.Source;
import org.hibernate.SessionFactory;

import java.util.List;

public class SourceDao extends BaseDao {

    public SourceDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public Source get(Long id) {
        return super.produceInTransaction(session -> session.get(Source.class,id));
    }

    public void save(Source source) {
        super.executeInTransaction(session -> session.save(source));
    }

    public void update(Source source) {
        super.executeInTransaction(session -> session.update(source));
    }

    public void delete(Source source) {
        super.executeInTransaction(session -> session.delete(source));
    }

    public List<Source> getAll(){
        return super.produceInTransaction(session -> session.createQuery("SELECT s FROM Source s",Source.class)
                .getResultList());
    }

    public List<Source> getAllWithSkills(){
        return super.produceInTransaction(session -> session.createQuery("SELECT s FROM Source s fetch all properties",Source.class)
                .getResultList());
    }

    public List<Source> getAllWithSkills2(){
        return super.produceInTransaction(session -> session.createQuery("SELECT DISTINCT s FROM Source s JOIN FETCH s.attachedSkills",Source.class)
                .getResultList());
    }


}
