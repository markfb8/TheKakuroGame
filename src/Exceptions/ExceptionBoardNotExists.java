package Exceptions;

@SuppressWarnings("serial")
public class ExceptionBoardNotExists extends Exception {
	
	public String getMessage() {
		return "Selected board does not exist.";
	}

}
