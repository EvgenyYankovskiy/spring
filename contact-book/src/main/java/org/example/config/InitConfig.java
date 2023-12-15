package org.example.config;

import org.example.ContactsBook;
import org.example.FileWorker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

@Configuration
@Profile("init")
@PropertySource("classpath:application-init.properties")
public class InitConfig {

    @Bean
    public ContactsBook contactsBook() {
        return new ContactsBook();
    }

    @Bean
    public FileWorker fileWorker() {
        return new FileWorker();
    }
}
