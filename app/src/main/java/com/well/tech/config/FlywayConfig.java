package com.well.tech.config;

import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class FlywayConfig {

    @Bean(initMethod = "migrate")
    public Flyway flyway(DataSource dataSource) {
        return Flyway.configure()
                .dataSource(dataSource)
                .locations("classpath:db/migration")
                .baselineOnMigrate(true)
                .load();
    }

    @Bean
    public static BeanFactoryPostProcessor flywayEntityManagerFactoryDependencyPostProcessor() {
        return beanFactory -> {
            if (beanFactory.containsBean("entityManagerFactory")) {
                beanFactory.getBeanDefinition("entityManagerFactory")
                        .setDependsOn("flyway");
            }
        };
    }
}