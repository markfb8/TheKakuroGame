package Domain.Tests;

import java.util.ArrayList;
import java.util.Scanner;

import Domain.Controllers.CtrlBoard;

public class DriverCtrlBoard {
	public static CtrlBoard CB;
	
	public static void testGetList() {
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		System.out.println("Write a level (EASY,MEDIUM,HARD):");
		System.out.println(CB.getList(input.nextLine()));
	}
	
	public static void testGetBoard() {
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		System.out.println("Write a level (EASY,MEDIUM,HARD):");
		String level = input.nextLine();
		System.out.println(CB.getList(level));
		try {
			CB.getBoard(input.nextLine(),level);
			System.out.println("Correct");
		} catch (Exception e) {
			System.out.println("Not correct");
		}
	}
	
	public static void testValidateBoard() {
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
			CB.validateBoard(readen);
			System.out.println("Correct");
		} catch (Exception e) {
			System.out.println("Not correct");
		}
	}

	public static void main(String[] args) {
		
		CB = new CtrlBoard();
		
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		String str = "";
				
		while(!str.equals("4")) {
			System.out.println();
			System.out.println("Please choose one option:");
			System.out.println();
			System.out.println("	1. testGetList()");
			System.out.println("	2. testGetBoard()");
			System.out.println("	3. testValidateBoard()");
			System.out.println("	4. Exit");
			System.out.println();
				
			System.out.print("OPTION NUMBER: ");
			str = input.nextLine();
				
			switch(str) {
			case "1": 
				testGetList();
				break;
			case "2":
				testGetBoard();
				break;
			case "3":
				testValidateBoard();
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