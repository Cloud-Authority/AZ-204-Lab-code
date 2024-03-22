package com.clsa.dao;


import org.springframework.data.jpa.repository.JpaRepository;

import com.clsa.model.Student;

public interface StudentRepository extends JpaRepository<Student, Integer>{
	
}
