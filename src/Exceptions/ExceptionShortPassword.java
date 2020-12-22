package Exceptions;

@SuppressWarnings("serial")
public class ExceptionShortPassword extends Exception {
	
	public String getMessage() {
		return "Password must have at least length 8.";
	}

}