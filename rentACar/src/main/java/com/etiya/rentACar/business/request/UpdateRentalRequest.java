package com.etiya.rentACar.business.request;

import java.sql.Date;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateRentalRequest {
	@NotNull
	private int rentalId;

	@NotNull
	private int carId;

	@NotNull
	private int userId;

	@NotNull
	private Date rentDate;


	private Date returnDate;

	@NotNull
	private String rentCity;

	@NotNull
	private String returnCity;

	@NotNull
	private int returnKilometer;

	private String additionalServices;
	private String demandedAdditionalServices;

}