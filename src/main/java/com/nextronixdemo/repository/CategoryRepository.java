package com.nextronixdemo.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nextronixdemo.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{
	List<Category> findByParentId(Long parentId);
	List<Category> findByParentIdIsNull();

}
