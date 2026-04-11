package com.agoda.tpm.observability.controller;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class MessageController {

    private final RabbitTemplate rabbitTemplate;

    @Value("${rabbitmq.exchange.name:javaguides_exchange}")
    private String exchange;

    @Value("${rabbitmq.routing.key:javaguides_routing_key}")
    private String routingKey;

    public MessageController(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @GetMapping("/publish")
    public String sendMessage(@RequestParam("message") String message) {
        rabbitTemplate.convertAndSend(exchange, routingKey, message);
        return "Message sent to RabbitMQ -> " + message;
    }
}