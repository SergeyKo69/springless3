package ru.kogut.enterprise.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.kogut.enterprise.model.Category;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@Transactional
public class CategoryDAO extends AbstractDAO<Category> {
    Map<String, Category> listCategory = new HashMap<>();

    @PersistenceContext
    private EntityManager entityManager;

    public Category getCategoryById(String id) {
        if (id.isEmpty()) return null;
        Category category = listCategory.get(id);
        if (category != null) {
            return category;
        } else {
            List<Category> list = entityManager.createQuery("SELECT e FROM Category e WHERE e.id = :id", Category.class)
                    .setParameter("id", id).getResultList();
            if (list.size() > 0) {
                category = list.get(0);
                listCategory.put(category.getId(), category);
                return category;
            } else {
                return null;
            }
        }
    }

    public List<Category> getAllCategories() {
        return entityManager.createQuery("SELECT e FROM Category e").getResultList();
    }
}
