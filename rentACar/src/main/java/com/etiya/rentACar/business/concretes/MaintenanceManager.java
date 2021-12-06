package com.etiya.rentACar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import com.etiya.rentACar.business.constants.messages.MaintenanceMessages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etiya.rentACar.business.abstracts.MaintenanceService;
import com.etiya.rentACar.business.abstracts.RentalService;
import com.etiya.rentACar.business.dtos.MaintenanceSearchListDto;
import com.etiya.rentACar.business.request.CreateMaintenanceRequest;
import com.etiya.rentACar.business.request.DeleteMaintenanceRequest;
import com.etiya.rentACar.business.request.UpdateMaintenanceRequest;
import com.etiya.rentACar.core.utilities.business.BusinessRules;
import com.etiya.rentACar.core.utilities.mapping.ModelMapperService;
import com.etiya.rentACar.core.utilities.results.DataResult;
import com.etiya.rentACar.core.utilities.results.ErrorResult;
import com.etiya.rentACar.core.utilities.results.Result;
import com.etiya.rentACar.core.utilities.results.SuccessDataResult;
import com.etiya.rentACar.core.utilities.results.SuccessResult;
import com.etiya.rentACar.dataAccess.MaintenanceDao;
import com.etiya.rentACar.entities.Maintenance;

@Service
public class MaintenanceManager implements MaintenanceService{

	private MaintenanceDao maintenanceDao;
	private ModelMapperService modelMapperService;
	private RentalService rentalService;
	
	@Autowired
	public MaintenanceManager(MaintenanceDao maintenanceDao, ModelMapperService modelMapperService, RentalService rentalService) {
		super();
		this.maintenanceDao = maintenanceDao;
		this.modelMapperService = modelMapperService;
		this.rentalService = rentalService;
	}

	@Override
	public DataResult<List<MaintenanceSearchListDto>> getAll() {
		List<Maintenance> result = this.maintenanceDao.findAll();
		List<MaintenanceSearchListDto> response = result.stream().map(maintenance -> modelMapperService.forDto()
				.map(maintenance, MaintenanceSearchListDto.class)).collect(Collectors.toList());
		return new SuccessDataResult<List<MaintenanceSearchListDto>>(response);
	}

	@Override
	public Result save(CreateMaintenanceRequest createMaintenanceRequest) {
		Result result = BusinessRules.run(checkIfCarIsRentedNow(createMaintenanceRequest.getCarId()));
		
		if(result != null) {
			return result;
		}
		
		Maintenance maintenance = modelMapperService.forRequest().map(createMaintenanceRequest, Maintenance.class);
		this.maintenanceDao.save(maintenance);
		return new SuccessResult(MaintenanceMessages.add);
	}

	@Override
	public Result delete(DeleteMaintenanceRequest deleteMaintenanceRequest) {
		Maintenance maintenance = modelMapperService.forRequest().map(deleteMaintenanceRequest, Maintenance.class);
		this.maintenanceDao.delete(maintenance);
		return new SuccessResult(MaintenanceMessages.delete);
	}

	@Override
	public Result update(UpdateMaintenanceRequest updateMaintenanceRequest) {
		Result result = BusinessRules.run(checkIfCarIsRentedNow(updateMaintenanceRequest.getCarId()));
		
		if(result != null) {
			return result;
		}
		Maintenance maintenance = modelMapperService.forRequest().map(updateMaintenanceRequest, Maintenance.class);
		this.maintenanceDao.save(maintenance);
		return new SuccessResult(MaintenanceMessages.update);
	}
	
	private Result checkIfCarIsRentedNow(int carId) {
		Result isCarReturned = rentalService.checkCarIsReturned(carId);
		if(!isCarReturned.isSuccess()) {
			return new ErrorResult(MaintenanceMessages.checkIfCarIsRentedNow);
		}
		return new SuccessResult();
	}
	
	public Result checkIfCarIsOnMaintenance(int carId) {
		List<Maintenance> maintenance = maintenanceDao.getByCar_CarId(carId);
			for (Maintenance maintenance2 : maintenance) {
				if(maintenance2.getEndDate() == null) {
					return new ErrorResult(MaintenanceMessages.checkIfCarIsOnMaintenance);
				}
			}
		
		return new SuccessResult();
	}

}