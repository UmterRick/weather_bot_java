package com.umterrick.weatherbot;

import com.umterrick.weatherbot.db.utils.HibernateSessionFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.umterrick.weatherbot")
public class WeatherBotApplication {

    public static void main(String[] args) {
        SpringApplication.run(WeatherBotApplication.class, args);
        HibernateSessionFactory.getSessionFactory();
        HibernateSessionFactory.shutdown();

    }
}
