package ma.youcode.cch.generic.implementations;

import ma.youcode.cch.generic.interfaces.GenericDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
@Transactional
public abstract class GenericDaoImp<T , ID> implements GenericDao<T , ID> {

    @Autowired
    protected  SessionFactory sessionFactory;

    private final Class<T> entityClass;

    public GenericDaoImp(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    @Override
    public T save(T entity) {
        if (entity != null) {
            Session session = sessionFactory.getCurrentSession();
            return session.merge(entity);
        }
        return null;
    }

    @Override
    public T update(T entity) {
        if (entity != null) {
            Session session = sessionFactory.getCurrentSession();
            return  session.merge(entity);
        }
        return null;
    }

    @Override
    public T delete(T entity) {
        if (entity != null) {
            Session session = sessionFactory.getCurrentSession();
            if(!session.contains(entity)){
               entity = session.merge(entity);
            }
            session.remove(entity);
            return entity;
        }
        return null;
    }

    @Override
    public Optional<T> findById(ID id) {
        if(id != null) {
            Session session = sessionFactory.getCurrentSession();
            return Optional.ofNullable(session.get(entityClass , id));
        }
        return Optional.empty();
    }

    @Override
    public Set<T> findAll() {
        Set<T> results = new HashSet<>();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            String queryStr = "FROM " + entityClass.getName();
            List<T> listResult = session.createQuery(queryStr , entityClass).getResultList();
            results.addAll(listResult);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null){ transaction.rollback();}
            e.printStackTrace();
        }finally {
            session.close();
        }
        return results;
    }


    @Override
    public boolean existsById(ID id) {
        if (id == null) {
            return false;
        }
        Session session = sessionFactory.getCurrentSession();
        T entity = (T) session.get(entityClass , id);
        return  entity != null;
    }
}
