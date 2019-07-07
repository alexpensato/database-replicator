package com.pensato.replicator.config;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableJpaRepositories(
        basePackages = "com.pensato.replicator.repositories.pgsql",
        entityManagerFactoryRef = "pgSqlEntityManagerFactory",
        transactionManagerRef = "pgSqlTransactionManager"
)
@AllArgsConstructor
public class PgSqlDataSourceConfig extends AbstractDataSourceConfig
{
    private Environment env;

    @Bean
    @ConfigurationProperties(prefix="datasource.pgsql")
    public DataSourceProperties pgSqlDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    public DataSource pgSqlDataSource() {
        DataSourceProperties pgSqlDataSourceProperties = pgSqlDataSourceProperties();
        return DataSourceBuilder.create()
                .driverClassName(pgSqlDataSourceProperties.getDriverClassName())
                .url(pgSqlDataSourceProperties.getUrl())
                .username(pgSqlDataSourceProperties.getUsername())
                .password(pgSqlDataSourceProperties.getPassword())
                .build();
    }

    @Bean
    public PlatformTransactionManager pgSqlTransactionManager()
    {
        EntityManagerFactory factory = pgSqlEntityManagerFactory().getObject();
        return new JpaTransactionManager(factory);
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean pgSqlEntityManagerFactory()
    {
        return getEntityManagerFactory(pgSqlDataSource(), env);
    }

    @Bean
    public DataSourceInitializer pgSqlDataSourceInitializer()
    {
        DataSourceInitializer dataSourceInitializer = new DataSourceInitializer();
        dataSourceInitializer.setDataSource(pgSqlDataSource());
        ResourceDatabasePopulator databasePopulator = new ResourceDatabasePopulator();
        databasePopulator.addScript(new ClassPathResource("create.sql"));
        dataSourceInitializer.setDatabasePopulator(databasePopulator);
        dataSourceInitializer.setEnabled(env.getProperty("datasource.pgsql.initialize", Boolean.class, false));
        return dataSourceInitializer;
    }
}