package com.etiya.rentACar.business.abstracts;

import java.util.List;

import com.etiya.rentACar.business.dtos.RentalSearchListDto;
import com.etiya.rentACar.business.request.CreateRentalRequest;
import com.etiya.rentACar.business.request.DeleteRentalRequest;
import com.etiya.rentACar.business.request.UpdateRentalRequest;
import com.etiya.rentACar.core.utilities.results.DataResult;
import com.etiya.rentACar.core.utilities.results.Result;
import com.etiya.rentACar.entities.AdditionalService;

public interface RentalService {
	DataResult<List<RentalSearchListDto>> getAll();
	Result save(CreateRentalRequest createRentalRequest);
	Result delete(DeleteRentalRequest deleteRentalRequest);
	Result update(UpdateRentalRequest updateRentalRequest);
	Result checkCarIsReturned(int carId);
	public List<AdditionalService> extractAdditionalServicesFromString(CreateRentalRequest createRentalRequest);
	public List<AdditionalService> extractAdditionalServicesFromString(UpdateRentalRequest updateRentalRequest);
}