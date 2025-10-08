package co.edu.unbosque.usuario.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import co.edu.unbosque.usuario.dto.UsuarioDTO;
import co.edu.unbosque.usuario.model.Usuario;
import co.edu.unbosque.usuario.service.UsuarioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/usuario")
@Tag(name = "Usuarios", description = "Operaciones relacionadas con los usuarios del sistema")
public class UsuarioController {

	@Autowired
	private UsuarioService service;

	@Operation(summary = "Listar todos los usuarios", description = "Obtiene una lista completa de los usuarios registrados")
	@ApiResponse(responseCode = "200", description = "Lista obtenida correctamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Usuario.class)))
	@GetMapping(path = "listarUsuarios")
	public ResponseEntity<List<Usuario>> listarUsuarios() {
		List<Usuario> usuarios = service.findAll();
		return ResponseEntity.ok(usuarios);
	}

	@Operation(summary = "Crear un nuevo usuario", description = "Registra un nuevo usuario en la base de datos")
	@ApiResponse(responseCode = "201", description = "Usuario creado exitosamente")
	@ApiResponse(responseCode = "400", description = "Datos inválidos", content = @Content)
	@PostMapping(path = "crearUsuario")
	public ResponseEntity<String> crearUsuario(@RequestBody UsuarioDTO u) {
		service.crearUsuario(u.getNombreCompleto(), u.getTipoDocumento(), u.getNumeroDocumento(), u.getEmail(),
				u.getTelefono(), u.getContrasena(), u.getEstadoCuenta(), u.getFechaRegistro(), u.getSaldo(),
				u.getRol());
		return ResponseEntity.status(HttpStatus.CREATED).body("Usuario creado con éxito");
	}

	@Operation(summary = "Eliminar un usuario", description = "Elimina un usuario existente por su ID")
	@ApiResponse(responseCode = "200", description = "Usuario eliminado correctamente")
	@ApiResponse(responseCode = "404", description = "Usuario no encontrado", content = @Content)
	@DeleteMapping(path = "eliminarUsuario/{id}")
	public ResponseEntity<String> eliminarUsuario(@PathVariable Long id) {

		if (!service.findById(id) || id == 1) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado");
		}
		service.deleteById(id);
		return ResponseEntity.status(HttpStatus.OK).body("Usuario eliminado con éxito");
	}

	@Operation(summary = "Actualizar usuario", description = "Actualiza la información de un usuario existente")
	@ApiResponse(responseCode = "200", description = "Usuario actualizado correctamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Usuario.class)))
	@ApiResponse(responseCode = "404", description = "Usuario no encontrado", content = @Content)
	@PutMapping(path = "actualizarUsuario/{id}")
	public ResponseEntity<Usuario> actualizarUsuario(@PathVariable Long id, @RequestBody UsuarioDTO dto) {
		Usuario nuevo = new Usuario(dto.getNombreCompleto(), dto.getTipoDocumento(), dto.getNumeroDocumento(),
				dto.getEmail(), dto.getTelefono(), dto.getContrasena(), dto.getEstadoCuenta(), dto.getFechaRegistro(),
				dto.getRol());
		Usuario actualizado = service.actualizar(id, nuevo);
		return ResponseEntity.ok(actualizado);
	}

	@Operation(summary = "Login de usuario", description = "Permite iniciar sesión con email y contraseña")
	@ApiResponse(responseCode = "200", description = "Inicio de sesión exitoso")
	@ApiResponse(responseCode = "401", description = "Credenciales incorrectas", content = @Content)
	@PostMapping(path = "login")
	public ResponseEntity<?> login(@RequestBody Usuario usuario) {
		boolean login = service.login(usuario.getEmail(), usuario.getContrasena());
		if (login) {
			return ResponseEntity.ok().body("Inicio de sesión exitoso");
		} else {
			return ResponseEntity.status(401).body("Credenciales incorrectas");
		}
	}
}
