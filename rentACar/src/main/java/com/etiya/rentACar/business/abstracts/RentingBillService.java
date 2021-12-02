package com.etiya.rentACar.business.abstracts;

import java.sql.Date;
import java.util.List;

import com.etiya.rentACar.business.dtos.RentingBillSearchListDto;

import com.etiya.rentACar.business.request.CreateRentalRequest;
import com.etiya.rentACar.business.request.DeleteRentingBillRequest;
import com.etiya.rentACar.business.request.UpdateRentalRequest;
import com.etiya.rentACar.core.utilities.results.DataResult;
import com.etiya.rentACar.core.utilities.results.Result;

public interface RentingBillService {
    DataResult<List<RentingBillSearchListDto>> getAll();
    Result save(CreateRentalRequest createRentalRequest);
    Result delete(DeleteRentingBillRequest deleteRentingBillRequest);
    Result update(UpdateRentalRequest updateRentalRequest);
    DataResult<List<RentingBillSearchListDto>> getRentingBillByUserId(int userId);
    DataResult<List<RentingBillSearchListDto>> getRentingBillByDateInterval(Date startDate, Date endDate);
}