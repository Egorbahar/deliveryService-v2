package com.exposit.persistence;

import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration("persistenceConfigurationStarter")
@PropertySource("/database/database.properties")
@ComponentScan(basePackages = {"com.exposit.persistence"})
@EnableJpaRepositories(basePackages = "com.exposit.persistence.repository")
@EntityScan(basePackages = "com.exposit.persistence.entity")
@EnableTransactionManagement
@AutoConfigureBefore({com.exposit.core.StarterConfig.class})
@AllArgsConstructor

public class StarterConfiguration {
    private final Environment environment;

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName(environment.getRequiredProperty("spring.datasource.driverClassName"));
        ds.setUrl(environment.getRequiredProperty("spring.datasource.url"));
        ds.setUsername(environment.getRequiredProperty("spring.datasource.username"));
        ds.setPassword(environment.getRequiredProperty("spring.datasource.password"));
        return ds;
    }
//    @Bean
//    public EntityManagerFactory entityManagerFactory(DataSource dataSource) {
//        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
//        vendorAdapter.setDatabase(Database.POSTGRESQL);
//        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
//        factory.setJpaVendorAdapter(vendorAdapter);
//        factory.setPackagesToScan("com/exposit/persistence/entity");
//        factory.setDataSource(dataSource);
//        factory.afterPropertiesSet();
//        return factory.getObject();
//    }
}
