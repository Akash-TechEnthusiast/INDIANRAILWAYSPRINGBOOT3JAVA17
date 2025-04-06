package com.india.railway;

import java.util.Properties;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@EnableTransactionManagement
// @EnableJpaRepositories(basePackages = "com.india.railway.repository")
// @EnableJpaRepositories(basePackages = "com.india.railway")
@EnableJpaRepositories(basePackages = "com.india.railway.repository")
@EnableMongoRepositories(basePackages = "com.india.railway.repository")
@EnableRedisRepositories(basePackages = "com.india.railway.repository")
@ComponentScan(basePackages = { "com.india.railway" })
@EntityScan(basePackages = "com.india.railway.model")

@EnableElasticsearchRepositories(basePackages = "com.india.railway.elasticrepo")
@Slf4j
public class IndianRailwayApplication {

    public static void main(String[] args) {
        SpringApplication.run(IndianRailwayApplication.class, args);
        log.info("App started");
        log.error("App started");
        log.debug("App started");
        log.warn("App started");

    }

    @Bean
    public Session MailSenderSession() {
        final String username = "akash922.g@gmail.com";
        final String appPassword = "acfgjfvpuygcrisq"; // Generated App Password from

        // Setting up mail server properties
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        // Creating a new session with an authenticator
        Session session = Session.getInstance(props, new jakarta.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, appPassword);
            }
        });

        return session;

    }

}
