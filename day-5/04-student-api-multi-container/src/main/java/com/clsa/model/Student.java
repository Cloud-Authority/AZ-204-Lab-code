package com.clsa.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("student")
@Scope("prototype")
@Entity
@Table(name="STUDENT_TBL")
public class Student {
	@Id
	@Column(name="STUDENT_ID")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int studentId;
	
	@Column(name="STUDENT_NAME", length=40)
	private String studentName;
	
	@Column(name="STUDENT_SCORE")
	private double studentScore;
	
	@Autowired
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="ADDRESS_ID")
	private Address address;
	
	public Address getAddress() {
		return address;
	}
	// Setter injection
	public void setAddress(Address address) {
		this.address = address;
	}
	// constructor injection
	public Student(Address address) {
		System.out.println("Student object created by passing address object");
		this.address = address;
	}
	
	public Student() {
	}
	public Student(int studentId, String studentName, double studentScore) {
		super();
		this.studentId = studentId;
		this.studentName = studentName;
		this.studentScore = studentScore;
	}
	public int getStudentId() {
		return studentId;
	}
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public double getStudentScore() {
		return studentScore;
	}
	public void setStudentScore(double studentScore) {
		this.studentScore = studentScore;
	}
	@Override
	public String toString() {
		return "Student [studentId=" + studentId + ", studentName=" + studentName + ", studentScore=" + studentScore
				+ "]";
	}
	
}
