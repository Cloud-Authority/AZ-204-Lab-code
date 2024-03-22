package com.clsa.service;

import java.util.List;

import com.clsa.exceptions.DuplicateStudentException;
import com.clsa.exceptions.NoSuchStudentException;
import com.clsa.model.Student;

public interface StudentService {
	public boolean addStudent(Student student) throws DuplicateStudentException;
	public Student findStudentById(int studentId) throws NoSuchStudentException ;
	public List<Student> findAllStudents();
	public Student modifyStudent(Student student);
	public boolean deleteStudent(int studentId);
	
	public List<Student> findStudentsByCityName(String city);
	public List<Student> findStudentsByMarks(double min, double max);
}
