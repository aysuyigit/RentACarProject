package com.etiya.rentACar.dataAccess;

import com.etiya.rentACar.entities.AdditionalService;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdditionalServiceDao extends JpaRepository<AdditionalService, Integer> {
   boolean existsAdditionalServiceByName(String serviceName);
   AdditionalService getByServiceId(int serviceId);
}
