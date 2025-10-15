package co.edu.unbosque.usuario.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import co.edu.unbosque.usuario.model.Notificacion;
import co.edu.unbosque.usuario.model.Usuario;
import co.edu.unbosque.usuario.repository.UsuarioRepository;
import jakarta.transaction.Transactional;

@Service
public class UsuarioService {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private UsuarioRepository uRepo;

	public List<Notificacion> getNotificaciones(Long usuarioId){
		List<Notificacion> notificaciones = restTemplate.getForObject("http://localhost:8083/notificacion/usuario/"+ usuarioId, List.class);
		return notificaciones;
	}

	@Autowired
	private PasswordEncoder cifrador;

	public Usuario crearUsuario(String nombreCompleto, String tipoDocumento, String numeroDocumento, String email,
			String telefono, String contrasena, String estadoCuenta, LocalDate fechaRegistro, double saldo,
			String rol) {

		String contrasenaCifrada = cifrador.encode(contrasena);

		Usuario u = new Usuario(nombreCompleto, tipoDocumento, numeroDocumento, email, telefono, contrasenaCifrada,
				estadoCuenta, fechaRegistro, rol);

		return uRepo.save(u);
	}

	public List<Usuario> findAll() {
		return uRepo.findAll();
	}

	public void deleteById(Long id) {
		uRepo.deleteById(id);
	}

	public Usuario getUsuarioById(Long id){
		return uRepo.findById(id).orElse(null);
	}

	public boolean findById(Long id) {
		return uRepo.existsById(id);
	}

	@Transactional
	public Usuario actualizar(Long id, Usuario nuevo) {
		Usuario u = uRepo.findById(id).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
		u.setNombreCompleto(nuevo.getNombreCompleto());
		u.setTipoDocumento(nuevo.getTipoDocumento());
		u.setNumeroDocumento(nuevo.getNumeroDocumento());
		u.setEmail(nuevo.getEmail());
		u.setTelefono(nuevo.getTelefono());
		u.setContrasena(cifrador.encode(nuevo.getContrasena()));
		u.setEstadoCuenta(nuevo.getEstadoCuenta());
		u.setFechaRegistro(nuevo.getFechaRegistro());
		u.setRol(nuevo.getRol());

		return uRepo.save(u);

	}

	public boolean login(String email, String contrasena) {
		for (Usuario u : findAll()) {
			if (u.getEmail().equals(email)) {
				if (cifrador.matches(contrasena, u.getContrasena())) {
					return true;
				} else {
					return false;
				}

			}
		}
		return false;
	}

	@Transactional
	public Usuario recargarSaldo(Long id, double monto) {
		Usuario usuario = uRepo.findById(id).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
		usuario.setSaldo(usuario.getSaldo() + monto);
		return uRepo.save(usuario);
	}

}
