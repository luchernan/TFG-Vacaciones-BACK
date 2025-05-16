package com.example.vacaciones.service.impl;

import com.example.vacaciones.dao.MensajeRepository;
import com.example.vacaciones.dao.UsuarioRepository;
import com.example.vacaciones.dao.ViajeRepository;
import com.example.vacaciones.dto.MensajeDto;
import com.example.vacaciones.entity.Mensaje;
import com.example.vacaciones.entity.Usuario;
import com.example.vacaciones.entity.Viaje;
import com.example.vacaciones.service.MensajeService;
import com.example.vacaciones.service.UsuarioService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class MensajeServiceImpl implements MensajeService {

    @Autowired
    private MensajeRepository mensajeRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ViajeRepository viajeRepository;

    @Override
    public void enviarMensaje(MensajeDto mensajeDto) {
        // Convertir el MensajeDto a la entidad Mensaje
        Mensaje mensaje = new Mensaje();

        // Obtener el remitente como un objeto Usuario
        Usuario remitente = usuarioRepository.findById(mensajeDto.getRemitenteId())
                .orElseThrow(() -> new RuntimeException("Usuario remitente no encontrado"));

        // Obtener el destinatario como un objeto Usuario
        Usuario destinatario = usuarioRepository.findById(mensajeDto.getDestinatarioId())
                .orElseThrow(() -> new RuntimeException("Usuario destinatario no encontrado"));

        // Obtener el viaje como un objeto Viaje
        Viaje viaje = viajeRepository.findById(mensajeDto.getViajeId())
                .orElseThrow(() -> new RuntimeException("Viaje no encontrado"));

        // Asignar los valores al objeto Mensaje
        mensaje.setRemitente(remitente);  // Establece el remitente como un objeto Usuario
        mensaje.setDestinatario(destinatario);  // Establece el destinatario como un objeto Usuario
        mensaje.setViaje(viaje);  // Establece el viaje como un objeto Viaje
        mensaje.setContenido(mensajeDto.getContenido());

        // Usar LocalDateTime en vez de Timestamp
        mensaje.setFechaEnvio(mensajeDto.getFechaEnvio());

        // Asegúrate de que en tu DTO tengas un método getter 'getLeido' correctamente definido
        mensaje.setLeido(mensajeDto.getLeido());

        // Guardar el mensaje en la base de datos
        mensajeRepository.save(mensaje);
    }

    @Override
    public List<MensajeDto> obtenerConversacionesUsuario(Long usuarioId) {
        List<Mensaje> mensajes = mensajeRepository.findByRemitenteIdOrDestinatarioId(usuarioId, usuarioId);
        return mensajes.stream().map(this::convertirADto).toList();
    }

    @Override
    public List<MensajeDto> obtenerMensajesEntreUsuarios(Long usuario1Id, Long usuario2Id, Long viajeId) {
        return mensajeRepository.findAll().stream()
                .filter(m -> (
                        (m.getRemitente().getId().equals(usuario1Id) && m.getDestinatario().getId().equals(usuario2Id)) ||
                                (m.getRemitente().getId().equals(usuario2Id) && m.getDestinatario().getId().equals(usuario1Id))
                ) && m.getViaje().getId().equals(viajeId))
                .map(this::convertirADto).toList();
    }

    private MensajeDto convertirADto(Mensaje m) {
        MensajeDto dto = new MensajeDto();
        dto.setId(m.getId());
        dto.setRemitenteId(m.getRemitente().getId());  // Extraemos el ID del remitente
        dto.setDestinatarioId(m.getDestinatario().getId());  // Extraemos el ID del destinatario
        dto.setViajeId(m.getViaje().getId());  // Extraemos el ID del viaje
        dto.setContenido(m.getContenido());
        dto.setFechaEnvio(m.getFechaEnvio());
        dto.setLeido(m.isLeido());
        return dto;
    }
}



