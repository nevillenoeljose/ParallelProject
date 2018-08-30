package com.cg.pp.ui;

// Required Imports
import java.time.LocalDateTime;
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
		
		// Integer variables declared for the purpose of looping program
		int choice = 0;
		int cont = 0;
		
		// Do while Loop for looping application until exited
		do{
			
		// Main Menu	
		System.out.println();
		System.out.println("Menu");
		System.out.println("1. Create New Account");
		System.out.println("2. Display Balance");
		System.out.println("3. Deposit Amount");
		System.out.println("4. Withdraw Amount");
		System.out.println("5. Fund Transfer");
		System.out.println("6. Show Transaction History");
		System.out.println("7. Exit");
		System.out.println("Enter your choice");
		
		// Exits application if input is not integer 
		// Asks for input again if input is integer but not an applicable one
		
		if(sc.hasNextInt()){
			choice = sc.nextInt();	
		System.out.println();
		
		// Switch case for the 6 functionalities
		switch (choice){
		
		case 1: 
			System.out.println("Enter Mobile Number: ");
			String mobileNo1 = sc.next();
			System.out.println("Enter Customer Name: ");
			String name = sc.next();
			System.out.println("Enter Opening Balance: ");
			float balance = sc.nextFloat();
			Customer customer = new Customer(name, mobileNo1, balance);
			
			// Required validate functions called to check user input
			// failing which error shall be thrown
			try{
				if(wser.validateCustomer(customer)){
					Customer newAC = wser.createAccount(customer);
					float newBal = newAC.getBalance();
					String newName = newAC.getName();
					System.out.println("New Customer: " + newName 
							+ " added succesfully. Current balance is " + newBal );
				}
			} catch (Exception e1){
				System.err.println(e1.getMessage());
				System.out.println("Please try again!");
				break;
			} 			
			// Asks user if he wants to use application again for another functionality 
			// or if he wants to exit application. Integer cont declared earlier is used
			System.out.println("Press 1 to exit or 0 to reuse application!");
			cont = sc.nextInt();
			break;
			
		case 2: 
			System.out.println("Enter Mobile Number: ");
			String mobileNo2 = sc.next();
			
			// Required validate functions called to check user input
			// failing which error shall be thrown
			try{
				if(wser.validateNumber(mobileNo2)){
					wser.showBalance(mobileNo2);
					break;
				}
			} catch (Exception e2){
				System.err.println(e2.getMessage());
				System.out.println("Please try again!");
				break;
				}
			System.out.println("Press 1 to exit or 0 to reuse application!");
			cont = sc.nextInt();
			break;
			
		case 3: 
			System.out.println("Enter Mobile Number: ");
			String mobileNo3 = sc.next();
			
			// Required validate functions called to check user input
			// failing which error shall be thrownv
			try{
				if(wser.validateNumber(mobileNo3)){
					float curBal = wser.showBalance(mobileNo3);
					System.out.println("Please enter the amount you wish to deposit.");
					float amount = sc.nextFloat();
					wser.depositAmount(mobileNo3, amount);
					LocalDateTime timeRaw =  LocalDateTime.now();
					String time = timeRaw.toString();
					History hist3 = new History(mobileNo3, choice, amount, time);
					wser.addToHistory(hist3);
					}
			} catch (Exception e3){
				System.err.println(e3.getMessage());
				System.out.println("Please try again!");
				break;
				}

			System.out.println("Press 1 to exit or 0 to reuse application!");
			cont = sc.nextInt();
			break;
			
		case 4: 
			System.out.println("Enter Mobile Number: ");
			String mobileNo4 = sc.next();
			
			// Required validate functions called to check user input
			// failing which error shall be thrown
			try{
				if(wser.validateNumber(mobileNo4)){
					wser.showBalance(mobileNo4);
					System.out.println("Please enter the amount you wish to withdraw.");
					float amount = sc.nextFloat();
					Customer cust = wser.findAccount(mobileNo4);
					if(wser.validateAmount(amount, cust)) {
						wser.withdrawAmount(mobileNo4, amount);
						LocalDateTime timeRaw =  LocalDateTime.now();
						String time = timeRaw.toString();						
						History hist4 = new History(mobileNo4, choice, amount, time);
						wser.addToHistory(hist4);
					}
					}
			} catch (Exception e4){
				System.err.println(e4.getMessage());
				System.out.println("Please try again!");
				break;
				}
			System.out.println("Press 1 to exit or 0 to reuse application!");
			cont = sc.nextInt();
			break;
			
		case 5:
			System.out.println("Enter the mobile number to transfer funds FROM: ");
			String acFrom = sc.next();
			System.out.println("Enter the mobile number to transfer funds TO: ");
			String acTo = sc.next();
			System.out.println("Please enter the amount you wish to transfer: ");
			float amount = sc.nextFloat();
			Customer custFrom = wser.findAccount(acFrom);
			
			// Required validate functions called to check user input
			// failing which error shall be thrown
			try{
			if(wser.validateNumber(acFrom) && wser.validateNumber(acTo) 
			   && wser.validateAmount(amount, custFrom) && wser.validateTransfer(acFrom, acTo)){
						wser.fundTransfer(acFrom, acTo, amount);
						LocalDateTime timeRawFrom =  LocalDateTime.now();
						String timeFrom = timeRawFrom.toString().concat("From");
						LocalDateTime timeRawTo =  LocalDateTime.now();
						String timeTo = timeRawTo.toString().concat("To");
						History hist5 = new History(acFrom, choice, amount, timeFrom);
						wser.addToHistory(hist5);
						History hist6 = new History(acTo, choice, amount, timeTo);
						wser.addToHistory(hist6);
				}
			} catch (Exception e5){
				System.err.println(e5.getMessage());
				System.out.println("Please try again!");
				break;
				}
			System.out.println("Press 1 to exit or 0 to reuse application!");
			cont = sc.nextInt();
			break;
			
		case 6: 
			System.out.println("Enter Mobile Number: ");
			String mobileNo6 = sc.next();
			
			// Required validate functions called to check user input
			// failing which error shall be thrown
			try {
				if(wser.findOne(mobileNo6)) {
					wser.showHistory(mobileNo6);
				}
			} catch (Exception e6){
				System.err.println(e6.getMessage());
				System.out.println("Please try again!");
				break;
			}
			System.out.println("Press 1 to exit or 0 to reuse!");
			cont = sc.nextInt();
			break;
			
		case 7:
			cont = 1;
			break;
		default:
			// Integer input given but not an applicable one
			System.out.println("Please enter a valid input!");
		}
		}
		else {
			// Exits application in case of non integer input 
			// to prevent infinite looping due to do-while case
			System.out.println("Please enter a valid input!");
			cont = 1;
			break;			
		}
		} while(cont != 1);
		System.out.println("Thank you for using the application. Please rerun the application if needed.");
		sc.close();
		
	}
	
}
