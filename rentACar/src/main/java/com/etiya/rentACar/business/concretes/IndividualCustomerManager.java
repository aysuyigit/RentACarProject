package com.etiya.rentACar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import com.etiya.rentACar.business.abstracts.UserService;
import com.etiya.rentACar.business.constants.messages.IndividualCustomerMessages;
import com.etiya.rentACar.core.utilities.business.BusinessRules;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etiya.rentACar.business.abstracts.IndividualCustomerService;
import com.etiya.rentACar.business.dtos.IndividualCustomerSearchListDto;
import com.etiya.rentACar.business.request.CreateIndividualCustomerRequest;
import com.etiya.rentACar.business.request.DeleteIndividualCustomerRequest;
import com.etiya.rentACar.business.request.UpdateIndividualCustomerRequest;
import com.etiya.rentACar.core.utilities.mapping.ModelMapperService;
import com.etiya.rentACar.core.utilities.results.DataResult;
import com.etiya.rentACar.core.utilities.results.Result;
import com.etiya.rentACar.core.utilities.results.SuccessDataResult;
import com.etiya.rentACar.core.utilities.results.SuccessResult;
import com.etiya.rentACar.dataAccess.IndividualCustomerDao;
import com.etiya.rentACar.entities.IndividualCustomer;

@Service
public class IndividualCustomerManager implements IndividualCustomerService{
	
	private IndividualCustomerDao individualCustomerDao;
	private ModelMapperService modelMapperService;
	private UserService userService;
	@Autowired
	public IndividualCustomerManager(IndividualCustomerDao individualCustomerDao, ModelMapperService modelMapperService,UserService userService) {
		super();
		this.individualCustomerDao = individualCustomerDao;
		this.modelMapperService = modelMapperService;
		this.userService = userService;
	}

	@Override
	public DataResult<List<IndividualCustomerSearchListDto>> getAll() {
		List<IndividualCustomer> result = this.individualCustomerDao.findAll();
		List<IndividualCustomerSearchListDto> response = result.stream().map(individualCustomer -> modelMapperService.forDto()
				.map(individualCustomer, IndividualCustomerSearchListDto.class)).collect(Collectors.toList());
		return new SuccessDataResult<List<IndividualCustomerSearchListDto>>(response);
	}

	@Override
	public Result save(CreateIndividualCustomerRequest createIndividualCustomerRequest) {
        Result result = BusinessRules.run(userService.existsByEmail(createIndividualCustomerRequest.getEmail()));
		if(result != null){
			return result;
		}
		IndividualCustomer individualCustomer = modelMapperService.forRequest().map(createIndividualCustomerRequest, IndividualCustomer.class);
		this.individualCustomerDao.save(individualCustomer);
		return new SuccessResult(IndividualCustomerMessages.add);
	}

	@Override
	public Result delete(DeleteIndividualCustomerRequest deleteIndividualCustomerRequest) {
		IndividualCustomer individualCustomer = modelMapperService.forRequest().map(deleteIndividualCustomerRequest, IndividualCustomer.class);
		this.individualCustomerDao.delete(individualCustomer);
		return new SuccessResult(IndividualCustomerMessages.delete);
	}

	@Override
	public Result update(UpdateIndividualCustomerRequest updateIndividualCustomerRequest) {
		IndividualCustomer individualCustomer = modelMapperService.forRequest().map(updateIndividualCustomerRequest, IndividualCustomer.class);
		this.individualCustomerDao.save(individualCustomer);
		return new SuccessResult(IndividualCustomerMessages.update);
	}
	
//	private Result checkUserIdDuplication(int userId) {
//		IndividualCustomer individualCustomer = this.individualCustomerDao.getByUser_UserId(userId);
//		if(individualCustomer != null) {
//			return new ErrorResult("Kullanıcı numarası tekrarlayamaz.");
//		}
//		return new SuccessResult();
//	}
//	
//	private Result checkIfUserExist(int userId) {
//		for (User users : userDao.findAll()) {
//			if(users.getUserId() == userId) {
//				return new SuccessResult();
//			}
//		}
//		return new ErrorResult("Kullanıcı bulunamadı.");
//	}

	@Override
	public IndividualCustomer getCustomerByCustomerId(int customerId) {
		IndividualCustomer individualCustomer = individualCustomerDao.getById(customerId);
		return individualCustomer;
	}
}