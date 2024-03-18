package org.contacts.config;

import org.contacts.ContactsMap;
import org.contacts.InitContactsMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application.properties")
@Profile("init")
public class InitAppConfig {

    @Bean
    public ContactsMap contactsMap() {
       return  new InitContactsMap();
    }
}
