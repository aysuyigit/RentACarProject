package com.etiya.rentACar.business.concretes;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etiya.rentACar.business.abstracts.CarImageService;
import com.etiya.rentACar.business.abstracts.CarService;
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
import com.etiya.rentACar.dataAccess.abstracts.CarDao;
import com.etiya.rentACar.entities.Car;
import com.etiya.rentACar.entities.CarImage;
import com.etiya.rentACar.entities.complexTypes.CarDetail;

@Service
public class CarManager implements CarService{
	
	private CarDao carDao;
	private ModelMapperService modelMapperService;
	private CarImageService carImageService;
	
	@Autowired
	public CarManager(CarDao carDao, ModelMapperService modelMapperService, CarImageService carImageService) {
		super();
		this.carDao = carDao;
		this.modelMapperService = modelMapperService;
		this.carImageService = carImageService;
	}

	@Override
	public List<CarSearchListDto> getCars() {
		
		List<Car> result = this.carDao.findAll();
		List<CarSearchListDto> response = result.stream().map(car -> modelMapperService.forDto()
				.map(car, CarSearchListDto.class)).collect(Collectors.toList());
		
		return response;
	}  

	@Override
	public Result save(CreateCarRequest createCarRequest) {
		Car car = modelMapperService.forRequest().map(createCarRequest, Car.class);
		this.carDao.save(car);
		
		car.setFindexPoint(createCarRequest.getFindexPoint());
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
		car.setFindexPoint(updateCarRequest.getFindexPoint());
		this.carDao.save(car);
		return new SuccessResult("Car updated.");
	}

	@Override
	public DataResult<List<CarDetail>> getCarWithColorAndBrandDetails() {
		List<CarDetail> result = this.carDao.getCarWithColorAndBrandDetails();
		return new SuccessDataResult<List<CarDetail>>(result);
	}

	@Override
	public DataResult<CarSearchListDto> getById(int id) {
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
		List<CarSearchListDto> response = cars.stream().map(car -> modelMapperService.forDto()
				.map(car, CarSearchListDto.class)).collect(Collectors.toList());
		return new SuccessDataResult<List<CarSearchListDto>>(response);
	}

	@Override
	public DataResult<List<CarSearchListDto>> getByColorId(int colorId) {
		List<Car> cars = this.carDao.getByColor_ColorId(colorId);
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
}
