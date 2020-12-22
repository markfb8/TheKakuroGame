package Domain.Tests;

import java.util.Scanner;

import Domain.Classes.Game;
import Domain.Controllers.CtrlRanking;

public class DriverCtrlRanking {
	
	public static CtrlRanking CR;
	
	public static void testAddToRanking() {
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		System.out.println("Please input the board ID, the score and your username");
		String str = input.nextLine();
		String[] s = str.split(" ");
		Game g = new Game();
		g.setScore(Integer.parseInt(s[1]));
		try {
			CR.addToRanking(g ,s[0], s[2]);
			System.out.println("Correct");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public static void main(String[] args) {
		CR = new CtrlRanking();
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		String str = "";
		
		
		while(!str.equals("0")) {
			System.out.println("Please choose an option:");
			System.out.println("	1. testAddToRanking()");
			System.out.println("	0. Exit");
			
			
			System.out.println("OPTION NUMBER: ");
			str = input.nextLine();
			
			
			switch(str) {
			case "1":
				testAddToRanking();
				break;
			case "0":
				System.out.println("Closing...");
				break;
			default:
				System.out.println("Wrong input");
			}
		}
	}
}
