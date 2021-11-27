package com.etiya.rentACar.business.abstracts;

import java.util.List;

import com.etiya.rentACar.business.dtos.BrandSearchListDto;
import com.etiya.rentACar.business.request.CreateBrandRequest;
import com.etiya.rentACar.business.request.DeleteBrandRequest;
import com.etiya.rentACar.business.request.UpdateBrandRequest;
import com.etiya.rentACar.core.utilities.results.Result;

public interface BrandService {
	List<BrandSearchListDto> getBrands();
	Result save(CreateBrandRequest createBrandRequest);
	Result delete(DeleteBrandRequest deleteBrandRequest);
	Result update(UpdateBrandRequest updateBrandRequest);
}
