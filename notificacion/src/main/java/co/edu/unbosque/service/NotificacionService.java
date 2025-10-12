package co.edu.unbosque.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import co.edu.unbosque.dto.NotificacionDTO;
import co.edu.unbosque.model.Notificacion;
import co.edu.unbosque.repository.NotificacionRepository;

/**
 * Servicio encargado de gestionar el envío de correos electrónicos.
 */
@Service
public class NotificacionService {

    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private NotificacionRepository notificacionRepository;
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
    public void guardarNotificacion(Notificacion notificacion) {
        notificacionRepository.save(notificacion);
    }
    public void enviarYGuardar(NotificacionDTO dto) {
        try {
            enviarCorreo(dto);
            guardarNotificacion(new Notificacion(dto.getCorreoDestino(), dto.getAsunto(), dto.getMensaje(), true));
        } catch (Exception e) {
            guardarNotificacion(new Notificacion(dto.getCorreoDestino(), dto.getAsunto(), dto.getMensaje(), false));
            throw e;
        }
    }
}