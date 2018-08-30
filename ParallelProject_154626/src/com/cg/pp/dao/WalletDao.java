package com.cg.pp.dao;

import com.cg.pp.beans.Customer;
import com.cg.pp.beans.History;

public interface WalletDao {
	public Customer createAccount(Customer customer);
	public float showBalance (String mobileno);
	public Customer fundTransfer (String sourceMobileNo,String targetMobileNo, float amount);
	public Customer depositAmount (String mobileNo,float amount );
	public Customer withdrawAmount(String mobileNo, float amount);
	public boolean findOne(String mobileNo);
	public Customer findAccount(String mobileno);
	public History addToHistory(History history);
	public History showHistory(String mobileno);
}
