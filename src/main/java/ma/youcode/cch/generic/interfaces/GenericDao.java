package ma.youcode.cch.generic.interfaces;

import java.util.Optional;
import java.util.Set;

public interface GenericDao<T, ID> {

    T save(T entity);

    T update(T entity);

    T delete(T entity);

    Optional<T> findById(ID id);
    boolean existsById(ID id);

    Set<T> findAll();


}
