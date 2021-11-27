package com.etiya.rentACar.business.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class RentalSearchListDto {
	private int rentalId; 
	private int individualId;
	private int carId;
	private String rentDate;
	private String returnDate;
	
}
