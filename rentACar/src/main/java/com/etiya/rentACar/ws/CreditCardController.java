package com.etiya.rentACar.ws;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.etiya.rentACar.business.abstracts.CreditCardService;
import com.etiya.rentACar.business.dtos.CreditCardDto;
import com.etiya.rentACar.business.request.CreateCreditCardRequest;
import com.etiya.rentACar.business.request.DeleteCreditCardRequest;
import com.etiya.rentACar.business.request.UpdateCreditCardRequest;
import com.etiya.rentACar.core.utilities.results.DataResult;
import com.etiya.rentACar.core.utilities.results.Result;

public class CreditCardController {
   private CreditCardService creditCardService;
	
	@Autowired
	public CreditCardController(CreditCardService creditCardService) {
		super();
		this.creditCardService = creditCardService;
	}
	
	@GetMapping("/getall")
	public DataResult<List<CreditCardDto>> getAll() {
		return this.creditCardService.getAll();
	}
	
	@PostMapping("/addcard")
	public Result create(@Valid @RequestBody  CreateCreditCardRequest createCreditCardRequest) {
		return this.creditCardService.create(createCreditCardRequest);
	}
	
	@PostMapping("/updatecard")
	public Result update( @Valid @RequestBody UpdateCreditCardRequest updateCreditCardRequest) {
		return this.creditCardService.update(updateCreditCardRequest);
	}
	
	@DeleteMapping("/deletecard")
	public Result delete(DeleteCreditCardRequest deleteCreditCardRequest) {
		return this.creditCardService.delete(deleteCreditCardRequest);
	}
	

}
