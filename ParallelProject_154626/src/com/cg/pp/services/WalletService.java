package com.cg.pp.services;
import com.cg.pp.beans.Customer;
import com.cg.pp.beans.History;
import com.cg.pp.exceptions.InputException;

public interface WalletService {
	public Customer createAccount(Customer customer);
	public float showBalance (String mobileno);
	public Customer fundTransfer (String sourceMobileNo,String targetMobileNo, float amount);
	public Customer depositAmount (String mobileNo,float amount );
	public Customer withdrawAmount(String mobileNo, float amount);
	public boolean validateCustomer(Customer customer) throws InputException;
	public boolean validateNumber(String mobileno1) throws InputException;
	public boolean validateAmount(float amount, Customer customer) throws InputException;
	public boolean validateTransfer(String acfrom, String acto) throws InputException;
	public Customer findAccount(String mobileno);
	public boolean findOne(String mobileNo);
	public History addToHistory(History history);
	public History showHistory(String mobileno);
}
