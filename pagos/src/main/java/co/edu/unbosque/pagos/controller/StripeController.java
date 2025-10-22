package co.edu.unbosque.pagos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import co.edu.unbosque.pagos.service.StripeService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/pagos")
@Tag(name = "Pagos", description = "Operaciones relacionadas con la gestión de pagos a través de Stripe")
public class StripeController {

	@Autowired
	private StripeService stripeService;

	@Operation(summary = "Crear sesión de pago en Stripe", description = "Crea una nueva sesión de pago para un usuario existente en el sistema. "
			+ "Antes de crear la sesión, el servicio valida que el usuario exista consultando el microservicio de usuarios.")
	@ApiResponse(responseCode = "200", description = "Sesión de pago creada correctamente. Devuelve la URL de Stripe para completar el pago.", content = @Content(mediaType = "text/plain", schema = @Schema(example = "https://checkout.stripe.com/pay/cs_test_12345")))
	@ApiResponse(responseCode = "400", description = "El usuario no existe o los datos de entrada son inválidos", content = @Content(mediaType = "text/plain", schema = @Schema(example = "El usuario con ID 99 no existe.")))
	@ApiResponse(responseCode = "500", description = "Error interno al crear la sesión de pago en Stripe", content = @Content(mediaType = "text/plain", schema = @Schema(example = "Error al crear la sesión de pago: clave Stripe inválida")))
	@PostMapping("/pago")
	public ResponseEntity<String> crearPago(@RequestParam String id, @RequestParam Long amount) {
		try {
			if (!usuarioExiste(id)) {
				return ResponseEntity.badRequest().body("El usuario con ID " + id + " no existe.");
			}

			String url = stripeService.crearSesionPago(id, amount);
			return ResponseEntity.ok(url);

		} catch (Exception e) {
			return ResponseEntity.internalServerError().body("Error al crear la sesión de pago: " + e.getMessage());
		}
	}
	private boolean usuarioExiste(String idUsuario) {
		try {
			RestTemplate restTemplate = new RestTemplate();
			String url = "http://localhost:8082/usuario/existe/" + idUsuario;
			ResponseEntity<String> respuesta = restTemplate.getForEntity(url, String.class);
			return respuesta.getStatusCode().is2xxSuccessful();
		} catch (Exception e) {
			return false;
		}
	}
}
