package Domain.Tests;

import java.util.Scanner;

import Domain.Classes.Board;
import Domain.Controllers.CtrlBoard;
import Domain.Controllers.CtrlRuleViolations;

public class DriverCtrlRuleViolations {

	public static CtrlRuleViolations CR;
	public static CtrlBoard CB;
	
	public static void ruleViolations() {
		try {
			CB = new CtrlBoard();
			Board b = CB.getBoard("957","EASY");
			CR = new CtrlRuleViolations(b);
			int i = 0;
			b.writeBoard();
			@SuppressWarnings("resource")
			Scanner input = new Scanner(System.in);
			while(i < 3) {
				String str = input.nextLine();
				String[] s = str.split(" ");
				b.setValue((Integer.parseInt(s[0]))-1,(Integer.parseInt(s[1]))-1,Integer.parseInt(s[2]));
				if(CR.ruleViolations((Integer.parseInt(s[0]))-1,(Integer.parseInt(s[1])-1))) System.out.println("Correct");
				else {
					b.setValue((Integer.parseInt(s[0]))-1,(Integer.parseInt(s[1]))-1,0);
					System.out.println("Not correct");
				}
				b.writeBoard();
				++i;
			}
		} catch (Exception e) {
			System.out.println("Not correct");
		}
	}
	
	public static void main(String[] args) {
		
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		String str = "";
				
		while(!str.equals("2")) {
			System.out.println();
			System.out.println("Please choose one option:");
			System.out.println();
			System.out.println("	1. ruleViolations()");
			System.out.println("	2. Exit");
			System.out.println();
				
			System.out.print("OPTION NUMBER: ");
			str = input.nextLine();
				
			switch(str) {
			case "1": 
				ruleViolations();
				break;
			case "2":
				System.out.println("Closing...");
				break;
			default:
				System.out.println("Wrong input.");
				break;
			}
		}
	}

}
