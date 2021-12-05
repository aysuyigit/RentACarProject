package com.etiya.rentACar.business.abstracts;

import java.util.List;

import com.etiya.rentACar.business.dtos.CarDetailDto;
import com.etiya.rentACar.business.dtos.CarSearchListDto;
import com.etiya.rentACar.business.request.CreateCarRequest;
import com.etiya.rentACar.business.request.DeleteCarRequest;
import com.etiya.rentACar.business.request.UpdateCarRequest;
import com.etiya.rentACar.core.utilities.results.DataResult;
import com.etiya.rentACar.core.utilities.results.Result;
import com.etiya.rentACar.entities.Car;
import com.etiya.rentACar.entities.complexTypes.CarDetail;

public interface CarService {
	List<CarSearchListDto> getCars();
	Result save(CreateCarRequest createCarRequest);
	Result delete(DeleteCarRequest deleteCarRequest);
	Result update(UpdateCarRequest updateCarRequest);
	DataResult<List<CarDetail>> getCarWithColorAndBrandDetails();
	DataResult<CarSearchListDto> getCarDetailsById(int id);
	DataResult<List<CarSearchListDto>> getByBrandId(int brandId);
	DataResult<List<CarSearchListDto>> getByColorId(int colorId);
	DataResult<List<CarSearchListDto>> getByCityName(String cityName);
	DataResult<CarDetailDto> getCarDetailsByCarId(int carId);
	Car getCarAsElementByCarId(int carId);
	void updateCarCity(int carId, String cityName);
	void updateCarKilometer(int carId, int kilometer);
	Result checkExistingCar(int carId);

	DataResult<List<CarDetail>> getCarsThatAreNotOnMaintenance();
}