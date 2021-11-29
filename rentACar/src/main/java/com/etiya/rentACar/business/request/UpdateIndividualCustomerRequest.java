package com.etiya.rentACar.business.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateIndividualCustomerRequest {
	
	@NotNull
	private int individualId;
	
	@NotNull
	@Size(min=2,max=16)
	private int firstName;
	
	@NotNull
	@Size(min=2,max=16)
	private int lastName;
	
	@NotNull
	private String birthday;
	
	@NotNull
	private String password;
	
	@NotNull
	private String email;
}
