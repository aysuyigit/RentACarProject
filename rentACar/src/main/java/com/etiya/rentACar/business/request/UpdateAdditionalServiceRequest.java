package com.etiya.rentACar.business.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private int serviceDailyPrice;
}
