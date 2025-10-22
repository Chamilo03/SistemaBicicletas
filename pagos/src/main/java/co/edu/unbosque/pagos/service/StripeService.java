package co.edu.unbosque.pagos.service;

import org.springframework.stereotype.Service;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;

import jakarta.annotation.PostConstruct;

@Service
public class StripeService {

	private final String secretKey = "sk_test_51SGohv76L7NXhUXdrZAQsMPHIw9a66KnNTZOWWnlIynHc757Tx6uS6c0U0St56w79eX4NfNcHF1ioFfiCn789h7V00UN84NcdK";

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
