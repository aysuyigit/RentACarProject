package com.etiya.rentACar.business.concretes;


import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.etiya.rentACar.business.abstracts.RentalService;

import com.etiya.rentACar.business.request.CreateRentalRequest;
import com.etiya.rentACar.business.request.DeleteRentingBillRequest;
import com.etiya.rentACar.business.request.UpdateRentalRequest;
import com.etiya.rentACar.entities.AdditionalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.etiya.rentACar.business.abstracts.CarService;
import com.etiya.rentACar.business.abstracts.RentingBillService;
import com.etiya.rentACar.business.abstracts.UserService;
import com.etiya.rentACar.business.dtos.RentingBillSearchListDto;
import com.etiya.rentACar.core.utilities.mapping.ModelMapperService;
import com.etiya.rentACar.core.utilities.results.DataResult;
import com.etiya.rentACar.core.utilities.results.Result;
import com.etiya.rentACar.core.utilities.results.SuccessDataResult;
import com.etiya.rentACar.core.utilities.results.SuccessResult;
import com.etiya.rentACar.business.abstracts.RentingBillDao;
import com.etiya.rentACar.entities.RentingBill;

@Service
public class RentingBillManager implements RentingBillService {

    private RentingBillDao rentingBillDao;
    private ModelMapperService modelMapperService;
    private UserService userService;
    private CarService carService;
    private RentalService rentalService;
    @Autowired
    public RentingBillManager(RentingBillDao rentingBillDao, ModelMapperService modelMapperService,
                              UserService userService, CarService carService, @Lazy RentalService rentalService) {
        super();
        this.rentingBillDao = rentingBillDao;
        this.modelMapperService = modelMapperService;
        this.userService = userService;
        this.carService = carService;
        this.rentalService = rentalService;
    }

    @Override
    public DataResult<List<RentingBillSearchListDto>> getAll() {
        List<RentingBill> result = rentingBillDao.findAll();
        List<RentingBillSearchListDto> response = result.stream().map(rentingBill -> modelMapperService.forDto().
                map(rentingBill, RentingBillSearchListDto.class)).collect(Collectors.toList());
        return new SuccessDataResult<List<RentingBillSearchListDto>>(response);
    }


    @Override
    public Result save(CreateRentalRequest createRentalRequest) {

        RentingBill rentingBill = new RentingBill();
        Date dateNow = new java.sql.Date(new java.util.Date().getTime());
        rentingBill.setCreationDate(dateNow);
        rentingBill.setRentingStartDate(createRentalRequest.getRentDate());
        rentingBill.setRentingEndDate(createRentalRequest.getReturnDate());
        rentingBill.setUser(userService.getById(createRentalRequest.getUserId()));
        int totalRentDay = calculateDifferenceBetweenDays(createRentalRequest.getReturnDate(), createRentalRequest.getRentDate());
        rentingBill.setTotalRentingDay(totalRentDay);
        int dailyPriceOfCar = (int)(carService.getCarDetailsById(createRentalRequest.getCarId()).getData().getDailyPrice());

        rentingBill.setRentingPrice(calculateRentingPrice(createRentalRequest.getRentCity(),
                createRentalRequest.getReturnCity(),
                dailyPriceOfCar,totalRentDay,createRentalRequest));

        rentingBillDao.save(rentingBill);
        return new SuccessResult("Renting bill added.");
    }

    @Override
    public Result delete(DeleteRentingBillRequest deleteRentingBillRequest) {
        RentingBill rentingBill = modelMapperService.forRequest().map(deleteRentingBillRequest, RentingBill.class);
        this.rentingBillDao.delete(rentingBill);
        return new SuccessResult("Renting bill deleted.");
    }

