import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import Domain.CtrlDomain;
import Domain.Controllers.CtrlGame;
import Exceptions.ExceptionBoardSolution;
import Exceptions.ExceptionInvalidBoard;
import Exceptions.ExceptionInvalidSize;
import Exceptions.ExceptionOutOfRangePosition;
import Exceptions.ExceptionOutOfRangeValue;

/** @mainpage
 * 
 * 	En el fitxer ConsoleMain.java es troba el programa principal.
 */

/** @file ConsoleMain.java
 * @brief Programa principal
 * <em>Aplicacio per jugar a kakuros amb diverses funcionalitats des de la consola</em>.
 */

public class ConsoleMain {
	
	private static CtrlDomain CD;
	private static CtrlGame CG;
	private static Scanner input = new Scanner(System.in);
	
	public static void main(String[] args) {
		CD = new CtrlDomain();
		CG = CD.createGame("console");
		
		String str = "";
		while(!str.equals("5")) {
			try {
				System.out.println();
				System.out.println("Choose an option:");
				System.out.println();
				System.out.println("	1. Generate kakuro");
				System.out.println("	2. Select kakuro");
				System.out.println("	3. Propose kakuro");
				System.out.println("	4. Load game");
				System.out.println("	5. Exit");
				System.out.println();
				System.out.print("OPTION NUMBER: ");
				str = input.nextLine();
				
				switch(str) {
				case "1": 
					generate();
					break;
				case "2":
					select();
					break;
				case "3":
					propose();
					break;
				case "4":
					load();
					break;
				case "5":
					System.out.println();
					System.out.println("Closing...");
					break;
				default:
					throw new Exception();
				}
			} catch (Exception e) {
				System.out.println();
				System.out.println("Selected option is not valid, please enter a valid option.");
			}
		}
	}
	
	private static void generate() {
		boolean valid = false;
		while(!valid) {
			try {
				System.out.println();
				System.out.print("Please introduce rows, columns (min 5x5, max 20x20) and level (EASY/MEDIUM/HARD) of kakuro: ");
				String str = input.nextLine();
				String[] s = str.split(" ");
				int rows = Integer.parseInt(s[0]);
				int columns = Integer.parseInt(s[1]);
				s[2] = s[2].toUpperCase();
				valid = true;
				System.out.println();
				System.out.println("Generating...");
				System.out.println();
				CG.generateGame(rows,columns,s[2]);
			} catch (ExceptionInvalidSize e) {
				System.out.println();
				System.out.println(e.getMessage());
			} catch (Exception e) {
				System.out.println();
				System.out.println("Incorrect input, please try again.");
			}
			CG.getGame().getBoard().writeBoard();
			play();
		}
	}

	private static void load() {
		ArrayList<String> ids = CG.getGamesList();
		if(ids != null && ids.size()>0) {
			System.out.println();
			System.out.println(ids);
			boolean valid = false;
			while(!valid) {
				try {
					System.out.println();
					System.out.println("Choose a board:");
					String str = input.nextLine();
					if(!ids.contains(str)) throw new Exception();
					CG.loadGame(str);
					valid = true;
					play();
				} catch (Exception e) {
					System.out.println();
					System.out.println("Incorrect input, please try again.");
				}
			}
			System.out.println();
		} else {
			System.out.println();
			System.out.println("No saved games to show.");
			System.out.println();
		}
	}
	
	private static void select() {
		boolean valid = false;
		ArrayList<String> ids = new ArrayList<String>();
		String level = "";
		while(!valid) {
			try {
				System.out.println();
				System.out.println("Write a level (EASY/MEDIUM/HARD):");
				String str = input.nextLine();
				str = str.toUpperCase();
				level = str;
				switch(str) {
				case "EASY":
					ids = CG.getBoardList(str);
					valid = true;
					break;
				case "MEDIUM":
					ids = CG.getBoardList(str);
					valid = true;
					break;
				case "HARD":
					ids = CG.getBoardList(str);
					valid = true;
					break;
				default: throw new Exception();
				}
			} catch (Exception e) {
				System.out.println();
				System.out.println("Incorrect input, please try again.");
			}
		}
		if(ids != null && ids.size()>0) {
			System.out.println();
			System.out.println(ids);
			valid = false;
			while(!valid) {
				try {
					System.out.println();
					System.out.println("Choose a board:");
					String str = input.nextLine();
					if(!ids.contains(str)) throw new Exception();
					CG.selectGame(str,level);
					valid = true;
					play();
				} catch (Exception e) {
					System.out.println();
					System.out.println("Incorrect input, please try again.");
				}
			}
			System.out.println();
		} else {
			System.out.println();
			System.out.println("No boards to show.");
			System.out.println();
		}
	}

