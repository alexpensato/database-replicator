package com.pensato.replicator.config;

import org.springframework.core.env.Environment;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;
import java.util.Properties;

public abstract class AbstractDataSourceConfig {

    LocalContainerEntityManagerFactoryBean getEntityManagerFactory(DataSource dataSource, Environment env)
    {
        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setDataSource(dataSource);
        factory.setPackagesToScan("com.pensato.replicator.models");
        factory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());

        Properties jpaProperties = new Properties();
        String autoDdl = env.getProperty("spring.jpa.hibernate.ddl-auto");
        if (autoDdl!=null) {
            jpaProperties.put("hibernate.hbm2ddl.auto", autoDdl);
        }
        String showSql = env.getProperty("spring.jpa.show-sql");
        if (showSql!=null) {
            jpaProperties.put("hibernate.show-sql", showSql);
        }
        factory.setJpaProperties(jpaProperties);

        return factory;
    }
}
