package com.etiya.rentACar.business.request;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateRentalRequest {
	
	@JsonIgnore
	private int rentalId;
	
	@NotNull
	private String rentDate;
 
	private String returnDate;
	
	@NotNull
	private int carId;
	
	@NotNull
	private int individualId;
	
}
