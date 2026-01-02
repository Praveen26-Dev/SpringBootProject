package com.nextronixdemo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nextronixdemo.model.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	Optional<User> findByEmail(String email);
//	Optional<User> findById(String id);
	

//	Optional<User> findByPhoneNo(String formattedPhone);
	Optional<User> findByPhoneNo(String formattedPhone);
	Optional<User> findById(Integer id);
	boolean existsByEmail(String email);


	Optional<User> findByName(String identifier);

}
