package com.votaya.ledgerservice;

import org.springframework.amqp.core.Queue;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class LedgerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(LedgerServiceApplication.class, args);
    }

    // ESTO SOLUCIONA EL ERROR:
    // Al ponerlo aquí, Spring Boot lo lee obligatoriamente y crea la cola.
    @Bean
    public Queue votesQueue() {
        return new Queue("votes-queue", true);
    }
}