package com.etiya.rentACar.business.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class UpdateAdditionalServiceRequest {

    @NotNull
    private int serviceId;

    @NotNull
    private String serviceName;

    @NotNull
    @Min(0)
    private int serviceDailyPrice;
}
