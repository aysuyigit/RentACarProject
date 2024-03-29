package com.etiya.rentACar.dataAccess;

import org.springframework.data.jpa.repository.JpaRepository;

import com.etiya.rentACar.entities.Brand;

public interface BrandDao extends JpaRepository<Brand, Integer> {
	Brand getByBrandName(String brandName);

}
