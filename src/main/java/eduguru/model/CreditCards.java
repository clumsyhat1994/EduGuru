package eduguru.model;

import java.sql.Date;

public class CreditCards {
	protected String cardNumber;
	protected Date expirationDate;
	protected String username;

	public CreditCards(String cardNumber, Date expirationDate,
			String username) {
		this.cardNumber = cardNumber;
		this.expirationDate = expirationDate;
		this.username = username;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public Date getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}
