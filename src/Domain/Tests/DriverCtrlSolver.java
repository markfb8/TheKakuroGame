package Domain.Tests;

import java.util.ArrayList;
import java.util.Scanner;

import Domain.Classes.Board;
import Domain.Controllers.CtrlBoard;
import Domain.Controllers.CtrlSolver;

public class DriverCtrlSolver {
	
	public static CtrlSolver CS;
	public static CtrlBoard CB;
	public static Board b;
	
	public static void solveKakuro() {
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		System.out.println("Write a board:");
		ArrayList<String> board = new ArrayList<String>();
		String str = input.nextLine();
		board.add(str);
		String[] s = str.split(",");
		int rows = Integer.parseInt(s[0]);
		for(int i = 0; i < rows; ++i) {
			board.add(input.nextLine());
		}
		try {
			b = new Board(board);
		} catch (Exception e) {
			e.getMessage();
		}
		CS = new CtrlSolver(b);
		if(!CS.solveKakuro()) System.out.println("Not correct");
		else {
			System.out.println("Correct");
			b.writeSolution();
		}
	}

	public static void main(String[] args) {
		
		CB = new CtrlBoard();
		
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		String str = "";
				
		while(!str.equals("2")) {
			System.out.println();
			System.out.println("Please choose one option:");
			System.out.println();
			System.out.println("	1. solveKakuro()");
			System.out.println("	2. Exit");
			System.out.println();
				
			System.out.print("OPTION NUMBER: ");
			str = input.nextLine();
				
			switch(str) {
			case "1": 
				solveKakuro();
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
