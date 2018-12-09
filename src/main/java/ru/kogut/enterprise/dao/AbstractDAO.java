package ru.kogut.enterprise.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.kogut.enterprise.model.AbstractEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
@Transactional
public class AbstractDAO<T extends AbstractEntity> {

    @PersistenceContext
    private EntityManager entityManager;

    public void save(T o) {
        if (o == null) return;
        entityManager.persist(o);
    }

    public void delete(T o) {
        if (o == null) return;
        entityManager.remove(entityManager.contains(o) ? o : entityManager.merge(o));
//        entityManager.remove(o);
    }

    public void saveAndUpdate(T o) {
        if (o == null) return;
        entityManager.merge(o);
    }

}
