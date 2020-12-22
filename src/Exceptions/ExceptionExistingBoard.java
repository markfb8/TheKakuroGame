package Exceptions;

@SuppressWarnings("serial")
public class ExceptionExistingBoard extends Exception {
	
	public String getMessage() {
		return "This board already is on the repository.";
	}
}
