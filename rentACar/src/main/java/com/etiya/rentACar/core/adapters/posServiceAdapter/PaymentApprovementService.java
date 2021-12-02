package com.etiya.rentACar.core.adapters.posServiceAdapter;

import com.etiya.rentACar.entities.CreditCard;

public interface PaymentApprovementService {
    boolean checkPaymentSuccess(CreditCard creditCard);
}