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

    /**
     * Envía una notificación por correo electrónico al usuario.
     * @param notificacionDTO información del destinatario y mensaje.
     * @return mensaje de confirmación o error.
     */
    @PostMapping(path ="/enviar")
    public ResponseEntity<?> enviarNotificacion(@RequestBody NotificacionDTO notificacionDTO) {
        try {
            notificacionService.enviarCorreo(notificacionDTO);
            return ResponseEntity.ok("Correo enviado correctamente a " + notificacionDTO.getCorreoDestino());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al enviar el correo: " + e.getMessage());
        }
    }
}