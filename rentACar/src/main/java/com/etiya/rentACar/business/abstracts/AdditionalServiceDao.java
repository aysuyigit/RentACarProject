package com.etiya.rentACar.business.abstracts;

import com.etiya.rentACar.entities.AdditionalService;
import com.etiya.rentACar.entities.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdditionalServiceDao extends JpaRepository<AdditionalService, Integer> {
   boolean existsAdditionalServiceByName(String serviceName);
   AdditionalService getByServiceId(int serviceId);
}
