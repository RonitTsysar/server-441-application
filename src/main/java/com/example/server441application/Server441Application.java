package com.example.server441application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication // is a meta-annotation that pulls in component scanning, autoconfiguration, and property support.
public class Server441Application
{
    public static void main(String... args) {
        SpringApplication.run(Server441Application.class, args);
    }
}