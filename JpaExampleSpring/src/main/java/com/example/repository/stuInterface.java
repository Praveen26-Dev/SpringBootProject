package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.model.Student;
import java.util.List;


public interface stuInterface  extends JpaRepository<Student, Integer>{
	
	List<Student> findByCity(String city);
}
