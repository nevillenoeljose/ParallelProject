package com.cg.pp.ui;

import java.util.Scanner;

import com.cg.pp.beans.Customer;
import com.cg.pp.beans.History;
import com.cg.pp.exceptions.InputException;
import com.cg.pp.services.WalletService;
import com.cg.pp.services.WalletServiceImpl;

public class Client {
	public static void main( String[] args ) throws InputException{
		WalletService wser = new WalletServiceImpl();
		Scanner sc = new Scanner(System.in);
		int choice = 0;
		int cont = 0;
		do{
		System.out.println("----------------------------");
		System.out.println("Menu");
		System.out.println("1. Create New Account");
		System.out.println("2. Display Balance");
		System.out.println("3. Deposit Amount");
		System.out.println("4. Withdraw Amount");
		System.out.println("5. Fund Transfer");
		System.out.println("6. Show Transaction History");
		System.out.println("7. Exit");
		System.out.println("Enter your choice");
		choice = sc.nextInt();
		System.out.println();
		switch (choice){
		
		case 1: 
			System.out.println("Enter Mobile Number: ");
			String mobileno1 = sc.next();
			System.out.println("Enter Customer Name: ");
			String name = sc.next();
			System.out.println("Enter Opening Balance: ");
			float balance = sc.nextFloat();
			Customer customer = new Customer(name, mobileno1, balance);
			try{
				if(wser.validateCustomer(customer)){
					Customer newac = wser.createAccount(customer);
					float newbal = newac.getBalance();
					String newname = newac.getName();
					System.out.println("New Customer: " + newname + " added succesfully. Current balance is " + newbal );
				}
			} catch (Exception e1){
				System.err.println(e1.getMessage());
				}
			System.out.println("Press 1 to exit or 0 to reuse!");
			cont = sc.nextInt();
			break;
			
		case 2: 
			System.out.println("Enter Mobile Number: ");
			String mobileno2 = sc.next();
			try{
				if(wser.validateNumber(mobileno2)){
					wser.showBalance(mobileno2);
					break;
				}
			} catch (Exception e2){
				System.err.println(e2.getMessage());
				}
			System.out.println("Press 1 to exit or 0 to reuse!");
			cont = sc.nextInt();
			break;
			
		case 3: 
			System.out.println("Enter Mobile Number: ");
			String mobileno3 = sc.next();
			try{
				if(wser.validateNumber(mobileno3)){
					float curbal = wser.showBalance(mobileno3);
					System.out.println("Your current available balance is: " + curbal 
							+ ". Please enter the amount you wish to deposit.");
					float amount = sc.nextFloat();
					wser.depositAmount(mobileno3, amount);
					History hist3 = new History(mobileno3, choice, amount);
					wser.addToHistory(hist3);
					}
			} catch (Exception e3){
				System.err.println(e3.getMessage());
				}

			System.out.println("Press 1 to exit or 0 to reuse!");
			cont = sc.nextInt();
			break;
			
		case 4: 
			System.out.println("Enter Mobile Number: ");
			String mobileno4 = sc.next();
			try{
				if(wser.validateNumber(mobileno4)){
					wser.showBalance(mobileno4);
					System.out.println("Please enter the amount you wish to withdraw.");
					float amount = sc.nextFloat();
					Customer cust = wser.findAccount(mobileno4);
					if(wser.validateAmount(amount, cust)) {
						wser.withdrawAmount(mobileno4, amount);
						History hist4 = new History(mobileno4, choice, amount);
						wser.addToHistory(hist4);
					}
					}
			} catch (Exception e4){
				System.err.println(e4.getMessage());
				}
			System.out.println("Press 1 to exit or 0 to reuse!");
			cont = sc.nextInt();
			break;
			
		case 5:
			System.out.println("Enter the mobile number to transfer funds FROM: ");
			String acfrom = sc.next();
			System.out.println("Enter the mobile number to transfer funds TO: ");
			String acto = sc.next();
			System.out.println("Please enter the amount you wish to transfer: ");
			float amount = sc.nextFloat();
			Customer custfrom = wser.findAccount(acfrom);
			try{
			if(wser.validateNumber(acfrom) && wser.validateNumber(acto) 
			   && wser.validateAmount(amount, custfrom) && wser.validateTransfer(acfrom, acto)){
						wser.fundTransfer(acfrom, acto, amount);
						History hist5 = new History(acfrom, choice, amount);
						wser.addToHistory(hist5);
						History hist6 = new History(acto, choice, amount);
						wser.addToHistory(hist6);
				}
			} catch (Exception e5){
				System.err.println(e5.getMessage());
				}
			System.out.println("Press 1 to exit or 0 to reuse!");
			cont = sc.nextInt();
			break;
			
		case 6: 
			System.out.println("Enter Mobile Number: ");
			String mobileno6 = sc.next();
			if(wser.findOne(mobileno6)) {
				wser.showHistory(mobileno6);
			}
			System.out.println("Press 1 to exit or 0 to reuse!");
			cont = sc.nextInt();
			break;
			
		case 7:
			cont = 1;
			break;
		default:
			System.out.println("Please enter a valid input!");
		}
		} while(cont != 1);
		System.out.println("Thank you for using the application");
		sc.close();
	}
	
}
