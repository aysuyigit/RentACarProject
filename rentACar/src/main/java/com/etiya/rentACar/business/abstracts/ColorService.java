package com.etiya.rentACar.business.abstracts;

import java.util.List;

import com.etiya.rentACar.business.dtos.ColorSearchListDto;
import com.etiya.rentACar.business.request.CreateColorRequest;
import com.etiya.rentACar.business.request.DeleteColorRequest;
import com.etiya.rentACar.business.request.UpdateColorRequest;
import com.etiya.rentACar.core.utilities.results.Result;

public interface ColorService {
	List<ColorSearchListDto> getColors();
	Result save(CreateColorRequest createColorRequest);
	Result delete(DeleteColorRequest deleteColorRequest);
	Result update(UpdateColorRequest updateColorRequest);
}
