package com.github.pawelsosulski.skillscollector.model.dao;

import com.github.pawelsosulski.skillscollector.model.Source;
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
        return super.produceInTransaction(session -> session.createQuery("Select s FROM Source s",Source.class))
                .getResultList();
    }


}
