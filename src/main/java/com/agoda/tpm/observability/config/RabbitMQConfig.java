package com.agoda.tpm.observability.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Value("${rabbitmq.queue.name:javaguides_queue}")
    private String queue;

    @Value("${rabbitmq.exchange.name:javaguides_exchange}")
    private String exchange;

    @Value("${rabbitmq.routing.key:javaguides_routing_key}")
    private String routingKey;

    @Bean
    public Queue queue() { return new Queue(queue); }

    @Bean
    public TopicExchange exchange() { return new TopicExchange(exchange); }

    @Bean
    public Binding binding() {
        return BindingBuilder.bind(queue()).to(exchange()).with(routingKey);
    }
}