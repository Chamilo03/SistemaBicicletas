package co.edu.unbosque.dto;

public class NotificacionDTO {

    private String correoDestino;
    private String asunto;
    private String mensaje;

    public NotificacionDTO() {}

    public NotificacionDTO(String correoDestino, String asunto, String mensaje) {
        this.correoDestino = correoDestino;
        this.asunto = asunto;
        this.mensaje = mensaje;
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
}
