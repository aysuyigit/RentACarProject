package com.etiya.rentACar.business.externalService;

import com.etiya.rentACar.entities.complexTypes.CreditCardDetail;

public class FakePosService {

    public boolean isPaymentSuccessful(CreditCardDetail creditCardDetail){
        if (creditCardDetail.getCardNumber().isEmpty()){
            return false;
        }
        return true;
    }
}