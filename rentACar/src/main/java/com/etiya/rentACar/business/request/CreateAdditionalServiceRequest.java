package com.etiya.rentACar.business.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateAdditionalServiceRequest {

    @NotNull
    private String serviceName;

    @NotNull
    @Min(0)
    private Integer serviceDailyPrice;


}
