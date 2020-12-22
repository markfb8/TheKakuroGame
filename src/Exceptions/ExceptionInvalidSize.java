package Exceptions;

@SuppressWarnings("serial")
public class ExceptionInvalidSize extends Exception {
	
	public String getMessage() {
		return "Size must be between 5x5 and 20x20";
	}

}
