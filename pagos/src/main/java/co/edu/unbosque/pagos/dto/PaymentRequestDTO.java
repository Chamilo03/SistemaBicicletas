package co.edu.unbosque.pagos.dto;

public class PaymentRequestDTO {

	private Long amount;
	private String currency;
	private String description;
	private String successUrl;
	private String cancelUrl;
	private Long quantity = 1L;

	public PaymentRequestDTO() {
		// TODO Auto-generated constructor stub
	}

	public PaymentRequestDTO(Long amount, String currency, String description, String successUrl, String cancelUrl,
			Long quantity) {
		super();
		this.amount = amount;
		this.currency = currency;
		this.description = description;
		this.successUrl = successUrl;
		this.cancelUrl = cancelUrl;
		this.quantity = quantity;
	}

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSuccessUrl() {
		return successUrl;
	}

	public void setSuccessUrl(String successUrl) {
		this.successUrl = successUrl;
	}

	public String getCancelUrl() {
		return cancelUrl;
	}

	public void setCancelUrl(String cancelUrl) {
		this.cancelUrl = cancelUrl;
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

}
