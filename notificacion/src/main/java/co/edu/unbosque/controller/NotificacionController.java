package co.edu.unbosque.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.unbosque.dto.NotificacionDTO;
import co.edu.unbosque.service.NotificacionService;

/**
 * Controlador para el envío de notificaciones por correo electrónico.
 */
@RestController
@RequestMapping("/notificacion")
@CrossOrigin(origins = "*")
public class NotificacionController {

    @Autowired
    private NotificacionService notificacionService;

    @PostMapping("/enviar")
    public ResponseEntity<String> enviar(@RequestBody NotificacionDTO dto) {
        try {
            notificacionService.enviarCorreo(dto);
            return ResponseEntity.ok("Correo enviado correctamente a " + dto.getCorreoDestino());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al enviar el correo: " + e.getMessage());
        }
    }

    @PostMapping("/crear")
    public ResponseEntity<String> crear(@RequestBody NotificacionDTO dto) {
        try {
            notificacionService.enviarYGuardar(dto);
            return ResponseEntity.ok("Notificación enviada y guardada correctamente");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al enviar o guardar la notificación: " + e.getMessage());
        }
    }
}