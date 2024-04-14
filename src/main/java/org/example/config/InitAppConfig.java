package org.example.config;

import org.example.EnvContact;
import org.example.IniEnvContact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application-init.properties")
@org.springframework.context.annotation.Profile("init")
public class InitAppConfig {
    @Bean
    public EnvContact envContact() {
        return new IniEnvContact();
    }
}
