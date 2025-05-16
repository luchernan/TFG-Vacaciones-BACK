package com.example.vacaciones.seguridad;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.core.Queue;
@Configuration
public class RabbitConfig {
    public static final String MENSAJE_QUEUE = "mensajes.queue";

    @Bean
    public Queue mensajeQueue() {
        return new Queue(MENSAJE_QUEUE, false);
    }
}
