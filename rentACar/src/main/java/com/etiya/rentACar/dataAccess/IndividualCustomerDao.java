package com.etiya.rentACar.dataAccess;

import org.springframework.data.jpa.repository.JpaRepository;


import com.etiya.rentACar.entities.IndividualCustomer;



public interface IndividualCustomerDao extends JpaRepository<IndividualCustomer, Integer>{
    IndividualCustomer getByUserId(int userId);
}
