package ru.kogut.enterprise.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.kogut.enterprise.model.Ad;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@Transactional
public class AdDAO extends AbstractDAO<Ad> {
    Map<String, Ad> listAd = new HashMap<>();

    @PersistenceContext
    private EntityManager entityManager;

    public Ad getAdById(String id) {
        if (id.isEmpty()) return null;
        Ad ad = listAd.get(id);
        if (ad != null) {
            return ad;
        } else {
            List<Ad> list = entityManager.createQuery("SELECT e FROM Ad e WHERE e.id = :id", Ad.class)
                    .setParameter("id", id).getResultList();
            if (list.size() > 0) {
                ad = list.get(0);
                listAd.put(ad.getId(), ad);
                return ad;
            } else {
                return null;
            }
        }
    }

    public List<Ad> getAllAds() {
        return entityManager.createQuery("SELECT e FROM Ad e").getResultList();
    }
}
