package com.clsa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("address2")
@Scope("prototype")
@Entity
@Table(name="ADDRESS_TBL")
public class Address {
	@Id
	@Column(name="ADDRESS_ID")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int addressId;
	
	@Column(name="CITY", length=50)
	private String city;
	
	@Column(name="STATE", length=50)
	private String state;
	
	@Column(name="PIN", length=10)
	private String pin;
	
	public Address() {
	}
	public Address(int addressId, String city, String state, String pin) {
		super();
		this.addressId = addressId;
		this.city = city;
		this.state = state;
		this.pin = pin;
	}
	public int getAddressId() {
		return addressId;
	}
	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getPin() {
		return pin;
	}
	public void setPin(String pin) {
		this.pin = pin;
	}
	@Override
	public String toString() {
		return "Address [addressId=" + addressId + ", city=" + city + ", state=" + state + ", pin=" + pin + "]";
	}
}
