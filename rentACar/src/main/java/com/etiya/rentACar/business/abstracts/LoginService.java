package com.etiya.rentACar.business.abstracts;

import com.etiya.rentACar.business.request.LoginRequest;
import com.etiya.rentACar.business.request.RegisterCorporateCustomerRequest;
import com.etiya.rentACar.business.request.RegisterIndividualCustomerRequest;
import com.etiya.rentACar.core.utilities.results.Result;

public interface LoginService {

	Result login(LoginRequest loginRequest);
	Result individualCustomerRegister(RegisterIndividualCustomerRequest registerIndividualCustomerRequest);
	Result corporateCustomerRegister(RegisterCorporateCustomerRequest registerCorporateCustomerRequest);
}
