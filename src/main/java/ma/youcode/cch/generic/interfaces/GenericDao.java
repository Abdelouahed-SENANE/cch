package ma.youcode.cch.generic.interfaces;

import java.util.Set;

public interface GenericDao<T, ID> {

    T save(T entity);

    T update(T entity);

    T delete(T entity);

    T findById(ID id);

    Set<T> findAll();


}