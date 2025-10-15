package co.edu.unbosque.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;

import co.edu.unbosque.dto.NotificacionDTO;
import jakarta.annotation.PostConstruct;

@Service
public class StripeService {
	@Autowired
    private RestTemplate restTemplate;
	@Value("${servicio.notificacion.url}")
    private String notificacionServiceUrl;


	private final String secretKey = System.getenv("STRIPE_SECRET_KEY");

	@PostConstruct
	public void init() {
		Stripe.apiKey = secretKey;
	}

	public String crearSesionPago(String clienteId, Long monto) throws StripeException {
		SessionCreateParams params = SessionCreateParams.builder().setMode(SessionCreateParams.Mode.PAYMENT)
				.addLineItem(SessionCreateParams.LineItem.builder().setQuantity(1L)
						.setPriceData(SessionCreateParams.LineItem.PriceData.builder().setCurrency("cop")
								.setUnitAmount(monto * 100)
								.setProductData(SessionCreateParams.LineItem.PriceData.ProductData.builder()
										.setName("Recarga cliente " + clienteId).build())
								.build())
						.build())
				.setSuccessUrl("http://localhost:8082/usuario/recargarSaldo/" + clienteId + "?monto=" + monto)
				.setCancelUrl("https://http.cat/status/400").build();

		Session session = Session.create(params);
		return session.getUrl();
	}

    public void enviarCorreoDeConfirmacion(String correo, Long monto) {
        NotificacionDTO dto = new NotificacionDTO();
        dto.setCorreoDestino(correo);
        dto.setAsunto("Confirmación de pago");
        dto.setMensaje("Tu pago por $" + monto + " COP fue exitoso. ¡Gracias por tu compra!");

        restTemplate.postForEntity(notificacionServiceUrl + "/notificacion/crear", dto, String.class);
    }
}
