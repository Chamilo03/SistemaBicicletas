package co.edu.unbosque.service;

import co.edu.unbosque.dto.NotificacionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * Servicio encargado de gestionar el envío de correos electrónicos.
 */
@Service
public class NotificacionService {

    @Autowired
    private JavaMailSender mailSender;

    /**
     * Envía un correo electrónico simple al usuario.
     * @param notificacionDTO información del correo.
     */
    public void enviarCorreo(NotificacionDTO notificacionDTO) {
        SimpleMailMessage mensaje = new SimpleMailMessage();
        mensaje.setTo(notificacionDTO.getCorreoDestino());
        mensaje.setSubject(notificacionDTO.getAsunto());
        mensaje.setText(notificacionDTO.getMensaje());
        mailSender.send(mensaje);
    }
}