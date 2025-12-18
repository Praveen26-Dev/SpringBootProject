package com.example.controller;
import com.example.service.stuService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Student;

@RestController
@RequestMapping("/api")
public class stController {

	@Autowired
	private stuService std;

	@PostMapping("/save")
	public Student saveData(@RequestBody Student obj) {
		std.saveObj(obj);
		
		return obj;
		
	}
	
	@GetMapping("/get")
	public List<Student> get() {
		return std.getData();
	}
	
	@DeleteMapping("/delete/{id}")
	public String delet(@PathVariable Integer id) {
		
		return std.deleteData(id);
	}
	
	@PutMapping("/update/{id}")
	public Student update(@PathVariable Integer id,@RequestBody Student st) {
		
		return std.updateData(id,st);
	}
	
	@PostMapping("/page")
	public Page<Student> paginate(@RequestParam int pg,@RequestParam int sz) {
		return std.pagi(pg, sz);
		
	}
	
	@PostMapping("/sort")
	public List<Student> sort(@RequestParam String field,@RequestParam String direction) {
	  return	std.sortByField(field, direction);
	}
	
	@PostMapping("/filter")
	public List<Student> filter(@RequestParam String city) {
		return std.filter(city);
	}
	
}
