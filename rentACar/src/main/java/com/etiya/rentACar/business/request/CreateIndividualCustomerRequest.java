package com.etiya.rentACar.business.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateIndividualCustomerRequest {
	
	@NotNull
	@Size(min=2,max=16)
	private String firstName;
	
	@NotNull
	@Size(min=2,max=16)
	private String lastName;
	
	@NotNull
	private String birthday;

	private String email;

	@NotNull
	@JsonIgnore
	private int findeksPointPerson;
}
