package com.clsa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.clsa.exceptions.DuplicateStudentException;
import com.clsa.exceptions.NoSuchStudentException;
import com.clsa.model.Student;
import com.clsa.service.StudentService;

@RestController
@RequestMapping(path="students")
public class StudentRestController {
	@Autowired
	private StudentService service;
	
	// http://localhost:9090/student-api/students/byId/1
	@GetMapping(path="byId/{studentId}", produces="application/json")
	public ResponseEntity<Student> getStudentById(@PathVariable("studentId") int studentId)throws NoSuchStudentException {
		Student result = service.findStudentById(studentId);
		ResponseEntity<Student> response = null;
		if(result != null) {
			response = new ResponseEntity<Student>(result, HttpStatus.OK);
			return response;
		}
		throw new NoSuchStudentException("Student Not Found");
	}
	
	//http://localhost:9090/student-api/students - - Method Post
	@PostMapping(consumes="application/json")
	public boolean addStudent(@RequestBody Student student) throws DuplicateStudentException {
		return service.addStudent(student);
	}
	
	//http://localhost:9090/student-api/students - - Method Get
	@GetMapping(produces="application/json")
	public List<Student> getAllStudents(){
		List<Student> result = service.findAllStudents();
		return result;
	}
	
	//http://localhost:9090/student-api/students - - Method Put
	@PutMapping(consumes=MediaType.APPLICATION_JSON_VALUE)
	public Student updateStudent(@RequestBody Student student) {
		return service.modifyStudent(student);
	}
	
	//http://localhost:9090/student-api/students/100 - Method Delete
	@DeleteMapping(path="{studentId}", produces=MediaType.APPLICATION_JSON_VALUE)
	public boolean deleteStudent(@PathVariable("studentId") int studentId) {
		return service.deleteStudent(studentId);
	}
	
	//http://localhost:9090/student-api/students/byCity/Mumbai
	@GetMapping(path="byCity/{city}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Student> findStudetsByCity(@PathVariable("city") String city){
		return service.findStudentsByCityName(city);
	}
	
	//http://localhost:9090/student-api/students/50/70
	@GetMapping(path="{min}/{max}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Student> findStudentsByMarks(@PathVariable("min") double min, @PathVariable("max") double max){
		return service.findStudentsByMarks(min, max);
	}
	
	@ExceptionHandler(value=NoSuchStudentException.class)
	@ResponseStatus(code=HttpStatus.INTERNAL_SERVER_ERROR, reason="Student Not Found.")
	public void handleNoSuchStudent() {
		
	}
	
	@ExceptionHandler(value=DuplicateStudentException.class)
	@ResponseStatus(code=HttpStatus.INTERNAL_SERVER_ERROR, reason="Student already exists.")
	public void handleDuplicateStudentException() {
		
	}
}