    @Override
    public Result update(UpdateRentalRequest updateRentalRequest) {
        RentingBill rentingBill = new RentingBill();
        Date dateNow = new java.sql.Date(new java.util.Date().getTime());
        rentingBill.setCreationDate(dateNow);
        rentingBill.setRentingStartDate(updateRentalRequest.getRentDate());
        rentingBill.setRentingEndDate(updateRentalRequest.getReturnDate());
        rentingBill.setUser(userService.getById(updateRentalRequest.getUserId()));
        int totalRentDay = calculateDifferenceBetweenDays(updateRentalRequest.getReturnDate(), updateRentalRequest.getRentDate());
        rentingBill.setTotalRentingDay(totalRentDay);
        int dailyPriceOfCar = (int)(carService.getCarDetailsById(updateRentalRequest.getCarId()).getData().getDailyPrice());
        rentingBill.setRentingPrice(calculateRentingPrice(updateRentalRequest.getRentCity(),
                updateRentalRequest.getReturnCity(),
                dailyPriceOfCar,totalRentDay,updateRentalRequest));
        rentingBillDao.save(rentingBill);
        return new SuccessResult("Renting bill added.");
    }

    private int calculateDifferenceBetweenDays(Date endDate, Date startDate) {
        long difference = (endDate.getTime() - startDate.getTime())/86400000;
        return Math.abs((int)difference);
    }

    @Override
    public DataResult<List<RentingBillSearchListDto>> getRentingBillByUserId(int userId) {
        List<RentingBill> list = rentingBillDao.getByUser_UserId(userId);
        List<RentingBillSearchListDto> response = list.stream().map(rentingBill -> modelMapperService.forDto().
                map(rentingBill, RentingBillSearchListDto.class)).collect(Collectors.toList());
        return new SuccessDataResult<List<RentingBillSearchListDto>>(response);
    }


    @Override
    public DataResult<List<RentingBillSearchListDto>> getRentingBillByDateInterval(Date startDate, Date endDate) {
        List<RentingBill> list = rentingBillDao.findByCreationDateBetween(startDate, endDate);
        List<RentingBillSearchListDto> response = list.stream().map(rentingBill -> modelMapperService.forDto().
                map(rentingBill, RentingBillSearchListDto.class)).collect(Collectors.toList());
        return new SuccessDataResult<List<RentingBillSearchListDto>>(response);
    }

    private int calculateRentingPrice(String rentCity, String returnCity, int dailyPriceOfCar,
                                      int totalRentDay, CreateRentalRequest createRentalRequest){

        List<AdditionalService> list = rentalService.extractAdditionalServicesFromString(createRentalRequest);
        if (list == null){
            if (!rentCity.equals(returnCity)){
                int price = (dailyPriceOfCar*totalRentDay) + 500;
                return price;
            }
            int price = dailyPriceOfCar*totalRentDay;
            return price;
        }
        int totalAdditionalServiceCost=0;
        for (AdditionalService service : list) {
            totalAdditionalServiceCost += service.getServiceDailyPrice();
        }

        if (!rentCity.equals(returnCity)){
            int price = (dailyPriceOfCar*totalRentDay) + 500;
            price += totalAdditionalServiceCost * totalRentDay;
            return price;
        }
        int price = dailyPriceOfCar*totalRentDay;
        price += totalAdditionalServiceCost * totalRentDay;
        return price;

    }
    private int calculateRentingPrice(String rentCity, String returnCity, int dailyPriceOfCar,
                                      int totalRentDay, UpdateRentalRequest updateRentalRequest){

        List<AdditionalService> list = rentalService.extractAdditionalServicesFromString(updateRentalRequest);
        if (list == null){
            if (!rentCity.equals(returnCity)){
                int price = (dailyPriceOfCar*totalRentDay) + 500;
                return price;
            }
            int price = dailyPriceOfCar*totalRentDay;
            return price;
        }
        int totalAdditionalServiceCost=0;
        for (AdditionalService service : list) {
            totalAdditionalServiceCost += service.getServiceDailyPrice();
        }

        if (!rentCity.equals(returnCity)){
            int price = (dailyPriceOfCar*totalRentDay) + 500;
            price += totalAdditionalServiceCost * totalRentDay;
            return price;
        }
        int price = dailyPriceOfCar*totalRentDay;
        price += totalAdditionalServiceCost * totalRentDay;
        return price;

    }

}