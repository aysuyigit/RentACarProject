package com.etiya.rentACar.dataAccess;

import com.etiya.rentACar.entities.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityDao extends JpaRepository<City, Integer> {

}