	private static void propose() {
		boolean valid = false;
		while(!valid) {
			try {
				ArrayList<String> readen = new ArrayList<String>();
				System.out.println();
				System.out.println("Write your kakuro, first its size (rows,columns) and then the board itself:");
				System.out.println();
				String str = input.nextLine();
				String[] s = str.split(",");
				int m;
				@SuppressWarnings("unused")
				int n;
				try {
					m = Integer.parseInt(s[0]);
					n = Integer.parseInt(s[1]);
				} catch (Exception e) {
					throw new ExceptionInvalidSize();
				}
				readen.add(str);
				for(int i = 0; i < m; ++i) {
					readen.add(input.nextLine());
				}
				System.out.println();
				System.out.println("Processing...");
				String lev = CG.proposeGame(readen);
				valid = true;
				System.out.println("Your kakuro was submitted and classified as: "+lev);
			} catch (ExceptionInvalidSize e) {
				System.out.println();
				System.out.println(e.getMessage());
			} catch (ExceptionInvalidBoard e) {
				System.out.println();
				System.out.println(e.getMessage());
			} catch (ExceptionBoardSolution e) {
				System.out.println();
				System.out.println(e.getMessage());
			} catch (Exception e) {
				System.out.println();
				System.out.println("Something went wrong :(");
			}
		}
	}
	
	private static void play() {
		String str = "";
		int i = -1;
		int j = -1;
		int value = -1;
		System.out.println("--- PLAY MODE ---");
		System.out.println();
		System.out.println("How to play?");
		System.out.println("	- Write the changes you want to perform as: i j value (Where 'i' identifies the row and 'j' the column, starting at 1).");
		System.out.println("	- Only white cells can be modified.");
		System.out.println("	- Values must be from 1 to 9, if you want to erase cell value use 0.");
		System.out.println("	- If any value violates kakuro rules a message will appear and your board will stay the same.");
		System.out.println("	- If you are stuck, feel free to ask for a hint by typing 'hint'.");
		System.out.println("	- You can save your game anytime by writting 'save'.");
		System.out.println("	- You can exit anytime by typing 'exit'.");
		System.out.println();
		long start = System.currentTimeMillis(); //time starts to count
		while(!str.equals("exit")) {
			boolean valid = false;
			while(!valid) {
				try {
					CG.getGame().getBoard().writeBoard();
					System.out.println();
					System.out.print("YOUR MOVE: ");
					str = input.nextLine();
					String[] s = str.split(" ");
					if(str.equals("hint") || str.equals("exit") || str.equals("save")) break;
					i = Integer.parseInt(s[0]);
					j = Integer.parseInt(s[1]);
					value = Integer.parseInt(s[2]);
					valid = true;
				} catch (Exception e) {
					System.out.println();
					System.out.println("Wrong input.");
				}
			}
			if(str.equals("exit")) break;
			if(str.equals("save")) {
				CG.storeGame();
				System.out.println();
				System.out.println("Game saved!");
				System.out.println();
			}
			else if(str.equals("hint")) CG.getHint();
			else {
				try {
					if(!CG.setCellValue(i-1, j-1, value)) {
						System.out.println();
						System.out.println("Value violates the rules!");
						System.out.println();
					}
				} catch (ExceptionOutOfRangeValue e) {
					System.out.println(e.getMessage());
				} catch (ExceptionOutOfRangePosition e) {
					System.out.println(e.getMessage());
				}
			}
			
			if(CG.isFinished()) {
				System.out.println("Kakuro finished! Score: "+CG.getScore());
				System.out.println();
				long time = System.currentTimeMillis() - start;
				String hms = String.format("%02d hours, %02d minutes, %02d seconds", TimeUnit.MILLISECONDS.toHours(time),
					    TimeUnit.MILLISECONDS.toMinutes(time) % TimeUnit.HOURS.toMinutes(1),
					    TimeUnit.MILLISECONDS.toSeconds(time) % TimeUnit.MINUTES.toSeconds(1));
				System.out.println("You have been playing for "+hms);
				str = "exit";
				break;
			}
		}
	}
}