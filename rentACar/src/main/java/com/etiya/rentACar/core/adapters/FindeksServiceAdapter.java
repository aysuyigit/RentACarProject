package com.etiya.rentACar.core.adapters;

import org.springframework.stereotype.Service;

import com.etiya.rentACar.business.abstracts.CustomerFindeksPointCheckService;
import com.etiya.rentACar.core.services.FindeksService;
import com.etiya.rentACar.entities.IndividualCustomer;

@Service
public class FindeksServiceAdapter implements CustomerFindeksPointCheckService{

	@Override
	public int checkIndividualCustomerFindexPoint(IndividualCustomer individualCustomer) {
		FindeksService findeksService= new FindeksService();
		return findeksService.individualCustomerFindexPoint(individualCustomer.getIndividualId());
	}

}
