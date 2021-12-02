package com.etiya.rentACar.business.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.etiya.rentACar.entities.Color;

public interface ColorDao extends JpaRepository<Color, Integer>{

}
