package co.edu.unbosque.usuario.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import co.edu.unbosque.usuario.dto.PaymentRequestDTO;
import co.edu.unbosque.usuario.service.StripeService;

@RestController
@RequestMapping("/api/pagos")
@CrossOrigin(origins = "*")
public class StripeController {

	@Autowired
	private StripeService stripeService;

	@PostMapping("/pago")
	public ResponseEntity<String> crearPago(@RequestParam String id, @RequestBody PaymentRequestDTO request) {
		try {
			String url = stripeService.crearSesionPago(id, request.getAmount());
			return ResponseEntity.ok(url);
		} catch (Exception e) {
			return ResponseEntity.internalServerError().body("Error al crear la sesión de pago: " + e.getMessage());
		}
	}

}