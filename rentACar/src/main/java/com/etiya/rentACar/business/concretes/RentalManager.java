package com.etiya.rentACar.business.concretes;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import com.etiya.rentACar.business.abstracts.*;

import com.etiya.rentACar.business.request.CreateRentalRequest;
import com.etiya.rentACar.business.request.DeleteRentalRequest;
import com.etiya.rentACar.business.request.UpdateRentalRequest;
import com.etiya.rentACar.core.adapters.findeksServiceAdapter.FinancialDataService;
import com.etiya.rentACar.core.adapters.posServiceAdapter.PaymentApprovementService;
import com.etiya.rentACar.core.utilities.results.*;
import com.etiya.rentACar.dataAccess.RentalDao;
import com.etiya.rentACar.entities.AdditionalService;
import com.etiya.rentACar.entities.CreditCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.etiya.rentACar.business.dtos.RentalSearchListDto;

import com.etiya.rentACar.core.utilities.business.BusinessRules;
import com.etiya.rentACar.core.utilities.mapping.ModelMapperService;

import com.etiya.rentACar.entities.Car;
import com.etiya.rentACar.entities.Rental;

@Service
public class RentalManager implements RentalService {

	private RentalDao rentalDao;
	private ModelMapperService modelMapperService;
	private CarService carService;
	private MaintenanceService maintenanceService;
	private FinancialDataService financialDataService;
	private RentingBillService rentingBillService;
	private PaymentApprovementService paymentApprovementService;
	private CreditCardService creditCardService;
	private AdditionalServiceService additionalServiceService;

	@Autowired
	public RentalManager(RentalDao rentalDao, ModelMapperService modelMapperService,
						 CarService carService, FinancialDataService financialDataService,
						 @Lazy MaintenanceService maintenanceService,RentingBillService rentingBillService,
						 PaymentApprovementService paymentApprovementService,
						 CreditCardService creditCardService,
						 AdditionalServiceService additionalServiceService) {
		super();
		this.rentalDao = rentalDao;
		this.modelMapperService = modelMapperService;
		this.carService = carService;
		this.maintenanceService = maintenanceService;
		this.financialDataService = financialDataService;
		this.rentingBillService = rentingBillService;
		this.paymentApprovementService = paymentApprovementService;
		this.creditCardService = creditCardService;
		this.additionalServiceService = additionalServiceService;
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
		Result result = BusinessRules.run(checkCarIsReturned(createRentalRequest.getCarId()),
				checkFindeksPointAcceptability(createRentalRequest.getCarId(),createRentalRequest.getUserId()),
				maintenanceService.checkIfCarIsOnMaintenance(createRentalRequest.getCarId()),
				checkIfPaymentSuccesful(creditCardService.getById(4)),
				checkIfAdditionalServicesAreDeclaredInTrueFormat(createRentalRequest.getDemandedAdditionalServices()));

		if(result != null) {
			return result;
		}
		updateCityNameIfReturnCityIsDifferent(createRentalRequest);
		Rental rental = modelMapperService.forRequest().map(createRentalRequest, Rental.class);
		this.rentalDao.save(rental);

		/*if(createRentalRequest.getReturnDate() != null) {
			this.rentingBillService.save(createRentalRequest);
			return new SuccessResult("Rental log is added and renting bill is created.");
		}*/

		return new SuccessResult("Rental log is added.");
	}

	@Override
	public Result delete(DeleteRentalRequest deleteRentalRequest) {
		Rental rental = modelMapperService.forRequest().map(deleteRentalRequest, Rental.class);
		this.rentalDao.delete(rental);
		return new SuccessResult("Rental log is deleted.");
	}

