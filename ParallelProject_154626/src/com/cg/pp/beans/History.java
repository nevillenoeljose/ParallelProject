package com.cg.pp.beans;

public class History {
	private int transactionId;
	private static int serial = 1;
	String histmobileno;
	int operation;
	float histamount;

	
	public History(String histmobileno, int operation, float histamount) {
		transactionId = serial++;
		this.histmobileno = histmobileno;
		this.operation = operation;
		this.histamount = histamount;				
	}
	
	@Override
	public String toString() {
		return "\n" + "Number=" + histmobileno + ", Operation Number=" + operation
				 + ", Transaction Amount= " + histamount;
	}
	
	public int getSerial() {
		return serial;
	}
	public void setSerial(int serial) {
		this.serial = serial;
	}
	public String getHistmobileno() {
		return histmobileno;
	}
	public void setHistmobileno(String histmobileno) {
		this.histmobileno = histmobileno;
	}
	public int getOperation() {
		return operation;
	}
	public void setOperation(int operation) {
		this.operation = operation;
	}
	public float getHistamount() {
		return histamount;
	}
	public void setHistamount(float histamount) {
		this.histamount = histamount;
	}
}
