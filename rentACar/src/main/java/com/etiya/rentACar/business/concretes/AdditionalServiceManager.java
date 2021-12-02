package com.etiya.rentACar.business.concretes;

import com.etiya.rentACar.business.request.DeleteAdditionalServiceRequest;
import com.etiya.rentACar.business.request.UpdateAdditionalServiceRequest;
import com.etiya.rentACar.business.abstracts.AdditionalServiceService;
import com.etiya.rentACar.business.dtos.AdditionalServiceSearchListDto;
import com.etiya.rentACar.business.request.CreateAdditionalServiceRequest;
import com.etiya.rentACar.core.utilities.business.BusinessRules;
import com.etiya.rentACar.core.utilities.mapping.ModelMapperService;
import com.etiya.rentACar.core.utilities.results.*;
import com.etiya.rentACar.business.abstracts.AdditionalServiceDao;
import com.etiya.rentACar.entities.AdditionalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdditionalServiceManager implements AdditionalServiceService {

    private ModelMapperService modelMapperService;
    private AdditionalServiceDao additionalServiceDao;


    @Autowired
    public AdditionalServiceManager(ModelMapperService modelMapperService,AdditionalServiceDao additionalServiceDao) {
        this.modelMapperService = modelMapperService;
        this.additionalServiceDao=additionalServiceDao;
    }

    public DataResult<List<AdditionalServiceSearchListDto>> getAll() {
        List<AdditionalService> additionalServices=additionalServiceDao.findAll();
        List<AdditionalServiceSearchListDto> response=additionalServices.stream()
                .map(additionalService -> modelMapperService.forDto().map(additionalService,AdditionalServiceSearchListDto.class))
                .collect(Collectors.toList());

        return new SuccessDataResult<List<AdditionalServiceSearchListDto>>(response);
    }

    @Override
    public Result save(CreateAdditionalServiceRequest createAdditionalServiceRequest) {
        Result result = BusinessRules.run(checkExistingServiceName(createAdditionalServiceRequest.getServiceName()));
        if(result!=null){
            return result;
        }
        AdditionalService additionalService = modelMapperService.forRequest().
                map(createAdditionalServiceRequest, AdditionalService.class);
        this.additionalServiceDao.save(additionalService);
        return new SuccessResult("Additional service is added.");
    }


    @Override
    public Result delete(DeleteAdditionalServiceRequest deleteAdditionalServiceRequest) {
        AdditionalService additionalService = modelMapperService.forRequest().map(deleteAdditionalServiceRequest,AdditionalService.class);
        this.additionalServiceDao.delete(additionalService);
        return new SuccessResult("");
    }

    @Override
    public Result update(UpdateAdditionalServiceRequest updateAdditionalServiceRequest) {
        Result result = BusinessRules.run(checkExistingServiceName(updateAdditionalServiceRequest.getServiceName()));
        if(result!=null){
            return result;
        }
        AdditionalService additionalService = modelMapperService.forRequest().
                map(updateAdditionalServiceRequest, AdditionalService.class);
        this.additionalServiceDao.save(additionalService);
        return new SuccessResult("Additional service is updated.");
    }

    @Override
    public AdditionalService getById(int serviceId) {
        return additionalServiceDao.getByServiceId(serviceId);
    }

    private Result checkExistingServiceName(String serviceName){
        String lowerCaseServiceName = serviceName.toLowerCase();
        for (AdditionalService service: additionalServiceDao.findAll()) {
            String lowerCaseService = service.getServiceName().toLowerCase();
            if(lowerCaseService.equals(lowerCaseServiceName)) {
                return new ErrorResult("Servis ismi tekrar edemez!");
            }
        }
        return new SuccessResult();
    }
}
