package com.etiya.rentACar.business.dtos;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarDetailDto {
	
	private int carId;
	private String brandName;
	private String colorName;
	private double dailyPrice;
	private String modelYear;
	private List<String> imagePaths;
}
