package com.example.server441application.dao;

import lombok.extern.slf4j.Slf4j;
import com.example.server441application.model.Contact;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j //is a Lombok annotation to autocreate an Slf4j-based LoggerFactory as log,
       // allowing us to log these newly created "contact".
public class LoadDatabase
{
    @Bean
    CommandLineRunner initDatabase(IContactRepository repository)
    {
        return args -> {
            log.info("Preloading " + repository.save(new Contact("Ronit Tsysar", "0528633998")));
            log.info("Preloading " + repository.save(new Contact("Asaf Tsysar", "0523646042")));
        };
    }
}