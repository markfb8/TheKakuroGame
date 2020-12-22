package Exceptions;

@SuppressWarnings("serial")
public class ExceptionInvalidBoard extends Exception {
	
	public String getMessage() {
		return "Seems like your kakuro is not properly written.";
	}

}