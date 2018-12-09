package ru.kogut.enterprise;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.kogut.enterprise.configuration.ApplicationConfiguration;
import ru.kogut.enterprise.dao.CompanyDAO;
import ru.kogut.enterprise.model.Company;

public class Application {

    public static void main(String[] args) {
        final ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);
        final CompanyDAO companyDao = context.getBean(CompanyDAO.class);
        Company company = new Company();
        company.setName("ООО Рога и копыта");
        company.setDescription("Хорошая компания");
        company.setAddress("г. Москва");
        companyDao.save(company);
        System.out.println(company.getId());
    }
}