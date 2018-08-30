package com.cg.pp.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.cg.pp.beans.Customer;
import com.cg.pp.beans.History;

public class WalletDaoImpl implements WalletDao{
	
	private EntityManager entityManager;
	
	public WalletDaoImpl() {
		entityManager = JPAUtil.getEntityManager();
	}
	
	@Override
	public History addToHistory(History history) {
		entityManager.getTransaction().begin();
		entityManager.persist(history);
		entityManager.getTransaction().commit();
		System.out.println("Transaction history added succesfully");
		return null;
	}

	@Override
	public History showHistory(String mobileNo) {
		ArrayList<History> sHistory = new ArrayList<History>();
		String sql = "SELECT history FROM History history WHERE histmobileno = :qMobileNo";
		TypedQuery<History> query = entityManager.createQuery(sql, History.class);
		query.setParameter("qMobileNo", mobileNo);
		List<History> historyDB = query.getResultList();
		for (History history : historyDB) {
		    if (history.getHistmobileno().matches(mobileNo)) {
		        sHistory.add(history);
		    }
		}
		String op = null;
        System.out.println("-----------------------------------------------------------------------------");
    	System.out.printf("%10s %30s %20s", "OPERATION", "MOBILE NO", "AMOUNT");
    	System.out.println();
    	System.out.println("-----------------------------------------------------------------------------");
    	for(History history: sHistory){
    		if(history.getOperation() == 3) {
    			op = "Deposit      ";
    		}
    		if(history.getOperation() == 4) {
    			op = "Withdrawal   ";
    		}
    		if(history.getOperation() == 5) {
    			op = "Fund Transfer";
    		}
    		System.out.format("%10s %30s %20s",
    				op, history.getHistmobileno(), history.getHistamount());
    		System.out.println();
    	}
    	System.out.println("-----------------------------------------------------------------------------");
		return null;
	}
	
	public boolean findOne(String mobileNo){
		String sql = "SELECT customer FROM Customer customer WHERE mobileNo = :qMobileNo";
		TypedQuery<Customer> query = entityManager.createQuery(sql, Customer.class);
		query.setParameter("qMobileNo", mobileNo);
		Customer customer = query.getSingleResult();
		if(customer.equals(null)) {
			return false;
		}else
			return true;
		}

	@Override
	public Customer createAccount(Customer customer) {
		entityManager.getTransaction().begin();
		entityManager.persist(customer);
		entityManager.getTransaction().commit();
		System.out.println(customer + " added succesfully");
		return customer;
		}
	
	@Override
	public Customer findAccount(String mobileNo) {
		String sql = "SELECT customer FROM Customer customer WHERE mobileNo = :qMobileNo";
		TypedQuery<Customer> query = entityManager.createQuery(sql, Customer.class);
		query.setParameter("qMobileNo", mobileNo);
		Customer customer = query.getSingleResult();
	    return customer;
	}
	
	
//	
	@Override
	public float showBalance(String mobileNo){
	    String sql = "SELECT customer FROM Customer customer WHERE mobileno = :qMobileNo";
		TypedQuery<Customer> query = entityManager.createQuery(sql, Customer.class);
		query.setParameter("qMobileNo", mobileNo);
		Customer customer = query.getSingleResult();
		System.out.println("Balance available in your account is: " +customer.getBalance());
	    return customer.getBalance();
	}

	@Override
	public Customer depositAmount(String mobileNo, float amount) {
		String sql = "SELECT customer FROM Customer customer WHERE mobileno = :qMobileNo";
		TypedQuery<Customer> query = entityManager.createQuery(sql, Customer.class);
		query.setParameter("qMobileNo", mobileNo);
		float oldBal = query.getSingleResult().getBalance();
		float newBal = oldBal + amount;
		Customer updatedCust = query.getSingleResult();
		updatedCust.setBalance(newBal);
		entityManager.getTransaction().begin();
		entityManager.merge(updatedCust);
		entityManager.getTransaction().commit();
		System.out.println("Deposit of " + amount + " successful!");
		System.out.println("Balance available in your account is: " + updatedCust.getBalance());
		return null;
	}

	@Override
	public Customer withdrawAmount(String mobileNo, float amount) {
		String sql = "SELECT customer FROM Customer customer WHERE mobileno = :qMobileNo";
		TypedQuery<Customer> query = entityManager.createQuery(sql, Customer.class);
		query.setParameter("qMobileNo", mobileNo);
		float oldBal = query.getSingleResult().getBalance();
		float newBal = oldBal - amount;
		Customer updatedCust = query.getSingleResult();
		updatedCust.setBalance(newBal);
		entityManager.getTransaction().begin();
		entityManager.merge(updatedCust);
		entityManager.getTransaction().commit();
		System.out.println("Withdrawal of " + amount + " successful!");
		System.out.println("Balance available in your account is: " + updatedCust.getBalance());
		return null;
	}
	
	@Override
	public Customer fundTransfer(String acFrom, String acTo, float amount) {
		String sql = "SELECT customer FROM Customer customer WHERE mobileno = :qMobileNo";
		TypedQuery<Customer> queryFrom = entityManager.createQuery(sql, Customer.class);
		TypedQuery<Customer> queryTo = entityManager.createQuery(sql, Customer.class);
		queryFrom.setParameter("qMobileNo", acFrom);
		queryTo.setParameter("qMobileNo", acTo);
		float acFromBal = queryFrom.getSingleResult().getBalance();
		float acToBal = queryTo.getSingleResult().getBalance();
		float acFromBalNew = acFromBal - amount;
		float acToBalNew = acToBal + amount;
		Customer updatedCustFrom = queryFrom.getSingleResult();
		updatedCustFrom.setBalance(acFromBalNew);
		Customer updatedCustTo = queryTo.getSingleResult();
		updatedCustTo.setBalance(acToBalNew);
		entityManager.getTransaction().begin();
		entityManager.merge(updatedCustFrom);
		entityManager.merge(updatedCustTo);
		entityManager.getTransaction().commit();
		System.out.println("Transaction succesful!");
		System.out.println("Amount " + amount + " withdrawn from account " 
							+ acFrom + " and deposited in account " + acTo );
		System.out.println("Avaliable balances are " + acFromBalNew + " in " 
							+ acFrom + " and " + acToBalNew + " in " + acTo);
		return null;
	}
	
}
