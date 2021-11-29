package com.etiya.rentACar.business.abstracts;

import java.util.List;

import com.etiya.rentACar.business.dtos.MaintenanceSearchListDto;
import com.etiya.rentACar.business.request.CreateMaintenanceRequest;
import com.etiya.rentACar.business.request.DeleteMaintenanceRequest;
import com.etiya.rentACar.business.request.UpdateMaintenanceRequest;
import com.etiya.rentACar.core.utilities.results.DataResult;
import com.etiya.rentACar.core.utilities.results.Result;

public interface MaintenanceService {

	DataResult<List<MaintenanceSearchListDto>> getAll();
	Result save(CreateMaintenanceRequest createMaintenanceRequest);
	Result delete(DeleteMaintenanceRequest deleteMaintenanceRequest);
	Result update(UpdateMaintenanceRequest updateMaintenanceRequest);
	Result checkIfCarIsOnMaintenance(int carId);
}