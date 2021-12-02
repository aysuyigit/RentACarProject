package com.etiya.rentACar.dataAccess;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.etiya.rentACar.entities.CreditCard;

@Repository
public interface CreditCardDao extends JpaRepository<CreditCard	, Integer>{
	
}
	
