package com.etiya.rentACar.business.request;

import javax.validation.constraints.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCarRequest {
	
	@NotNull
	@Min(0)
	private int carId;
	
	@NotNull
	@Min(0)
	private int brandId;
	
	@NotNull
	@Min(0)
	private int colorId;
	
	@NotNull
	@Size(min=4, max=4)
	private String modelYear;
	
	@NotNull
	@Min(0)
	private Double dailyPrice;
	
	@NotNull
	@Size(max=50)
	private String description;
	
	@Min(0)
	@Max(1900)
	@NotNull
	private Integer findexPoint;

	@NotNull
	@Min(0)
	private Integer kilometer;

	@NotNull
	@Min(1)
	@Max(81)
	private int cityId;
}
