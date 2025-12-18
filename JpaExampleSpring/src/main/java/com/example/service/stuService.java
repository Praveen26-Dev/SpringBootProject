package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.model.Student;
import com.example.repository.stuInterface;

@Service
public class stuService {
	
	@Autowired
	private stuInterface stuObj;
	
	public Student saveObj(Student st) {
		
		stuObj.save(st);
		return st;
	}
	
	public List<Student> getData() {
		System.out.println("Hello");
	return 	stuObj.findAll();
	}
	
	public String deleteData(Integer id) {
	 stuObj.deleteById(id);
	 return "Deleted";
	}
	
	public Student updateData(Integer id,Student st) {
		Student studentExist= stuObj.findById(id).orElseThrow(()-> new RuntimeException("Student Not Found"));
		
		studentExist.setName("Roopam");
		studentExist.setCity("Mumbai");
		studentExist.setBatch("P20");
		
		return stuObj.save(studentExist);
	}
	
	
	public Page<Student> pagi(int pg,int sz) {
	Page<Student>	page=stuObj.findAll(PageRequest.of(pg, sz));
	return page;
	}
	
	public List<Student> sortByField(String field,String direction) {
	Sort sort=direction.equals("asc")?Sort.by(field).ascending():Sort.by(field).descending();
	
	return stuObj.findAll(sort);
	}
	
	public List<Student> filter(String city) {
	  return stuObj.findByCity(city);
	}
	

}
