package ma.youcode.cch.generic.implementations;

import ma.youcode.cch.generic.interfaces.GenericRepository;
import ma.youcode.cch.generic.interfaces.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public abstract class GenericServiceImp<T , ID> implements GenericService<T , ID> {

    private  GenericRepository<T, UUID> genericRepository;
    @Autowired
    protected void setGenericRepository(GenericRepository<T , UUID> genericRepository) {
        this.genericRepository = genericRepository;
    }
    @Override
    @Transactional
    public T save(T entity) {
        return genericRepository.save(entity);
    }

    @Override
    public T update(T entity) {
        return null;
    }

    @Override
    public T delete(Object entity) {
        return null;
    }

    @Override
    public List<T> findAll() {
        return List.of();
    }

    @Override
    public T findById(ID id) {
        return null;
    }
}
