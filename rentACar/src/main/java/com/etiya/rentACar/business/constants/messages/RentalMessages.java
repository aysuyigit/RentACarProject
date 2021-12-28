package com.etiya.rentACar.business.constants.messages;

public class RentalMessages {
    public static final String add = "New rental log is added.";
    public static final String delete = "Rental log is deleted.";
    public static final String update = "Rental log is updated.";
    public static final String updateAndCreateBill = "Rental log is updated and the renting bill is created.";
    public static final String carIsOnRental = "The car is on rental so it cannot be rented until it returns!";
    public static final String additionalServiceDeclaration = "Additional services are declared in a wrong way! Please write the ids of the services while separating them with commas. If you don't want any additional service, leave it empty.";
    public static final String dateAccordance = "The end date cannot be earlier than the start date!";
    public static final String rentalIdDoesNotExist = "Rental ID does not exist!";
    public static final String billsAlreadyCreated = " billsAlreadyCreated";
    public static final String returnKilometerIsBiggerThanRentKilometer = "ReturnKilometerIsBiggerThanRentKilometer";

}
