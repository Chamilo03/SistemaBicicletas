package co.edu.unbosque.usuario.dto;

import java.time.LocalDate;

public class UsuarioDTO {

	private Long id;
	private String nombreCompleto;
	private String tipoDocumento;
	private String numeroDocumento;
	private String email;
	private String telefono;
	private String contrasena;
	private String estadoCuenta;
	private LocalDate fechaRegistro;
	private double saldo;
	private String rol;

	public UsuarioDTO() {
		// TODO Auto-generated constructor stub
	}

	public UsuarioDTO(Long id, String nombreCompleto, String tipoDocumento, String numeroDocumento, String email,
			String telefono, String contrasena, String estadoCuenta, LocalDate fechaRegistro, double saldo,
			String rol) {
		super();
		this.id = id;
		this.nombreCompleto = nombreCompleto;
		this.tipoDocumento = tipoDocumento;
		this.numeroDocumento = numeroDocumento;
		this.email = email;
		this.telefono = telefono;
		this.contrasena = contrasena;
		this.estadoCuenta = estadoCuenta;
		this.fechaRegistro = fechaRegistro;
		this.saldo = saldo;
		this.rol = rol;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombreCompleto() {
		return nombreCompleto;
	}

	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}

	public String getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public String getEstadoCuenta() {
		return estadoCuenta;
	}

	public void setEstadoCuenta(String estadoCuenta) {
		this.estadoCuenta = estadoCuenta;
	}

	public LocalDate getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(LocalDate fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

}
