package com.cg.pp.services;
import com.cg.pp.beans.Customer;
import com.cg.pp.beans.History;
import com.cg.pp.exceptions.InputException;

public interface WalletService {
	
	// Validate functions
	/* The validate functions throw the user defined InputException as is mentioned
	 * The findOne function throws default SQL Exception. Hence not explicitly mentioned
	 */
	public boolean findOne(String mobileNo);
	public boolean validateCustomer(Customer customer) throws InputException;
	public boolean validateNumber(String mobileno1) throws InputException;
	public boolean validateAmount(float amount, Customer customer) throws InputException;
	public boolean validateTransfer(String acfrom, String acto) throws InputException;
	
	// Application functionalities
	public Customer createAccount(Customer customer);
	public Customer fundTransfer (String sourceMobileNo,String targetMobileNo, float amount);
	public Customer depositAmount (String mobileNo,float amount );
	public Customer withdrawAmount(String mobileNo, float amount);
	public Customer findAccount(String mobileno);
	public float showBalance (String mobileno);
	
	// Show transaction history functionalities
	public History addToHistory(History history);
	public History showHistory(String mobileno);
	
}