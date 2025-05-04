package com.example.vacaciones.rest;


import com.example.vacaciones.dao.UsuarioRepository;
import com.example.vacaciones.dto.*;
import com.example.vacaciones.entity.Usuario;
import com.example.vacaciones.service.*;
import com.example.vacaciones.service.impl.FotoperfilServiceImpl;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@org.springframework.web.bind.annotation.RestController
    @RequestMapping("${spring.data.rest.base-path:}")
    public class RestController {

        private final DestinoService destinoService;
        private final ViajeService viajeService;
        private final FotoperfilService fotoperfilService;
        private final MensajeService mensajeService;
    private final HttpSession session;
    private UsuarioService usuarioService;


    @Autowired
    public RestController(DestinoService destinoService, ViajeService viajeService, UsuarioService usuarioService ,MensajeService mensajeService,HttpSession session, FotoperfilService fotoperfilService) {
        this.destinoService = destinoService;
        this.viajeService = viajeService;
        this.fotoperfilService = fotoperfilService;
        this.mensajeService = mensajeService;
        this.usuarioService = usuarioService;
        this.session = session;
    }
    @GetMapping("/destino/{destinoId}")
    public ResponseEntity<List<ViajeDto>> getViajesPorDestino(@PathVariable Integer destinoId) {
        List<ViajeDto> viajes = viajeService.findByDestinoId(destinoId);


        return ResponseEntity.ok(viajes);
    }

    /**
     * Obtener todos los destinos
     * Ejemplo: GET http://localhost:8080/api/destinos
     */
    @GetMapping
    public ResponseEntity<List<DestinoDto>> getAllDestinos() {
        List<DestinoDto> destinos = destinoService.findAll();
        return ResponseEntity.ok(destinos);
    }

    /**
     * Obtener un destino por ID
     * Ejemplo: GET http://localhost:8080/api/destinos/1
     */
    @GetMapping("/{id}")
    public ResponseEntity<DestinoDto> getDestinoById(@PathVariable Integer id) {
        DestinoDto destino = destinoService.findById(id);
        return ResponseEntity.ok(destino);
    }

    /**
     * Crear un nuevo destino
     * Ejemplo: POST http://localhost:8080/api/destinos
     * Body (JSON):
     * {
     *     "nombre": "Nuevo Destino",
     *     "pais": "España",
     *     "descripcion": "Descripción del nuevo destino",
     *     "wikipediaSlug": "nuevo-destino",
     *     "fotoDestino": "https://ejemplo.com/foto.jpg"
     * }
     */
    @PostMapping
    public ResponseEntity<DestinoDto> createDestino(@RequestBody DestinoDto destinoDto) {
        DestinoDto nuevoDestino = destinoDto; // Simulación - reemplazar con llamada al servicio
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoDestino);
    }

    /**
     * Actualizar un destino existente
     * Ejemplo: PUT http://localhost:8080/api/destinos/1
     * Body (JSON): igual que en POST
     */
    @PutMapping("/{id}")
    public ResponseEntity<DestinoDto> updateDestino(@PathVariable Integer id, @RequestBody DestinoDto destinoDto) {
        DestinoDto destinoActualizado = destinoDto.withId(id);
        return ResponseEntity.ok(destinoActualizado);
    }

    /**
     * Eliminar un destino
     * Ejemplo: DELETE http://localhost:8080/api/destinos/1
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDestino(@PathVariable Integer id) {
        return ResponseEntity.noContent().build();
    }

    /**
     * Buscar destinos por país
     * Ejemplo: GET http://localhost:8080/api/destinos/buscar?pais=España
     */
    @GetMapping("/buscar")
    public ResponseEntity<List<DestinoDto>> searchByPais(@RequestParam String pais) {
        List<DestinoDto> resultados = destinoService.findAll().stream()
                .filter(d -> d.getPais().equalsIgnoreCase(pais))
                .toList();
        return ResponseEntity.ok(resultados);
    }


    /**
     * Obtener todos los viajes
     * Ejemplo: GET http://localhost:8080/api/viajes
     */
    @GetMapping("/viajes")
    public ResponseEntity<List<ViajeDto>> getAllViajes() {
        List<ViajeDto> viajes = viajeService.findAll();
        return ResponseEntity.ok(viajes);
    }

    /**
     * Obtener un viaje por ID
     * Ejemplo: GET http://localhost:8080/api/viajes/1
     */
    @GetMapping("/viajes/{id}")
    public ResponseEntity<ViajeDto> getViajeById(@PathVariable Integer id) {
        ViajeDto viaje = viajeService.findById(id);
        return ResponseEntity.ok(viaje);
    }

    /**
     * Crear un nuevo viaje
     * Ejemplo: POST http://localhost:8080/api/viajes
     * Body (JSON):
     * {
     *     "usuarioId": 1,
     *     "destinoId": 2,
     *     "fechaInicio": "2025-06-01",
     *     "fechaFin": "2025-06-10"
     * }
     */
    @PostMapping("/viajes")
    public ResponseEntity<ViajeDto> createViaje(@RequestBody ViajeDto viajeDto) {
        ViajeDto nuevoViaje = viajeService.create(viajeDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoViaje);
    }

    /**
     * Actualizar un viaje existente
     * Ejemplo: PUT http://localhost:8080/api/viajes/1
     */
    @PutMapping("/viajes/{id}")
    public ResponseEntity<ViajeDto> updateViaje(@PathVariable Integer id, @RequestBody ViajeDto viajeDto) {
        ViajeDto viajeActualizado = viajeService.update(id, viajeDto);
        return ResponseEntity.ok(viajeActualizado);
    }
    /**
     * Obtener todas las fotos de perfil
     * Ejemplo: GET http://localhost:8080/api/fotoperfil
     */
    @GetMapping("/fotoperfil")
    public ResponseEntity<List<FotoperfilDto>> getAllFotoPerfil() {
        List<FotoperfilDto> fotos = fotoperfilService.findAll();
        return ResponseEntity.ok(fotos);
    }

    /**
     * Obtener una foto de perfil por ID
     * Ejemplo: GET http://localhost:8080/api/fotoperfil/1
     */
    @GetMapping("/fotoperfil/{id}")
    public ResponseEntity<FotoperfilDto> getFotoPerfilById(@PathVariable Integer id) {
        FotoperfilDto foto = fotoperfilService.findById(id);
        return ResponseEntity.ok(foto);
    }

    /**
     * Buscar una foto de perfil por URL
     * Ejemplo: GET http://localhost:8080/api/fotoperfil/buscar?url=https://ejemplo.com/imagen.jpg
     */
    @GetMapping("/fotoperfil/buscar")
    public ResponseEntity<FotoperfilDto> findFotoPerfilByUrl(@RequestParam String url) {
        FotoperfilDto dto = fotoperfilService.findByUrl(url);
        return ResponseEntity.ok(dto);
    }
    /**
     * Obtener todos los mensajes
     * Ejemplo: GET http://localhost:8080/api/mensajes
     */
    @GetMapping("/mensajes")
    public ResponseEntity<List<MensajeDto>> getAllMensajes() {
        List<MensajeDto> mensajes = mensajeService.findAll();
        return ResponseEntity.ok(mensajes);
    }

    /**
     * Obtener un mensaje por ID
     * Ejemplo: GET http://localhost:8080/api/mensajes/1
     */
    @GetMapping("/mensajes/{id}")
    public ResponseEntity<MensajeDto> getMensajeById(@PathVariable Integer id) {
        MensajeDto mensaje = mensajeService.findById(id);
        return ResponseEntity.ok(mensaje);
    }

    /**
     * Enviar un nuevo mensaje
     * Ejemplo: POST http://localhost:8080/api/mensajes
     * Body (JSON):
     * {
     *     "remitenteId": 1,
     *     "destinatarioId": 2,
     *     "contenido": "Hola, ¿qué tal?"
     * }
     */
    @PostMapping("/mensajes")
    public ResponseEntity<MensajeDto> createMensaje(@RequestBody MensajeDto mensajeDto) {
        MensajeDto nuevoMensaje = mensajeService.create(mensajeDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoMensaje);
    }
    /**
     * Crear un nuevo usuario
     * Ejemplo: POST http://localhost:8080/api/usuarios
     * Body (JSON):
     * {
     *   "email": "ejemplo@correo.com",
     *   "password": "1234",
     *   "fechaNacimiento": "2000-01-01",
     *   "nombre": "Juan",
     *   "genero": "hombre",
     *   "descripcion": "Hola, soy Juan.",
     *   "tipoUsuario": "viajero",
     *   "idioma": "Español",
     *   "pais": "España",
     *   "ciudadLocal": "Madrid",
     *   "fotoPerfil": "https://ejemplo.com/foto.jpg"
     * }
     */
    @PostMapping("/usuarios")
    public ResponseEntity<UsuarioDto> createUsuario(@RequestBody UsuarioDto usuarioDto) {
        UsuarioDto nuevoUsuario = usuarioService.create(usuarioDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoUsuario);
    }

    /**
     * Inicio de sesión
     *
     */
    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UsuarioDto credenciales, HttpSession session) {
        Optional<UsuarioDto> usuarioOpt = usuarioService.findByEmailAndPassword(credenciales.getEmail(), credenciales.getPassword());

        if (usuarioOpt.isPresent()) {
            session.setAttribute("usuario", usuarioOpt.get());
            return ResponseEntity.ok(usuarioOpt.get());
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales inválidas");
        }
    }


    @GetMapping("/usuario-logueado")
    public ResponseEntity<?> obtenerUsuarioLogueado(HttpSession session) {
        UsuarioDto usuario = (UsuarioDto) session.getAttribute("usuario");
        if (usuario != null) {
            return ResponseEntity.ok(usuario);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("No hay sesión activa");
        }
    }
    /**
     * Verificar si hay sesión activa
     * Ejemplo: GET http://localhost:8080/api/check-session
     */
    @GetMapping("/check-session")
    public ResponseEntity<Boolean> checkSession() {
        // Verifica si hay un usuario en la sesión
        Object usuario = session.getAttribute("usuario");
        if (usuario != null) {
            return ResponseEntity.ok(true); // La sesión está activa
        } else {
            return ResponseEntity.ok(false); // No hay sesión activa
        }
    }
    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpSession session) {
        session.invalidate();
        return ResponseEntity.ok("Sesión cerrada");
    }

    /**
     * Filtrar usuarios por género, rango de edad e idioma
     * Ejemplo: GET http://localhost:8080/api/usuarios/filtrar?genero=Hombre&edadMinima=20&edadMaxima=30&idioma=Español
     */
    @GetMapping("/usuarios/filtrar")
    public ResponseEntity<List<UsuarioDto>> filtrarUsuarios(
            @RequestParam(required = false) String genero,
            @RequestParam(required = false) Integer edadMinima,
            @RequestParam(required = false) Integer edadMaxima,
            @RequestParam(required = false) String idioma) {

        List<UsuarioDto> usuariosFiltrados = usuarioService.filtrarUsuarios(genero, edadMinima, edadMaxima, idioma);
        return ResponseEntity.ok(usuariosFiltrados);
    }

    /**
     * Obtener edad del usuario por ID
     * Ejemplo: GET http://localhost:8080/api/usuarios/edad/1
     */
    @GetMapping("/usuarios/edad/{id}")
    public ResponseEntity<Integer> getEdad(@PathVariable Integer id) {
        int edad = usuarioService.calcularEdad(id);
        return ResponseEntity.ok(edad);
    }
    /**
     * Alternar el tipo de usuario (local <-> viajero)
     * Ejemplo: PUT http://localhost:8080/api/usuarios/tipo/{id}
     */
    @PutMapping("/usuarios/tipo/{id}")
    public ResponseEntity<UsuarioDto> alternarTipoUsuario(@PathVariable Integer id) {
        UsuarioDto usuarioActualizado = usuarioService.alternarTipoUsuario(id);
        return ResponseEntity.ok(usuarioActualizado);
    }

    @GetMapping("/usuarios/{id}")
    public ResponseEntity<UsuarioDto> getUsuarioById(@PathVariable Long id) {
        UsuarioDto usuario = usuarioService.findById(id);
        return ResponseEntity.ok(usuario);
    }



}
