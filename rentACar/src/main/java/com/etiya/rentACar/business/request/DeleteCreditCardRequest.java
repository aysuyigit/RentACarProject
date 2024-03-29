package com.etiya.rentACar.business.request;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DeleteCreditCardRequest {
	
	
	@NotNull(message = "Boş Geçilemez!")
	private int cardId;
}
