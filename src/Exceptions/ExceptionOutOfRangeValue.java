package Exceptions;

@SuppressWarnings("serial")
public class ExceptionOutOfRangeValue extends Exception {
	
	public String getMessage() {
		return "The value you are trying to place is out of range, remember it must be a number between 0 and 9.";
	}

}