package com.example.demo.service;

public class PersonException extends Exception{

	private String exceptionMessage;

	public String getExceptionMessage() {
		return exceptionMessage;
	}

	public void setExceptionMessage(String exceptionMessage) {
		this.exceptionMessage = exceptionMessage;
	}

	@Override
	public String toString() {
		return "PersonException [exceptionMessage=" + exceptionMessage + "]";
	}
	
	
}
