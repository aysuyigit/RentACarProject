package com.etiya.rentACar.dataAccess;

import org.springframework.data.jpa.repository.JpaRepository;

import com.etiya.rentACar.entities.CorporateCustomer;

public interface CorporateCustomerDao extends JpaRepository<CorporateCustomer, Integer> {
    CorporateCustomer getByUserId(int userId);
}
