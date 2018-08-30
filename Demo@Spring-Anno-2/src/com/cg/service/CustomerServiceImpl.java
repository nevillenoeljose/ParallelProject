package com.cg.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.model.Customer;
import com.cg.repository.CustomerRepository;
import com.cg.repository.HibernateCustomerRepositoryImpl;
@Service("customerService")
public class CustomerServiceImpl implements CustomerService {

	private CustomerRepository customerRepository;

	/*
	 * { customerRepository= new HibernateCustomerRepositoryImpl(); }
	 */

	public List<Customer> findAll() {
		return customerRepository.findAll();
	}
	
	public CustomerServiceImpl() {
		System.out.println("Going inside default constructor CustomerServiceImpl()");
		System.out.println("customerRepostory ref: " + customerRepository);
	}
	
	public CustomerServiceImpl(CustomerRepository customerRepository) {
		System.out.println("Going inside parameterized constructor");
		this.customerRepository = customerRepository;
		System.out.println("customer repository ref: " + customerRepository);
	}
	@Autowired
	public void setCustomerRepository(CustomerRepository customerRepository) {
		System.out.println("Going inside setter");
		this.customerRepository = customerRepository;
	}
	
	
	
	
	
}
