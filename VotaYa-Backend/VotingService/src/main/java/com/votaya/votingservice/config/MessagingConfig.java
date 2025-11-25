package com.votaya.votingservice.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessagingConfig {

    public static final String VOTES_QUEUE = "votes-queue";

    @Bean
    public Queue votesQueue() {
        return new Queue(VOTES_QUEUE, true); // 'true' makes the queue durable
    }
}
