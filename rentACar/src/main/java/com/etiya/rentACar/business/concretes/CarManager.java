package com.etiya.rentACar.business.concretes;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.etiya.rentACar.business.abstracts.CarImageService;
import com.etiya.rentACar.business.abstracts.CarService;
import com.etiya.rentACar.business.abstracts.MaintenanceService;
import com.etiya.rentACar.business.dtos.CarDetailDto;
import com.etiya.rentACar.business.dtos.CarSearchListDto;
import com.etiya.rentACar.business.request.CreateCarRequest;
import com.etiya.rentACar.business.request.DeleteCarRequest;
import com.etiya.rentACar.business.request.UpdateCarRequest;
import com.etiya.rentACar.core.utilities.mapping.ModelMapperService;
import com.etiya.rentACar.core.utilities.results.DataResult;
import com.etiya.rentACar.core.utilities.results.Result;
import com.etiya.rentACar.core.utilities.results.SuccessDataResult;
import com.etiya.rentACar.core.utilities.results.SuccessResult;
import com.etiya.rentACar.business.abstracts.CarDao;
import com.etiya.rentACar.entities.Car;
import com.etiya.rentACar.entities.CarImage;
import com.etiya.rentACar.entities.complexTypes.CarDetail;

@Service
public class CarManager implements CarService{
	
	private CarDao carDao;
	private ModelMapperService modelMapperService;
	private CarImageService carImageService;
	private MaintenanceService maintenanceService;
	
	@Autowired
	public CarManager(CarDao carDao, ModelMapperService modelMapperService, CarImageService carImageService, @Lazy MaintenanceService maintenanceService) {
		super();
		this.carDao = carDao;
		this.modelMapperService = modelMapperService;
		this.carImageService = carImageService;
		this.maintenanceService = maintenanceService;
	}

	@Override
	public List<CarSearchListDto> getCars() {
		
		List<Car> list = this.carDao.findAll();
		for (Car car2 : list) {
			int carId = car2.getCarId();
			if(!maintenanceService.checkIfCarIsOnMaintenance(carId).isSuccess()) {
				list.remove(car2);
			}
		}
		List<CarSearchListDto> response = list.stream().map(car -> modelMapperService.forDto()
				.map(car, CarSearchListDto.class)).collect(Collectors.toList());
		
		return response;
	}  

	@Override
	public Result save(CreateCarRequest createCarRequest) {
		Car car = modelMapperService.forRequest().map(createCarRequest, Car.class);
		this.carDao.save(car);
		return new SuccessResult("Car added.");
	}

	@Override
	public Result delete(DeleteCarRequest deleteCarRequest) {
		Car car = modelMapperService.forRequest().map(deleteCarRequest, Car.class);
		this.carDao.delete(car);
		return new SuccessResult("Car deleted.");
	}

	@Override
	public Result update(UpdateCarRequest updateCarRequest) {
		Car car = modelMapperService.forRequest().map(updateCarRequest, Car.class);
		this.carDao.save(car);
		return new SuccessResult("Car updated.");
	}

	@Override
	public DataResult<List<CarDetail>> getCarWithColorAndBrandDetails() {
		List<CarDetail> result = this.carDao.getCarWithColorAndBrandDetails();
		for (CarDetail car2 : result) {
			int carId = car2.getCarId();
			if(!maintenanceService.checkIfCarIsOnMaintenance(carId).isSuccess()) {
				result.remove(car2);
			}
		}
		return new SuccessDataResult<List<CarDetail>>(result);
	}

	@Override
	public DataResult<CarSearchListDto> getCarDetailsById(int id) {
		Car car = this.carDao.findById(id).get();
		if(car != null) {
			CarSearchListDto carSearchListDto = modelMapperService.forDto().map(car, CarSearchListDto.class);
			return new SuccessDataResult<CarSearchListDto>(carSearchListDto);
		}
		return null;
	}

	@Override
	public DataResult<List<CarSearchListDto>> getByBrandId(int brandId) {
		List<Car> cars = this.carDao.getByBrand_BrandId(brandId); 
		int index = 0;
		int i = 0;
		for (Car car2 : cars) {
			int carId = car2.getCarId();
			if(!maintenanceService.checkIfCarIsOnMaintenance(carId).isSuccess()) {
				index = i;
			}
			i++;
		}
		cars.remove(index);
		List<CarSearchListDto> response = cars.stream().map(car -> modelMapperService.forDto()
				.map(car, CarSearchListDto.class)).collect(Collectors.toList());
		return new SuccessDataResult<List<CarSearchListDto>>(response);
	}

	@Override
	public DataResult<List<CarSearchListDto>> getByColorId(int colorId) {
		List<Car> cars = this.carDao.getByColor_ColorId(colorId);
		for (Car car2 : cars) {
			int carId = car2.getCarId();
			if(!maintenanceService.checkIfCarIsOnMaintenance(carId).isSuccess()) {
				cars.remove(car2);
			}
		}
		List<CarSearchListDto> response = cars.stream().map(car -> modelMapperService.forDto()
				.map(car, CarSearchListDto.class)).collect(Collectors.toList());
		return new SuccessDataResult<List<CarSearchListDto>>(response);
	}

	@Override
	public DataResult<CarDetailDto> getCarDetailsByCarId(int carId) {
		Car car = this.carDao.getById(carId);
		CarDetailDto carDetailDto = modelMapperService.forDto().map(car, CarDetailDto.class);
		List<String> list = new ArrayList<String>();
		List<String> defaultPic = new ArrayList<String>();
		List<CarImage> carImages = carImageService.getCarImageListByCarId(carId);
		for (CarImage image : carImages) {
			list.add(image.getImagePath());
		}
		if(list.size() == 0) {
			defaultPic.add("C:\\Users\\halit.mancar\\Desktop\\orange-car-hp-right-mercedez");
			carDetailDto.setImagePaths(defaultPic);
			return new SuccessDataResult<CarDetailDto>(carDetailDto);
		} else {
			carDetailDto.setImagePaths(list);
			return new SuccessDataResult<CarDetailDto>(carDetailDto);
		}
	}

	@Override
	public Car getCarAsElementByCarId(int carId) {
		Car car = carDao.getById(carId);
		return car;
	}

	@Override
	public Result checkExistingCar(int carId) {
		return null;
	}
}