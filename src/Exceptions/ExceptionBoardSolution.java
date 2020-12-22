package Exceptions;

@SuppressWarnings("serial")
public class ExceptionBoardSolution extends Exception {
	
	public String getMessage() {
		return "Provided kakuro does not have a solution or it is not unique.";
	}

}
