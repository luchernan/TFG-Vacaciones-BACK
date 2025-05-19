package com.example.vacaciones.service.impl;

import com.example.vacaciones.dao.ComentarioRepository;
import com.example.vacaciones.dao.UsuarioRepository;
import com.example.vacaciones.dao.ViajeRepository;
import com.example.vacaciones.dto.ComentarioDto;
import com.example.vacaciones.entity.Comentario;
import com.example.vacaciones.entity.Usuario;
import com.example.vacaciones.entity.Viaje;
import com.example.vacaciones.service.ComentarioService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


    @Service
    public class ComentarioServiceImpl implements ComentarioService {

        private final ComentarioRepository comentarioRepositorio;
        private final UsuarioRepository usuarioRepositorio;
        private final ViajeRepository viajeRepositorio;
        private final ModelMapper modelMapper;

        public ComentarioServiceImpl(ComentarioRepository comentarioRepositorio, UsuarioRepository usuarioRepositorio, ViajeRepository viajeRepositorio, ModelMapper modelMapper) {
            this.comentarioRepositorio = comentarioRepositorio;
            this.usuarioRepositorio = usuarioRepositorio;
            this.viajeRepositorio = viajeRepositorio;
            this.modelMapper = modelMapper;
        }

        @Override
        public ComentarioDto crearComentario(ComentarioDto comentarioDto) {
            Optional<Usuario> usuarioOpt = usuarioRepositorio.findById(comentarioDto.getUsuarioId());
            Optional<Viaje> viajeOpt = viajeRepositorio.findById(comentarioDto.getViajeId());

            if (usuarioOpt.isEmpty() || viajeOpt.isEmpty()) {
                throw new RuntimeException("Usuario o viaje no encontrado");
            }

            Comentario comentario = modelMapper.map(comentarioDto, Comentario.class);
            comentario.setUsuario(usuarioOpt.get());
            comentario.setViaje(viajeOpt.get());

            Comentario guardado = comentarioRepositorio.save(comentario);
            return modelMapper.map(guardado, ComentarioDto.class);
        }

        @Override
        public List<ComentarioDto> obtenerComentariosPorViaje(Integer viajeId) {
            List<Comentario> comentarios = comentarioRepositorio.findByViajeId(viajeId);
            return comentarios.stream()
                    .map(c -> modelMapper.map(c, ComentarioDto.class))
                    .collect(Collectors.toList());
        }

        @Override
        public void eliminarComentario(Integer id) {
            comentarioRepositorio.deleteById(id);
        }
    }

