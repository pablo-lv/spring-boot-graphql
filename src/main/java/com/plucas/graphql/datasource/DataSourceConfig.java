package com.plucas.graphql.datasource;

import com.github.javafaker.Faker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataSourceConfig {

    @Bean
    public Faker faker() {
        return new Faker();
    }
}
