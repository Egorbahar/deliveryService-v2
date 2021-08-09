package com.exposit.persistence;

import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration("persistenceConfigurationStarter")
@ComponentScan(basePackages = {"com.exposit.persistence"})
@EnableJpaRepositories(basePackages = "com.exposit.persistence.repository")
@EntityScan(basePackages = "com.exposit.persistence.model")
@EnableTransactionManagement
@AutoConfigureBefore({com.exposit.core.StarterConfig.class})
public class StarterConfiguration {
}
