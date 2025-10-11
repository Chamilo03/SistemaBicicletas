package co.edu.unbosque.service;

import org.springframework.stereotype.Service;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;

import jakarta.annotation.PostConstruct;

@Service
public class StripeService {

	private static final String secretKey = System.getenv("STRIPE_SECRET_KEY");

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
}
