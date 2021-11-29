package com.etiya.rentACar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etiya.rentACar.business.abstracts.RentalService;
import com.etiya.rentACar.business.dtos.RentalSearchListDto;
import com.etiya.rentACar.business.request.CreateRentalRequest;
import com.etiya.rentACar.business.request.DeleteRentalRequest;
import com.etiya.rentACar.business.request.UpdateRentalRequest;
import com.etiya.rentACar.core.utilities.business.BusinessRules;
import com.etiya.rentACar.core.utilities.mapping.ModelMapperService;
import com.etiya.rentACar.core.utilities.results.DataResult;
import com.etiya.rentACar.core.utilities.results.ErrorResult;
import com.etiya.rentACar.core.utilities.results.Result;
import com.etiya.rentACar.core.utilities.results.SuccessDataResult;
import com.etiya.rentACar.core.utilities.results.SuccessResult;
import com.etiya.rentACar.dataAccess.abstracts.RentalDao;
import com.etiya.rentACar.entities.Rental;

@Service
public class RentalManager implements RentalService {

	private RentalDao rentalDao;
	private ModelMapperService modelMapperService;
	
	@Autowired
	public RentalManager(RentalDao rentalDao, ModelMapperService modelMapperService) {
		super();
		this.rentalDao = rentalDao;
		this.modelMapperService = modelMapperService;
	}

	@Override
	public DataResult<List<RentalSearchListDto>> getAll() {
		List<Rental> result = this.rentalDao.findAll();
		List<RentalSearchListDto> response = result.stream().map(rental -> modelMapperService.forDto()
				.map(rental, RentalSearchListDto.class)).collect(Collectors.toList());
		return new SuccessDataResult<List<RentalSearchListDto>>(response);
	}

	@Override
	public Result save(CreateRentalRequest createRentalRequest) {
		Result result = BusinessRules.run(checkCarIsReturned(createRentalRequest.getCarId()));
		
		if(result != null) {
			return result;
		}
		 
		Rental rental = modelMapperService.forRequest().map(createRentalRequest, Rental.class);
		this.rentalDao.save(rental);
		return new SuccessResult("Rental added.");
	}

	@Override
	public Result delete(DeleteRentalRequest deleteRentalRequest) {
		Rental rental = modelMapperService.forRequest().map(deleteRentalRequest, Rental.class);
		this.rentalDao.delete(rental);
		return new SuccessResult("Rental deleted.");
	}

	@Override
	public Result update(UpdateRentalRequest updateRentalRequest) {
		Rental rental = modelMapperService.forRequest().map(updateRentalRequest, Rental.class);
		this.rentalDao.save(rental);
		return new SuccessResult("Rental updated.");
	}
	
	public Result checkCarIsReturned(int carId) {
		List<Rental> result = this.rentalDao.getByCar_CarId(carId);
		if(result != null) {
			for (Rental rentals : this.rentalDao.getByCar_CarId(carId)) {
				if(rentals.getReturnDate() == null) {
					return new ErrorResult("Araç teslim edilmediği için kiralanamaz");
				}
			}
		}
		return new SuccessResult();
	}
}



