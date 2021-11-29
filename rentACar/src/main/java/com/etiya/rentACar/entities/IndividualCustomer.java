package com.etiya.rentACar.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="individual_customers")
@PrimaryKeyJoinColumn(name = "user_id")
public class IndividualCustomer extends User {

	/*
	 * @Id
	 * 
	 * @GeneratedValue(strategy= GenerationType.IDENTITY)
	 * 
	 * @Column(name="individual_id") private int individualId;
	 */
	
	@Column(name="first_name")
	private String firstName;
	
	@Column(name="last_name")
	private String lastName;
	
	@Column(name="birthday")
	private String birthday;
	
	
}
