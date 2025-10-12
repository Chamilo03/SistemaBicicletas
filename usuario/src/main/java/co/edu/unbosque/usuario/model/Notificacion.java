package co.edu.unbosque.usuario.model;

import java.time.LocalDateTime;

public class Notificacion {

    private Long id;

    private String correoDestino;

    private String asunto;

    private String mensaje;

    private LocalDateTime fechaEnvio;

    private boolean enviado;

    public Notificacion(String correoDestino, String asunto, String mensaje, boolean enviado) {
        this.correoDestino = correoDestino;
        this.asunto = asunto;
        this.mensaje = mensaje;
        this.enviado = enviado;
        this.fechaEnvio = LocalDateTime.now();
    }

    // ---------- Getters y Setters ----------

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCorreoDestino() {
        return correoDestino;
    }

    public void setCorreoDestino(String correoDestino) {
        this.correoDestino = correoDestino;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public LocalDateTime getFechaEnvio() {
        return fechaEnvio;
    }

    public void setFechaEnvio(LocalDateTime fechaEnvio) {
        this.fechaEnvio = fechaEnvio;
    }

    public boolean isEnviado() {
        return enviado;
    }

    public void setEnviado(boolean enviado) {
        this.enviado = enviado;
    }

    public Notificacion(){
        super();
    }
}