package ru.kogut.enterprise.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.kogut.enterprise.model.Company;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Repository
@Transactional
public class CompanyDAO extends AbstractDAO<Company> {
    Map<String, Company> listCompany = new HashMap<>();

    @PersistenceContext
    private EntityManager entityManager;

    public Company getCompanyById(String id) {
        if (id.isEmpty()) return null;
        Company company = listCompany.get(id);
        if (company != null) {
            return company;
        } else {
            List<Company> list = entityManager.createQuery("SELECT e FROM Company e WHERE e.id = :id", Company.class)
                    .setParameter("id", id).getResultList();
            if (list.size() > 0) {
                company = list.get(0);
                listCompany.put(company.getId(), company);
                return company;
            } else {
                return null;
            }
        }
    }

    public List<Company> getAllCompanies() {
        return entityManager.createQuery("SELECT e FROM Company e").getResultList();
    }
}
