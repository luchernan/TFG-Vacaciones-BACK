package com.example.vacaciones.rest;

//@org.springframework.web.bind.annotation.RestController
//@RequestMapping("/api/mensajes")
//public class MensajeController {
//
//    @Autowired
//    private MensajeService mensajeService;
//
//    @Autowired
//    private MensajeProducer mensajeProducer;
//
//    // Endpoint para enviar un mensaje
//    @PostMapping
//    public ResponseEntity<Void> enviarMensaje(@RequestBody MensajeRequest request, Principal principal) {
//        // Aquí puedes obtener el ID del usuario logueado con principal.getName() o principal.getName()
//        Integer remitenteId = Integer.parseInteger(principal.getName()); // Suponiendo que el nombre es el ID del usuario
//
//        // Establecer el remitenteId en el MensajeRequest
//        request.setRemitenteId(remitenteId);
//
//        // Enviar mensaje a través de RabbitMQ
//        mensajeProducer.enviarMensaje(request);
//
//        return ResponseEntity.accepted().build();
//    }
//
//    // Endpoint para obtener todas las conversaciones de un usuario (por ID)
//    @GetMapping("/conversaciones/{usuarioId}")
//    public ResponseEntity<List<MensajeDto>> obtenerConversaciones(@PathVariable Long usuarioId) {
//        List<MensajeDto> conversaciones = mensajeService.obtenerConversacionesUsuario(usuarioId);
//        return ResponseEntity.ok(conversaciones);
//    }
//
//    // Endpoint para obtener los mensajes entre dos usuarios en un viaje específico
//    @GetMapping("/mensajes")
//    public ResponseEntity<List<MensajeDto>> obtenerMensajes(
//            @RequestParam Long usuario1Id,
//            @RequestParam Long usuario2Id,
//            @RequestParam Long viajeId) {
//        List<MensajeDto> mensajes = mensajeService.obtenerMensajesEntreUsuarios(usuario1Id, usuario2Id, viajeId);
//        return ResponseEntity.ok(mensajes);
//    }
//}