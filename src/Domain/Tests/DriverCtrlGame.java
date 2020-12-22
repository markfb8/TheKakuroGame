package Domain.Tests;

import java.util.ArrayList;
import java.util.Scanner;

import Domain.Controllers.CtrlGame;

public class DriverCtrlGame {
	
	public static CtrlGame CG;
	
	public static void testSelectGame() {
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		System.out.println(CG.getGamesList());
		System.out.println("Choose a game:");
		CG.loadGame(input.nextLine());
		System.out.println("Correct");
	}
	
	public static void testProposeGame() {
		ArrayList<String> readen = new ArrayList<String>();
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		String str = input.nextLine();
		String[] s = str.split(",");
		int m = Integer.parseInt(s[0]);
		readen.add(str);
		for(int i = 0; i < m; ++i) {
			readen.add(input.nextLine());
		}
		try {
			CG.proposeGame(readen);
			System.out.println("Correct");
		} catch (Exception e) {
			System.out.println("Not correct");
		}
	}
	
	public static void testSetCellValue() {
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		System.out.println(CG.getGamesList());
		System.out.println("Choose a game:");
		CG.loadGame(input.nextLine());
		System.out.println("Change any cell (i,j,value):");
		String str = input.nextLine();
		String[] s = str.split(" ");
		try {
			if(CG.setCellValue(Integer.parseInt(s[0]), Integer.parseInt(s[1]), Integer.parseInt(s[2]))) System.out.println("value placed");
			else System.out.println("value not placed");
		} catch (Exception e) {
			System.out.println("value not placed");
		}
	}
	
	public static void main (String[] args) {
		
		CG = new CtrlGame("gorka");
		
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		String str = "";
				
		while(!str.equals("4")) {
			System.out.println();
			System.out.println("Please choose one option:");
			System.out.println();
			System.out.println("	1. testSelectGame");
			System.out.println("	2. testProposeGame");
			System.out.println("	3. testSetCellValue");
			System.out.println("	4. Exit");
			System.out.println();
				
			System.out.print("OPTION NUMBER: ");
			str = input.nextLine();
				
			switch(str) {
			case "1":
				testSelectGame();
				break;
			case "2":
				testProposeGame();
				break;
			case "3":
				testSetCellValue();
				break;
			case "4":
				System.out.println("Closing...");
				break;
			default:
				System.out.println("Wrong input.");
				break;
			}
		}
	}
}