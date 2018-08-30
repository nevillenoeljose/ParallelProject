package com.cg.pp.exceptions;

public class InputException extends Exception {
	private static final long serialVersionUID = 1L;
	public InputException(){
		
	}
	
	public InputException(String msg) {
		super(msg);
	}
}
