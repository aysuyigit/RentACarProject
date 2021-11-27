package com.etiya.rentACar.business.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class CreateCreditCardRequest {
    private String cardName;
	

	private String cardNumber;
	
	
	private String expiration;
	
	private String cvc;
	
	private int individualCustomerId;

}
