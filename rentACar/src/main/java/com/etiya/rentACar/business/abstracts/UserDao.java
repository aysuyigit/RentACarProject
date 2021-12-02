package com.etiya.rentACar.business.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.etiya.rentACar.entities.User;

@Repository
public interface UserDao extends JpaRepository<User, Integer> {

   boolean existsByeMail(String eMail);
	
	User getByeMail(String eMail);
}
