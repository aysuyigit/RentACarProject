package com.etiya.rentACar.ws;

import com.etiya.rentACar.business.abstracts.AdditionalServiceService;
import com.etiya.rentACar.business.dtos.AdditionalServiceSearchListDto;
import com.etiya.rentACar.business.request.CreateAdditionalServiceRequest;
import com.etiya.rentACar.business.request.DeleteAdditionalServiceRequest;
import com.etiya.rentACar.business.request.UpdateAdditionalServiceRequest;
import com.etiya.rentACar.core.utilities.results.DataResult;
import com.etiya.rentACar.core.utilities.results.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/additionalService")
public class AdditionalServiceController {

    private AdditionalServiceService additionalServiceService;

    @Autowired
    public AdditionalServiceController(AdditionalServiceService additionalServiceService) {
        this.additionalServiceService = additionalServiceService;
    }

    @GetMapping("/getAll")
    public DataResult<List<AdditionalServiceSearchListDto>> getAll(){
        return  this.additionalServiceService.getAll();
    }
    @PostMapping("/add")
    public Result save(@RequestBody @Valid CreateAdditionalServiceRequest createAdditionalServiceRequest){
        return this.additionalServiceService.save(createAdditionalServiceRequest);
    }

    @PutMapping("/update")
    public Result update(@RequestBody @Valid UpdateAdditionalServiceRequest updateAdditionalServiceRequest){
        return this.additionalServiceService.update(updateAdditionalServiceRequest);
    }
    @DeleteMapping("/delete")
    public  Result delete(@RequestBody @Valid DeleteAdditionalServiceRequest deleteAdditionalServiceRequest){
        return this.additionalServiceService.delete(deleteAdditionalServiceRequest);
    }





}
