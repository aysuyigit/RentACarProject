package com.etiya.rentACar.business.abstracts;

import com.etiya.rentACar.business.request.LoginRequest;
import com.etiya.rentACar.business.request.RegisterIndividualRequest;
import com.etiya.rentACar.core.utilities.results.Result;

public interface LoginService {
	
	Result login(LoginRequest loginRequest);
	Result individualCustomerRegister(RegisterIndividualRequest registerIndividualRequuest);
}
