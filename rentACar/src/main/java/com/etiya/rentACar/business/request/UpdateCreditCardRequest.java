package com.etiya.rentACar.business.request;

import java.sql.Date;

import javax.validation.constraints.*;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCreditCardRequest {
	@NotNull
	private String nameOnCard;

	@NotNull
	@ApiModelProperty(example = "xxxxxxxxxxxxxxxx")
	private String cardNumber;

	@NotNull
	@ApiModelProperty(example = "1970-01-01")
	private Date expiration;

	@NotNull
	@Size(min = 3, max = 3)
	@ApiModelProperty(example = "123")
	private String cvc;

	@NotNull
	private int userId;

	@NotNull
	private int cardId;
}