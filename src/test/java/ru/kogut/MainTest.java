package ru.kogut;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.kogut.enterprise.configuration.ApplicationConfiguration;
import ru.kogut.enterprise.dao.AdDAO;
import ru.kogut.enterprise.dao.CategoryDAO;
import ru.kogut.enterprise.dao.CompanyDAO;
import ru.kogut.enterprise.model.Ad;
import ru.kogut.enterprise.model.Category;
import ru.kogut.enterprise.model.Company;

public class MainTest {

    @Test
    public void TestCompany() {
        final ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);
        final CompanyDAO companyDao = context.getBean(CompanyDAO.class);
        Company company = new Company();
        company.setName("ООО Рога и копыта");
        company.setDescription("Хорошая компания");
        company.setAddress("г. Москва");
        companyDao.save(company);
        Assert.assertNotNull(companyDao.getCompanyById(company.getId()));
        companyDao.delete(company);
    }

    @Test
    public void TestCategory() {
        final ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);
        final CategoryDAO categoryDAO = context.getBean(CategoryDAO.class);
        Category category = new Category();
        category.setName("New category");
        categoryDAO.save(category);
        Assert.assertNotNull(categoryDAO.getCategoryById(category.getId()));
        categoryDAO.delete(category);
    }

    @Test
    public void TestAd() {
        final ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);
        final CompanyDAO companyDao = context.getBean(CompanyDAO.class);
        final CategoryDAO categoryDAO = context.getBean(CategoryDAO.class);
        final AdDAO adDAO = context.getBean(AdDAO.class);
        Company company = new Company();
        company.setName("ООО Рога и копыта");
        company.setDescription("Хорошая компания");
        company.setAddress("г. Москва");
        companyDao.save(company);
        Category category = new Category();
        category.setName("New category");
        categoryDAO.save(category);
        Ad ad = new Ad();
        ad.setBody("body ad");
        ad.setCategory(category);
        ad.setCompany(company);
        ad.setPhone("+79007777777");
        ad.setTitle("title ad");
        adDAO.save(ad);
        Assert.assertNotNull(adDAO.getAdById(ad.getId()));
        adDAO.delete(ad);
        categoryDAO.delete(category);
        companyDao.delete(company);
    }

}
