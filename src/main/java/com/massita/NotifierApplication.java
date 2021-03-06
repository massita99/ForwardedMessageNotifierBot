package com.massita;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.telegram.telegrambots.ApiContextInitializer;

import java.sql.SQLException;

@SpringBootApplication
@EnableScheduling
public class NotifierApplication {

    public static void main(String[] args) throws SQLException {
        ApiContextInitializer.init();
        SpringApplication.run(NotifierApplication.class, args);
    }
}
