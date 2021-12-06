package com.etiya.rentACar.business.constants.messages;

public class RentalMessages {
    public static final String add = "Rental log is added.";
    public static final String delete = "Rental log is  deleted.";
    public static final String updateAndBilling = "Rental log is updated and renting bill is created.";
    public static final String update ="Rental log is updated.";
    public static final String checkCarIsReturned= "Car is not returned.Car can not rental.";
    public static final String checkFindeksPointAcceptability="Customer's findeks point is not enough";
    public static final String checkIfEndDateIsAfterStartDate = "End date cannot be earlier than the start date!";
    public static final String checkIfPaymentSuccesful = "Payment is not succesful.Car can not rent.";
    public static final String checkIfAdditionalServicesAreDeclaredInTrueFormat = "Additional services are declared in a wrong way! " +
            "Please write it as service id while seperating them with commas.";


}