	@Override
	public Result update(UpdateRentalRequest updateRentalRequest) {
		Result result = BusinessRules.run(checkIfEndDateIsAfterStartDate(updateRentalRequest.getReturnDate(), updateRentalRequest.getRentDate()),
				checkIfAdditionalServicesAreDeclaredInTrueFormat(updateRentalRequest.getDemandedAdditionalServices()));

		if(result != null) {
			return result;
		}
		updateCityNameIfReturnCityIsDifferent(updateRentalRequest);
		this.carService.updateCarKilometer(updateRentalRequest.getCarId(),updateRentalRequest.getReturnKilometer());
		Rental rental = modelMapperService.forRequest().map(updateRentalRequest, Rental.class);
		if (updateRentalRequest.getReturnDate() != null){
			this.rentingBillService.save(updateRentalRequest);
			this.rentalDao.save(rental);
			return new SuccessResult("Rental log is updated and renting bill is created.");
		}
		this.rentalDao.save(rental);
		return new SuccessResult("Rental log is updated.");
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


	private Result checkFindeksPointAcceptability(int carId, int userId) {
		Car car = carService.getCarAsElementByCarId(carId);
		int findeksCar = car.getFindeksPointCar();
		int findeksUser = financialDataService.getFindeksScore(userId);
		if(findeksCar>findeksUser) {
			return new ErrorResult("Müşterinin findeks puanı yetersiz.");
		}
		return new SuccessResult();
	}

	private Result checkIfEndDateIsAfterStartDate(Date endDate, Date startDate) {
		if(endDate != null) {
			if(endDate.before(startDate)) {
				return new ErrorResult("End date cannot be earlier than the start date!");
			}
		}
		return new SuccessResult();
	}

	private void updateCityNameIfReturnCityIsDifferent(CreateRentalRequest createRentalRequest){
		if(!((createRentalRequest.getRentCity()).equals(createRentalRequest.getReturnCity()))){
			this.carService.updateCarCity(createRentalRequest.getCarId(),createRentalRequest.getReturnCity());
		}
	}
	private void updateCityNameIfReturnCityIsDifferent(UpdateRentalRequest updateRentalRequest){
		if(!((updateRentalRequest.getRentCity()).equals(updateRentalRequest.getReturnCity()))){
			this.carService.updateCarCity(updateRentalRequest.getCarId(),updateRentalRequest.getReturnCity());
		}
	}
	private Result checkIfPaymentSuccesful(CreditCard creditCard){
		//creditCard.setCardNumber("");
		boolean result = paymentApprovementService.checkPaymentSuccess(creditCard);
		if(result==false){
			return new ErrorResult("Ödeme başarısız. Araç kiralanamaz!");
		}
		return new SuccessResult();
	}

	public DataResult<List<AdditionalService>> extractAdditionalServicesFromString(UpdateRentalRequest updateRentalRequest) throws NoSuchElementException {
		String services = updateRentalRequest.getDemandedAdditionalServices();
		if(services == null){
			return null;
		}
		if(services.equals("")){
			return null;
		}
		String[] servicesArray = services.split(",");
		List<AdditionalService> servicesAsElements = new ArrayList<AdditionalService>();
		for (String service: servicesArray) {
			boolean isExisting = additionalServiceService.isExisting(Integer.parseInt(service));
			if (isExisting){
				servicesAsElements.add(additionalServiceService.getById(Integer.parseInt(service)));
			} else{
				throw new NoSuchElementException("Servis "+ service + " bulunamadı.");
			}
		}
		return new SuccessDataResult<List<AdditionalService>>(servicesAsElements);
	}
	private Result checkIfAdditionalServicesAreDeclaredInTrueFormat(String demandedAdditionalServices){
		String regex = "^[1-9]+(,[1-9]+)*$";
		if (demandedAdditionalServices == null || demandedAdditionalServices == ""){
			return new SuccessResult();
		}
		if (!demandedAdditionalServices.matches(regex)){
			return new ErrorResult("Additional services are declared in a wrong way! Please write it as service id while seperating them with commas.");
		}
		return new SuccessResult();
	}
}