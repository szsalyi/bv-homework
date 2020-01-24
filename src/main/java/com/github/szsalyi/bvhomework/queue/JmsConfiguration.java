package com.github.szsalyi.bvhomework.queue;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.jms.Queue;

@Configuration
public class JmsConfiguration {

    @Bean
    public Queue queue() {
        return new ActiveMQQueue("ws-queue");
    }
}
