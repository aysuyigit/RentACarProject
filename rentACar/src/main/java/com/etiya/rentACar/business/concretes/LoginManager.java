package com.etiya.rentACar.business.concretes;

import org.springframework.stereotype.Service;

import com.etiya.rentACar.business.abstracts.IndividualCustomerService;
import com.etiya.rentACar.business.abstracts.LoginService;
import com.etiya.rentACar.business.abstracts.UserService;
import com.etiya.rentACar.business.request.CreateIndividualCustomerRequest;
import com.etiya.rentACar.business.request.LoginRequest;
import com.etiya.rentACar.business.request.RegisterIndividualRequest;
import com.etiya.rentACar.core.utilities.business.BusinessRules;
import com.etiya.rentACar.core.utilities.mapping.ModelMapperService;
import com.etiya.rentACar.core.utilities.results.ErrorResult;
import com.etiya.rentACar.core.utilities.results.Result;
import com.etiya.rentACar.core.utilities.results.SuccessResult;

@Service
public class LoginManager implements LoginService {
	
	private UserService userService;
	private ModelMapperService modelMapperService;
	private IndividualCustomerService individualCustomerService;
	

	public LoginManager(UserService userService, ModelMapperService modelMapperService, IndividualCustomerService individualCustomerService) {
		super();
		this.userService = userService;
		this.modelMapperService = modelMapperService;
		this.individualCustomerService = individualCustomerService;
	}


	@Override
	public Result login(LoginRequest loginRequest) {
		Result result = BusinessRules.run(this.checkCustomerByEmail(loginRequest),
				this.checkCustomerByPassword(loginRequest));

		if (result != null) {
			return result;
		}

		return new SuccessResult("Successfuly login");
	}
	

	private Result checkCustomerByEmail(LoginRequest loginRequest) {

		if (this.userService.existsByEmail(loginRequest.getEmail()).isSuccess()) {
			return new ErrorResult("Email hatalı");
		}
		return new SuccessResult();
	}
	
	private Result checkCustomerByPassword(LoginRequest loginRequest) {

		if (checkCustomerByEmail(loginRequest).isSuccess()) {

			if (!this.userService.getByEmail(loginRequest.getEmail()).getData().getPassword()
					.equals(loginRequest.getPassword())) {
				return new ErrorResult("Password hatalı");
			}
		}
		return new SuccessResult();
	}


	@Override
	public Result individualCustomerRegister(RegisterIndividualRequest registerIndividualRequuest) {
		
		CreateIndividualCustomerRequest result = modelMapperService.forRequest()
				.map(registerIndividualRequuest, CreateIndividualCustomerRequest.class);
		
		//result.setFindeksScore(rand.nextInt(1300)+600);
		this.individualCustomerService.save(result);
		return new SuccessResult("Customer added");
	}


	
	}






