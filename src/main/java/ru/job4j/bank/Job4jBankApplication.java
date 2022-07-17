package ru.job4j.bank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@SpringBootApplication
public class Job4jBankApplication {

    public static void main(String[] args) {
        SpringApplication.run(Job4jBankApplication.class, args);
    }

}
