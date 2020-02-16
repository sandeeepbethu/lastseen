package com.reliance.assessment.lastseen.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties("dev")
@Getter
@Setter
public class AppConfiguration {
    private String[] person_list;
    private String[] last_visited_list;
}
