package com.navvis.german;

public class FileNotFoundException extends PhoneNumbersException {

	public FileNotFoundException(String message) {
		super(message);
	}

	public FileNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
}
