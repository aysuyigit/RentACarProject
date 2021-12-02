package com.etiya.rentACar.business.abstracts;

import com.etiya.rentACar.business.request.DeleteAdditionalServiceRequest;
import com.etiya.rentACar.business.request.UpdateAdditionalServiceRequest;
import com.etiya.rentACar.business.dtos.AdditionalServiceSearchListDto;
import com.etiya.rentACar.business.request.CreateAdditionalServiceRequest;
import com.etiya.rentACar.core.utilities.results.DataResult;
import com.etiya.rentACar.core.utilities.results.Result;
import com.etiya.rentACar.entities.AdditionalService;

import java.util.List;

public interface AdditionalServiceService {

    DataResult<List<AdditionalServiceSearchListDto>> getAll();
    Result save(CreateAdditionalServiceRequest createAdditionalServiceRequest);
    Result delete(DeleteAdditionalServiceRequest deleteAdditionalServiceRequest);
    Result update(UpdateAdditionalServiceRequest updateAdditionalServiceRequest);
    AdditionalService getById(int serviceId);
}
