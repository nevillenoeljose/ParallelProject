package com.cg.pp.beans;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "history")
public class History {
	@Id
	String time;
	String histMobileNo;
	int operation;
	float histAmount;
	
	// Default constructor
	public History() {

	}
	
	// Parameterized Constructor
	public History(String histmobileno, int operation, float histamount, String time) {
		this.histMobileNo = histmobileno;
		this.operation = operation;
		this.histAmount = histamount;
		this.time = time;
	}
	
	// To String method for Customer object
	@Override
	public String toString() {
		return "\n" + "Number=" + histMobileNo + ", Operation Number=" + operation
				 + ", Transaction Amount= " + histAmount;
	}
	
	// Getters and Setters 
	public String getHistmobileno() {
		return histMobileNo;
	}
	public void setHistmobileno(String histmobileno) {
		this.histMobileNo = histmobileno;
	}
	public int getOperation() {
		return operation;
	}
	public void setOperation(int operation) {
		this.operation = operation;
	}
	public float getHistamount() {
		return histAmount;
	}
	public void setHistamount(float histamount) {
		this.histAmount = histamount;
	}
}
