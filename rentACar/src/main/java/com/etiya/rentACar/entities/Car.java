package com.etiya.rentACar.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="cars")
public class Car {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)

	@Column(name="car_id")
	private int carId;
	@Column(name="model_year")
	private String modelYear;
	@Column(name="daily_price")
	private double dailyPrice;
	@Column(name="description")
	private String description;
	@Column(name="findeks_point")
	private int findeksPointCar;

	@ManyToOne
	@JoinColumn(name="brand_id")
	private Brand brand;

	@ManyToOne
	@JoinColumn(name="color_id")
	private Color color;

	@OneToMany(mappedBy="car")
	private List<Rental> rentals;

	@OneToMany(mappedBy="car")
	private List<CarImage> carImages;

	@OneToMany(mappedBy="car")
	private List<Maintenance> maintenances;



	@Column(name="kilometer")
	private int kilometer;

	@OneToMany(mappedBy = "car")
	private List<CarDamage> carDamages;

	@ManyToOne
	@JoinColumn(name = "city_id")
	private City city;
}