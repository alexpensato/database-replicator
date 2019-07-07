package com.pensato.replicator.config;

import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(
        basePackages = "com.pensato.replicator.repositories.mariadb",
        entityManagerFactoryRef = "mariaDbEntityManagerFactory",
        transactionManagerRef = "mariaDbTransactionManager"
)
@AllArgsConstructor
public class MariaDbDataSourceConfig extends AbstractDataSourceConfig
{
    private Environment env;

    @Bean
    @ConfigurationProperties(prefix="datasource.mariadb")
    public DataSourceProperties mariaDbDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    public DataSource mariaDbDataSource() {
        DataSourceProperties mariaDbDataSourceProperties = mariaDbDataSourceProperties();
        return DataSourceBuilder.create()
                .driverClassName(mariaDbDataSourceProperties.getDriverClassName())
                .url(mariaDbDataSourceProperties.getUrl())
                .username(mariaDbDataSourceProperties.getUsername())
                .password(mariaDbDataSourceProperties.getPassword())
                .build();
    }

    @Bean
    public PlatformTransactionManager mariaDbTransactionManager()
    {
        EntityManagerFactory factory = mariaDbEntityManagerFactory().getObject();
        return new JpaTransactionManager(factory);
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean mariaDbEntityManagerFactory()
    {
        return getEntityManagerFactory(mariaDbDataSource(), env);
    }

    @Bean
    public DataSourceInitializer mariaDbDataSourceInitializer()
    {
        DataSourceInitializer dataSourceInitializer = new DataSourceInitializer();
        dataSourceInitializer.setDataSource(mariaDbDataSource());
        dataSourceInitializer.setEnabled(env.getProperty("datasource.mariadb.initialize", Boolean.class, false));
        return dataSourceInitializer;
    }
}