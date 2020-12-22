package Domain.Tests.Junit;


import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;


public class TestRunnerCell {
	public static void main(String[] args) {
		Result result = JUnitCore.runClasses(CellTest.class);
		for(Failure f: result.getFailures()) {
			System.out.println(f.toString());
			
		}
		System.out.println("Cell Test was successful");
	}
}
