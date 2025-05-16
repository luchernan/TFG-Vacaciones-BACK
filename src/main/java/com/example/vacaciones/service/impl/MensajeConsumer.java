package com.example.vacaciones.service.impl;

import com.example.vacaciones.dao.MensajeRepository;
import com.example.vacaciones.dao.UsuarioRepository;
import com.example.vacaciones.dao.ViajeRepository;
import com.example.vacaciones.entity.Mensaje;
import com.example.vacaciones.entity.Usuario;
import com.example.vacaciones.entity.Viaje;
import com.example.vacaciones.seguridad.RabbitConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;

@Service
public class MensajeConsumer {

    private final MensajeRepository mensajeRepository;
    private final UsuarioRepository usuarioRepository;
    private final ViajeRepository viajeRepository;

    public MensajeConsumer(MensajeRepository mensajeRepository,
                           UsuarioRepository usuarioRepository,
                           ViajeRepository viajeRepository) {
        this.mensajeRepository = mensajeRepository;
        this.usuarioRepository = usuarioRepository;
        this.viajeRepository = viajeRepository;
    }

    @RabbitListener(queues = RabbitConfig.MENSAJE_QUEUE)
    public void recibirMensaje(MensajeRequest request) {
        Usuario remitente = usuarioRepository.findById(request.getRemitenteId()).orElseThrow();
        Usuario destinatario = usuarioRepository.findById(request.getDestinatarioId()).orElseThrow();
        Viaje viaje = viajeRepository.findById(request.getViajeId()).orElseThrow();

        Mensaje mensaje = new Mensaje();
        mensaje.setRemitente(remitente);
        mensaje.setDestinatario(destinatario);
        mensaje.setViaje(viaje);
        mensaje.setContenido(request.getContenido());
        mensaje.setFechaEnvio(LocalDateTime.from(Instant.now()));
        mensaje.setLeido(false);

        mensajeRepository.save(mensaje);
    }
}
