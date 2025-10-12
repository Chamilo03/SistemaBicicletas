package co.edu.unbosque.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.edu.unbosque.dto.PaymentRequestDTO;
import co.edu.unbosque.service.StripeService;

@RestController
@RequestMapping("/api/pagos")
@CrossOrigin(origins = "*")
public class StripeController {

	@Autowired
	private StripeService stripeService;

	@PostMapping(path ="/pago")
	public ResponseEntity<String> crearPago(@RequestParam String id, @RequestBody PaymentRequestDTO request) {
		try {
			String url = stripeService.crearSesionPago(id, request.getAmount());
			return ResponseEntity.ok(url);
		} catch (Exception e) {
			return ResponseEntity.internalServerError().body("Error al crear la sesión de pago: " + e.getMessage());
		}
	}
	@PostMapping("/webhook")
    public ResponseEntity<String> handleStripeWebhook(@RequestBody String payload) {

        // Simulando que el pago fue exitoso:
        String correo = "camilocastro8181@gmail.com";
        Long monto = 50000L;

        // Llamar al servicio de envío de correo
        stripeService.enviarCorreoDeConfirmacion(correo, monto);

        return ResponseEntity.ok("Notificación enviada correctamente");
    }

}