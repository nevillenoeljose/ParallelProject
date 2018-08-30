package com.cg.pp.beans;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Customers")
public class Customer {
	
	@Id
	private String mobileNo;
	private String name;
	private float balance;
	
	// Default constructor
	public Customer() {
		
	}
	
	// Parameterized Constructor
	public Customer(String name, String mobileNo, float balance) {
		this.name = name;
		this.mobileNo = mobileNo;
		this.balance = balance;
	}
	
	// Getters and Setters 
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public float getBalance() {
		return balance;
	}
	public void setBalance(float balance) {
		this.balance = balance;
	}
	
	// To String method for Customer object
	@Override
	public String toString() {
		return "\n" + "Customer name=" + name + ", Mobile Num=" + mobileNo
				 + ", Balance= " + balance ;
	}
	
}
