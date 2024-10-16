package ma.youcode.cch.generic.implementations;

import ma.youcode.cch.generic.interfaces.GenericRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public abstract class GenericRepositoryImp<T , ID> implements GenericRepository<T , ID> {

    private  SessionFactory sessionFactory;

    @Autowired
    protected void setSessionFactory(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }


    @Override
    @Transactional
    public T save(T entity) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(entity);
        return entity;
    }

    @Override
    public T update(T entity) {
        return null;
    }

    @Override
    public T delete(T entity) {
        return null;
    }

    @Override
    public T findById(ID id) {
        return null;
    }

    @Override
    public List<T> findAll() {
        return List.of();
    }
}
