package Domain.Tests.Junit;


import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;


public class TestRunnerBoard {
	public static void main(String[] args) {
		Result result = JUnitCore.runClasses(BoardTest.class);
		for(Failure f: result.getFailures()) {
			System.out.println(f.toString());
			
		}
		System.out.println("Board Test was successful");
	}
}
