package com.etiya.rentACar.ws;

import java.sql.Date;
import java.util.List;

import javax.validation.Valid;

import com.etiya.rentACar.business.abstracts.RentingBillService;
import com.etiya.rentACar.business.dtos.RentingBillSearchListDto;
import com.etiya.rentACar.business.request.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.etiya.rentACar.core.utilities.results.DataResult;
import com.etiya.rentACar.core.utilities.results.Result;

@RestController
@RequestMapping("api/rentingbillcontroller")
public class RentingBillController {
    private RentingBillService rentingBillService;

    @Autowired
    public RentingBillController(RentingBillService rentingBillService) {
        super();
        this.rentingBillService = rentingBillService;
    }

    @GetMapping("list")
    public DataResult<List<RentingBillSearchListDto>> getAll(){
        return this.rentingBillService.getAll();
    }
    @GetMapping("getBillsByUserId")
    public DataResult<List<RentingBillSearchListDto>> getBillsByUserId(@RequestParam int userId){
        return this.rentingBillService.getRentingBillByUserId(userId);
    }
    @GetMapping("getBillsBetweenDates")
    public DataResult<List<RentingBillSearchListDto>> getBillsBetweenDates(@RequestParam Date startDate, @RequestParam Date endDate){
        return this.rentingBillService.getRentingBillByDateInterval(startDate, endDate);
    }


}
