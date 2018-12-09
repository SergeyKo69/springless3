package ru.kogut.enterprise.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan("ru.kogut.enterprise")
@Import(DataSourceConfiguration.class)
public class ApplicationConfiguration {
}
