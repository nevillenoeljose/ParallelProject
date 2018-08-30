package com.cg.pp.services;

import com.cg.pp.beans.Customer;
import com.cg.pp.beans.History;
import com.cg.pp.exceptions.InputException;
import com.cg.pp.dao.WalletDao;
import com.cg.pp.dao.WalletDaoImpl;
public class WalletServiceImpl implements WalletService{
	WalletDao dao = new WalletDaoImpl();
	
	public History addToHistory(History history) {
		return dao.addToHistory(history);
	}
	
	public History showHistory(String mobileno) {
		return dao.showHistory(mobileno);
	}
	
	public Customer findAccount(String mobileno) {
		return dao.findAccount(mobileno);
	}

	public float showBalance(String mobileNo){
		return dao.showBalance(mobileNo);
	}
	
	public Customer fundTransfer(String sourceMobileNo, String targetMobileNo, float amount) {
		return dao.fundTransfer(sourceMobileNo, targetMobileNo, amount);
	
	}

	public Customer depositAmount(String mobileNo, float amount) {
		return dao.depositAmount(mobileNo, amount);
	
	}

	public Customer withdrawAmount(String mobileNo, float amount) {
		return dao.withdrawAmount(mobileNo, amount);
	
	}

	public Customer createAccount(Customer customer) {
		return dao.createAccount(customer);
	}
	
	public boolean findOne(String mobileNo){
		return dao.findOne(mobileNo);
	}
	
	public boolean validateCustomer(Customer customer) throws InputException{
		if(!customer.getMobileNo().matches("[0-9]{10}"))
			throw new InputException("Please enter a valid 10 digit mobile number");
		if(customer.getBalance()<0)
			throw new InputException("Balance cannot be negative!");
			return true;
	}
	
	public boolean validateNumber(String mobileno) throws InputException{
		if(!mobileno.matches("[0-9]{10}"))
			throw new InputException("Please enter a valid 10 digit mobile number");
		if(!findOne(mobileno))
			throw new InputException("Account associated with entered mobile number not found!");
			return true;
	}

	public boolean validateAmount(float amount, Customer customer) throws InputException{
		if(amount > customer.getBalance())
			throw new InputException("You do not have sufficient balance to perform this transaction!");
			return true;
	}
	
	public boolean validateTransfer(String acfrom, String acto) throws InputException{
		if(acfrom.compareTo(acto) == 0)
			throw new InputException("Accounts cannot be the same!");
			return true;
	}
}