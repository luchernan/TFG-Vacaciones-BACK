package com.example.vacaciones.service.impl;

import com.example.vacaciones.dao.UsuarioRepository;
import com.example.vacaciones.dto.UsuarioDto;
import com.example.vacaciones.entity.Usuario;
import com.example.vacaciones.exception.NotFoundException;
import com.example.vacaciones.service.StorageService;
import com.example.vacaciones.service.UsuarioService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final ModelMapper mapper;
    private final StorageService storage;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public void updateProfilePhoto(String email, MultipartFile file) throws IOException {
        Usuario u = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("No existe usuario: " + email));
        // Guardar fichero y obtener URL pública / ruta
        String fotoUrl = storage.store(file);
        u.setFotoPerfil(fotoUrl);
        usuarioRepository.save(u);
    }

    @Override
    @Transactional
    public UsuarioDto alternarTipoUsuario(Integer idUsuario) {
        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + idUsuario));

        if ("local".equals(usuario.getTipoUsuario())) {
            usuario.setTipoUsuario("viajero");
        } else if ("viajero".equals(usuario.getTipoUsuario())) {
            usuario.setTipoUsuario("local");
        } else {
            throw new RuntimeException("Tipo de usuario desconocido: " + usuario.getTipoUsuario());
        }

        Usuario actualizado = usuarioRepository.save(usuario);
        return mapper.map(actualizado, UsuarioDto.class);
    }


    @Autowired
    public UsuarioServiceImpl(UsuarioRepository usuarioRepository, StorageService storage, ModelMapper mapper) {
        this.usuarioRepository = usuarioRepository;
        this.mapper = mapper;
        this.storage = storage;
    }

    @Override
    public UsuarioDto findById(Long id) {
        Usuario usuario = usuarioRepository.findById(Math.toIntExact(id))
                .orElseThrow(() -> new NotFoundException("Usuario no encontrado con id: " + id));
        return mapper.map(usuario, UsuarioDto.class);
    }

    @Override
    public List<UsuarioDto> filtrarUsuarios(String genero, Integer edadMinima, Integer edadMaxima, String idioma) {
        List<Usuario> usuarios = usuarioRepository.findAll(); // Obtener todos los usuarios

        // Filtrado por género
        if (genero != null && !genero.isEmpty()) {
            usuarios = usuarios.stream()
                    .filter(usuario -> usuario.getGenero().equalsIgnoreCase(genero))
                    .collect(Collectors.toList());
        }

        // Filtrado por rango de edad
        if (edadMinima != null && edadMaxima != null) {
            usuarios = usuarios.stream()
                    .filter(usuario -> calcularEdad(usuario.getId()) >= edadMinima
                            && calcularEdad(usuario.getId()) <= edadMaxima)
                    .collect(Collectors.toList());
        }

        // Filtrado por idioma
        if (idioma != null && !idioma.isEmpty()) {
            usuarios = usuarios.stream()
                    .filter(usuario -> usuario.getIdioma().equalsIgnoreCase(idioma))
                    .collect(Collectors.toList());
        }

        // Convertir a DTO antes de devolver usando ModelMapper
        return usuarios.stream()
                .map(usuario -> mapper.map(usuario, UsuarioDto.class)) // Usamos mapper para convertir la entidad a DTO
                .collect(Collectors.toList());
    }

    @Override
    public UsuarioDto updateById(Integer id, UsuarioDto usuarioDto) {
        try {
            // Buscar el usuario existente por id
            Usuario usuarioExistente = usuarioRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado con id: " + id));

            // Actualizar solo los campos permitidos
            if (usuarioDto.getNombre() != null) {
                usuarioExistente.setNombre(usuarioDto.getNombre());
            }
            if (usuarioDto.getFechaNacimiento() != null) {
                usuarioExistente.setFechaNacimiento(usuarioDto.getFechaNacimiento());
            }
            if (usuarioDto.getGenero() != null) {
                usuarioExistente.setGenero(usuarioDto.getGenero());
            }
            if (usuarioDto.getDescripcion() != null) {
                usuarioExistente.setDescripcion(usuarioDto.getDescripcion());
            }
            if (usuarioDto.getInstagram() != null) {
                usuarioExistente.setInstagram(usuarioDto.getInstagram());
            }
            if (usuarioDto.getTipoUsuario() != null) {
                usuarioExistente.setTipoUsuario(usuarioDto.getTipoUsuario());
            }
            if (usuarioDto.getIdioma() != null) {
                usuarioExistente.setIdioma(usuarioDto.getIdioma());
            }
            if (usuarioDto.getPais() != null) {
                usuarioExistente.setPais(usuarioDto.getPais());
            }
            if (usuarioDto.getCiudadLocal() != null) {
                usuarioExistente.setCiudadLocal(usuarioDto.getCiudadLocal());
            }
            if (usuarioDto.getFotoPerfil() != null) {
                usuarioExistente.setFotoPerfil(usuarioDto.getFotoPerfil());
            }

            // Guardar los cambios
            Usuario usuarioActualizado = usuarioRepository.save(usuarioExistente);

            return new UsuarioDto(usuarioActualizado);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al actualizar el usuario: " + e.getMessage());
        }
    }
    @Override
    public UsuarioDto create(UsuarioDto usuarioDto) {
        try {
            // Creación del objeto Usuario manualmente
            Usuario usuario = new Usuario();
            usuario.setEmail(usuarioDto.getEmail());
//            usuario.setPassword(usuarioDto.getPassword());
            usuario.setPassword(passwordEncoder.encode(usuarioDto.getPassword()));
            usuario.setFechaNacimiento(usuarioDto.getFechaNacimiento());
            usuario.setNombre(usuarioDto.getNombre());
            usuario.setGenero(usuarioDto.getGenero());
            usuario.setDescripcion(usuarioDto.getDescripcion());
            usuario.setInstagram(usuarioDto.getInstagram());
            usuario.setTipoUsuario(usuarioDto.getTipoUsuario());
            usuario.setIdioma(usuarioDto.getIdioma());
            usuario.setPais(usuarioDto.getPais());
            usuario.setCiudadLocal(usuarioDto.getCiudadLocal());

            // Si fotoPerfil es null, asigna un valor por defecto
            if (usuarioDto.getFotoPerfil() == null) {
                usuario.setFotoPerfil("default.jpg"); // O cualquier valor predeterminado que desees
            } else {
                usuario.setFotoPerfil(usuarioDto.getFotoPerfil());
            }

            // Guardar el usuario en la base de datos
            usuarioRepository.save(usuario);

            // Convertir a UsuarioDto antes de devolverlo
            return new UsuarioDto(usuario);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }




    @Override
    public UsuarioDto login(String email, String password) {
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Email no registrado"));

        if (!passwordEncoder.matches(password, usuario.getPassword())) {
            throw new RuntimeException("Credenciales incorrectas");
        }

        return mapper.map(usuario, UsuarioDto.class);
    }


    @Override
    public int calcularEdad(Integer usuarioId) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (usuario.getFechaNacimiento() == null) return -1;
        return Period.between(usuario.getFechaNacimiento(), LocalDate.now()).getYears();
    }
//    @Override
//    public Optional<UsuarioDto> findByEmailAndPassword(String email, String password) {
//        return usuarioRepository.findByEmailAndPassword(email, password)
//                .map(usuario -> mapper.map(usuario, UsuarioDto.class));
//    }
}
