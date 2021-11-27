package com.etiya.rentACar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etiya.rentACar.business.abstracts.BrandService;
import com.etiya.rentACar.business.dtos.BrandSearchListDto;
import com.etiya.rentACar.business.request.CreateBrandRequest;
import com.etiya.rentACar.business.request.DeleteBrandRequest;
import com.etiya.rentACar.business.request.UpdateBrandRequest;
import com.etiya.rentACar.core.utilities.mapping.ModelMapperService;
import com.etiya.rentACar.core.utilities.results.ErrorResult;
import com.etiya.rentACar.core.utilities.results.Result;
import com.etiya.rentACar.core.utilities.results.SuccessResult;
import com.etiya.rentACar.dataAccess.abstracts.BrandDao;
import com.etiya.rentACar.entities.Brand;

@Service
public class BrandManager implements BrandService{
	
	private BrandDao brandDao;
	private ModelMapperService modelMapperService;
	@Autowired
	public BrandManager(BrandDao brandDao, ModelMapperService modelMapperService) {
		super();
		this.brandDao = brandDao;
		this.modelMapperService = modelMapperService;
	}

	@Override
	public List<BrandSearchListDto> getBrands() {
		
		List<Brand> result = this.brandDao.findAll();
		List<BrandSearchListDto> response = result.stream().map(brand -> modelMapperService.forDto()
				.map(brand, BrandSearchListDto.class)).collect(Collectors.toList());
		
		return response;
	}

	@Override
	public Result save(CreateBrandRequest createBrandRequest) {
		Brand result = this.brandDao.getByBrandName(createBrandRequest.getBrandName());
		if(result ==null) {
			Brand brand = modelMapperService.forRequest().map(createBrandRequest, Brand.class);
			this.brandDao.save(brand);	
			return new SuccessResult("Brand added.");
		}
		return new ErrorResult("This brand already exists");
	}


	@Override
	public Result delete(DeleteBrandRequest deleteBrandRequest) {
		Brand brand = modelMapperService.forRequest().map(deleteBrandRequest, Brand.class);
		this.brandDao.delete(brand);	
		return new SuccessResult("Brand deleted.");
	}

	@Override
	public Result update(UpdateBrandRequest updateBrandRequest) {
		Brand brand = modelMapperService.forRequest().map(updateBrandRequest, Brand.class);
		this.brandDao.save(brand);
		return new SuccessResult("Brand updated.");
	}

}
