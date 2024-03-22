package com.clsa.service;

import java.util.List;
import java.util.Optional;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.clsa.dao.StudentRepository;
import com.clsa.exceptions.DuplicateStudentException;
import com.clsa.exceptions.NoSuchStudentException;
import com.clsa.model.Student;

@Service("service")
public class StudentServiceImpl implements StudentService {
	@Autowired
	private StudentRepository repository;

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public boolean addStudent(Student student) throws DuplicateStudentException{
		if(validateStudent(student)) {
			try {
				Student result = repository.save(student);
				return (result.getStudentId() > 0) ? true : false;
			}catch(ConstraintViolationException e) {
				throw new DuplicateStudentException("Student already exists.");
			}
		}
		return false;
	}

	@Override
	public Student findStudentById(int studentId) throws NoSuchStudentException {
		Optional<Student> result = repository.findById(studentId);
		Student student = null;
		if (result.isPresent()) {
			student = result.get();
		} else {
			throw new NoSuchStudentException("Student Not Found");
		}
		return student;
	}

	@Override
	public List<Student> findAllStudents() {
		return repository.findAll();
	}

	@Override
	@Transactional
	public Student modifyStudent(Student student) {
		return null;
	}

	@Override
	@Transactional
	public boolean deleteStudent(int studentId) {
		return false;
	}

	@Override
	public List<Student> findStudentsByCityName(String city) {
		return null;
	}

	@Override
	public List<Student> findStudentsByMarks(double min, double max) {
		return null;
	}

	private boolean validateStudent(Student student) {
		if(!student.getStudentName().matches("[A-Za-z]+")) {
			return false;
		}else if(student.getStudentScore() < 0) {
			return false;
		}
		return true;
	}
}
