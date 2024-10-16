package ma.youcode.cch.generic.interfaces;

import java.util.List;

public interface GenericService<T , ID> {

    T save(T entity);

    T update(T entity);

    T delete(T entity);

    T findById(ID id);

    List<T> findAll();


}
