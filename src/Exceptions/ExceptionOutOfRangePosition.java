package Exceptions;

@SuppressWarnings("serial")
public class ExceptionOutOfRangePosition extends Exception{

	public String getMessage() {
		return "Specified position for this board corresponds to a black cell or a non changeable cell or is out of range.";
	}

}
