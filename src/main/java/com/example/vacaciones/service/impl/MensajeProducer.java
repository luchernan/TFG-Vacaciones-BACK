package com.example.vacaciones.service.impl;

import com.example.vacaciones.seguridad.RabbitConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class MensajeProducer {
    private final RabbitTemplate rabbitTemplate;

    public MensajeProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void enviarMensaje(MensajeRequest mensajeRequest) {
        rabbitTemplate.convertAndSend(RabbitConfig.MENSAJE_QUEUE, mensajeRequest);
    }
}

