package com.etiya.rentACar.business.abstracts;

import java.util.List;

import com.etiya.rentACar.business.dtos.RentalSearchListDto;
import com.etiya.rentACar.business.request.CreateRentalRequest;
import com.etiya.rentACar.business.request.DeleteRentalRequest;
import com.etiya.rentACar.business.request.UpdateRentalRequest;
import com.etiya.rentACar.core.utilities.results.DataResult;
import com.etiya.rentACar.core.utilities.results.Result;

public interface RentalService {
	DataResult<List<RentalSearchListDto>> getAll();
	Result save(CreateRentalRequest createRentalRequest);
	Result delete(DeleteRentalRequest deleteRentalRequest);
	Result update(UpdateRentalRequest updateRentalRequest);
	Result checkCarIsReturned(int carId);
}