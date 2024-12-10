package ru.example.hibernateRepeatable;


import jakarta.persistence.EntityManagerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * Класс для ручной настройки на "чистом" Spring
 */

@Configuration
@PropertySource(value = "classpath:application.properties")
public class PersistenceConfig {

    private final Environment environment;

    public PersistenceConfig(Environment environment) {
        this.environment = environment;
    }

    private Properties hibernateProperties() {
        Properties properties = new Properties();
        /*
        hibernate.dialect нужно для обеспечения автоматической генерации кода
        hibernate.show_sql - параметр, принимает значения true/false
        Отвечает за включение или отключение режима отладочного вывода SQL-запроса
         */
        properties.put("hibernate.dialect", environment.getRequiredProperty("hibernate.dialect"));
        properties.put("hibernate.show_sql", environment.getRequiredProperty("hibernate.show_sql"));
        return properties;
    }

    /**
     * @param environment - значения свойств берутся из
     *                    файла настроек application.properties
     * @Bean - метод помеченный этой аннотацией, будет возвращать объекты - бины Spring
     * @return
     */
    @Bean
    public DataSource dataSource(Environment environment) {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(environment.getRequiredProperty("jdbc.driverClassName"));
        dataSource.setUrl(environment.getRequiredProperty("jdbc.url"));
        dataSource.setUsername(environment.getRequiredProperty("jdbc.username"));
        dataSource.setPassword(environment.getRequiredProperty("jdbc.password"));

        return dataSource;
    }

    /**
     * Менеджер сущностей
     * @param dataSource
     * @return
     */
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
        final LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
        emf.setDataSource(dataSource);
        /*
        В этом пакете Hibernate будет искать классы-сущности, отмеченные @Entity
         */
        emf.setPackagesToScan("ru.example.hibernateRepeatable");

        final HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        emf.setJpaVendorAdapter(vendorAdapter);
        emf.setJpaProperties(hibernateProperties());

        return emf;
    }

    @Bean
    public JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);
        return transactionManager;
    }




}
