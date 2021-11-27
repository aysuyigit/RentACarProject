package com.etiya.rentACar.business.abstracts;

import java.util.List;

import com.etiya.rentACar.business.dtos.CreditCardDto;
import com.etiya.rentACar.business.request.CreateCreditCardRequest;
import com.etiya.rentACar.business.request.DeleteCreditCardRequest;
import com.etiya.rentACar.business.request.UpdateCreditCardRequest;
import com.etiya.rentACar.core.utilities.results.DataResult;
import com.etiya.rentACar.core.utilities.results.Result;

public interface CreditCardService {
 
	DataResult<List<CreditCardDto>> getAll();
	Result create(CreateCreditCardRequest createCreditCardRequest);
	Result update(UpdateCreditCardRequest updateCreditCardRequest);
	Result delete(DeleteCreditCardRequest deleteCreditCardRequest);

}
