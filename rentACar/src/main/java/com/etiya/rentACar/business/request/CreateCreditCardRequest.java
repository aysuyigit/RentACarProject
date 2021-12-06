package com.etiya.rentACar.business.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class CreateCreditCardRequest {
	@JsonIgnore
	@NotNull
	private int cardId;

	@NotNull
	private String nameOnCard;

	@NotNull
	private String cardNumber;

	@NotNull
	private Date expiration;

	@NotNull
    @Size(min=3, max=3)
	private String cvc;

	@NotNull
	private int userId;

}
