package com.navvis.german;

public class PhoneNumbersException extends RuntimeException {

	public PhoneNumbersException(String message) {
		super(message);
	}

	public PhoneNumbersException(String message, Throwable cause) {
		super(message, cause);
	}
}
