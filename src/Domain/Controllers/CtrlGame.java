/** @file CtrlGame.java */

package Domain.Controllers;

import java.io.IOException;
import java.util.ArrayList;

import Domain.Classes.Game;
import Domain.Classes.Cell.Cell;
import Exceptions.ExceptionBoardNotExists;
import Exceptions.ExceptionBoardSolution;
import Exceptions.ExceptionExistingBoard;
import Exceptions.ExceptionInvalidBoard;
import Exceptions.ExceptionInvalidSize;
import Exceptions.ExceptionOutOfRangePosition;
import Exceptions.ExceptionOutOfRangeValue;
import Persistence.CtrlPersistenceGame;

/** @class CtrlGame
 * @brief Representa el controlador de la classe game i les operacions associades a aquest.
 * 
 *
 */

public class CtrlGame {
	
//attributes
	
	/** @brief Instancia del controlador CtrlPersistenceGame*/
	private CtrlPersistenceGame CP;
	/** @brief Instancia del controlador de la classe Board */
	private CtrlBoard CB;
	/** @brief Instancia del controlador CtrlRuleViolations*/
	private CtrlRuleViolations CR;
	/** @brief Instancia del controlador CtrlRanking*/
	private CtrlRanking CRK;
	/** @brief Nom de l'usuari associat a la partida*/
	private String username;
	/** @brief Instancia de la classe game associada al controlador*/
	private Game g;
	
	private long iniTime;
	private long finalTime;

//constructors
	
	/** @brief Constructora per defecte
	 * @pre cert
	 * @post Es genera una instancia de la classe CtrlGame amb nom d usuari username i amb una instancia dels controladors CtrlPersistenceGame i CtrlBoard associats.
	 * 	Les instancies de CtrlRuleViolations i Game es defineixen nules.
	 */
	
	public CtrlGame(String username) {
		this.username = username;
		CP = new CtrlPersistenceGame();
		CB = new CtrlBoard();
		CR = null;
		g = null;
	}
	
//getters
	
	/** @brief Metode que retorna el tauler associat al Game g
	 * @pre Hi ha algun Game valid associat
	 * @post Es retorna el tauler associat al Game g
	 */
	
	public Cell[][] getCells() {
		return g.getBoard().getCells();
	}
	
	/** @brief Metode que retorna informacio sobre les caselles del tauler (usat per la visualitzacio)
	 * @pre Hi ha algun Game valid associat
	 * @post Es retorna informacio sobre les caselles del tauler
	 */
	
	public ArrayList<ArrayList<String[]>> getCellsInfo() {
		Cell[][] cells = g.getBoard().getCells();
		ArrayList<ArrayList<String[]>> info = new ArrayList<ArrayList<String[]>>();
		for(int i = 0; i < cells.length; ++i) {
			ArrayList<String[]> row = new ArrayList<String[]>();
			for(int j = 0; j < cells[0].length; ++j) {
				String[] cell = new String[3];
				if(cells[i][j].isWhite()) {
					cell[0] = "W";
					cell[1] = String.valueOf(cells[i][j].getValue());
					if(cells[i][j].isPerm()) cell[2] = "P";
					else cell[2] = "NP";
				} else {
					cell[0] = "B";
					cell[1] = String.valueOf(cells[i][j].getHNum());
					cell[2] = String.valueOf(cells[i][j].getVNum());
				}
				row.add(cell);
			}
			info.add(row);
		}
		return info;
	}
	
	/** @brief Metode que retorna el nombre de pistes demanades al Game g
	 * @pre Hi ha algun Game valid associat
	 * @post Es retorna el nombre de pistes demanades
	 */
	
	public int getHints() {
		return g.getHints();
	}
	
	/** @brief Metode que retorna la puntuacio del Game g
	 * @pre Hi ha algun Game valid associat
	 * @post Es retorna la puntuacio
	 */
	
	public int getScore() {
		return g.getScore();
	}
	
	public Game getGame() {
		return g;
	}
	
//methods
	
	/** @brief Metode per consultar la llista jocs guardats
	 * @pre cert
	 * @post Es retorna la llista de jocs guardats de l'usuari identificat per username
	 * @param username = String que identifica a l'usuari
	 */
	
	public ArrayList<String> getGamesList() {
		return CP.getSaved(username);
	}
	
	/** @brief Metode per consultar la llista jocs completats
	 * @pre cert
	 * @post Es retorna la llista de jocs completats de l'usuari identificat per username
	 * @param username = String que identifica a l'usuari
	 */
	
	public ArrayList<String> getCompletedList() {
		return CP.getCompleted(username);
	}
	
	/**@brief Metode per seleccionar un kakuro dels finalitzats per l usuari (UNICAMENT per mostrar el ranking, NO per jugar)
	 * @pre cert
	 * @post Es genera una instancia de la classe game amb el tauler indicat associat
	 */
	
	public void selectCompleted(String selected) {
		g = new Game();
		try {
			g = CP.getCompletedGame(username, selected);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/** @brief Metode per consultar la llista de taulers disponibles
	 * @pre cert
	 * @post Es retorna la llista de taulers seleccionables per l'usuari identificat per username
	 * @param username = String que identifica a l'usuari
	 */
	
	public ArrayList<String> getBoardList(String level) {
		ArrayList<String> list = CB.getList(level);
		ArrayList<String> listSaved = getGamesList();
		ArrayList<String> listCompleted = CP.getCompleted(username);
		if(listSaved != null) {
			for(String savedGame : listSaved) {
				if(list.contains(savedGame)) {
					list.remove(savedGame);
				}
			}
		}
		if(listCompleted != null) {
			for(String compGame : listCompleted) {
				if(list.contains(compGame)) {
					list.remove(compGame);
				}
			}
		}
		return list;
	}
	
	/** @throws ExceptionInvalidSize 
	 * @brief Metode per generar partida
	 * @pre El nombre de columnes i files es major que 4
	 * @post Es genera una partida 
	 */
	
	public void generateGame(int rows, int cols, String level) throws ExceptionInvalidSize {
		g = new Game();
		try {
			g.setBoard(CB.generateKakuro(rows, cols, level));
			CR = new CtrlRuleViolations(g.getBoard());
		} catch (ExceptionBoardSolution e) {
			e.getMessage();
		}
		iniTime = System.currentTimeMillis();
	}
	
	/**@brief Metode per seleccionar un kakuro del repositori
	 * @pre cert
	 * @post Es genera una instancia de la classe game amb el tauler indicat associat
	 */
	
	public void selectGame(String selected,String level) {
		g = new Game();
		try {
			g.setBoard(CB.getBoard(selected,level));
			CR = new CtrlRuleViolations(g.getBoard());
		} catch (ExceptionBoardNotExists e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
		iniTime = System.currentTimeMillis();
	}
	
	/** @brief Metode per jugar a una partida proposada per terminal.
	 * @pre Donat una ArrayList de strings que conte la informacio necesaria per generar un tauler.
	 * @post Si la informacio era valida, es genera una nova partida amb el tauler en questio i es retorna el nivell que se li ha assignat
	 * @param input
	 * @throws ExceptionInvalidSize 
	 * @throws ExceptionInvalidBoard 
	 * @throws ExceptionBoardSolution 
	 * @throws ExceptionExistingBoard 
	 * @throws Exception
	 * @return String equivalent al nivell
	 */
	
	public String proposeGame(ArrayList<String> input) throws ExceptionBoardSolution, ExceptionInvalidBoard, ExceptionInvalidSize, ExceptionExistingBoard {
		g = new Game();
		g.setBoard(CB.validateBoard(input));
		CR = new CtrlRuleViolations(g.getBoard());
		return g.getBoard().getLevel();
	}
	
	/** @brief Metode per carregar una partida guardada
	 * @pre cert
	 * @post Es carrega la partida guardada
	 */
	
	public void loadGame(String id) {
		try {
			g = CP.getGame(username,id);
			CB = new CtrlBoard();
			CB.setBoard(id,g.getBoard());
			CR = new CtrlRuleViolations(g.getBoard());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		iniTime = System.currentTimeMillis();
	}
	
	/** @brief Metode per exportar un tauler a un arxiu de text en el format estandard
	 * @pre cert
	 * @post S ha exportat el tauler
	 */
	
	public void storeGame() {
		finalTime = System.currentTimeMillis();
		g.setTime(finalTime-iniTime);
		try {
			CP.storeGame(username, g, CB.getId());
		} catch(Exception e) {
			e.getMessage();
		}
	}
	
	/** @brief Metode per exportar un tauler a un arxiu de text en el format estandard
	 * @pre cert
	 * @post S ha exportat el tauler
	 */
	
	public void exportBoard() {
		CB.exportBoard();
	}
	
	/** @brief Metode per demanar una pista
	 * @pre cert
	 * @post Es mostra el valor correcte d'una casella aleatoria 
	 */
	
	public int[] getHint() {
		int[] pos = g.getBoard().getHint();
		g.setHints(g.getHints()+1);
		//storeGame();
		return pos;
	}
	
	/** @brief Metode per consultar si el joc s'ha finalitzat
	 * @pre El CtrlGame te algun Game associat
	 * @post cert
	 * @return true si el Board associat al Game associat al CtrlGame te totes les caselles blanques plenes, altrament false
	 */
	
	public boolean isFinished() {
		if(g.getBoard().getEmptyCells() == 0) {
			finalTime = System.currentTimeMillis();
			g.setTime(finalTime-iniTime);
			setScore();
			try {
				CRK = new CtrlRanking();
				CRK.addToRanking(g,CB.getId(),username);
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			try {
				CP.storeCompletedGame(username, g, CB.getId());
			} catch (Exception e) {
				e.getMessage();
			}
			return true;
		}
		return false;
	}
	
	/** @brief Metode que assigna la puntuacio en funcio del temps, les pistes i la dificultat
	 * @pre El CtrlGame te algun Game finalitzat associat
	 * @post S'ha assignat la puntuacio corresponent a la partida
	 */

	public void setScore() {
		String level = g.getBoard().getLevel();
		double basePoints = 0;
		if (level.equals("EASY")) basePoints = 1000;
		else if(level.equals("MEDIUM"))  basePoints = 2000;
		else  basePoints = 3000;
		
		basePoints *= ((g.getBoard().getRows() + g.getBoard().getColumns())/10.0);
		double pistesTotals =  g.getHints() + g.getBoard().getIniCells();
		double blanques = g.getBoard().getWhiteCells();
		double percentatgePistes = pistesTotals/blanques;
		
		long temps = (g.getTime() / 1000);//temps en segons
		double score =(  ((1-percentatgePistes) * basePoints) - 2 * temps  );
		
		g.setScore(Math.max((int)score,0));
	}
	
	/** @brief Coloca un valor a la matriu de caselles
	 * @pre Donats uns valors i,j que indiquen la posicio al tauler(0 <= i < rows, 0 <= j < columns), i un valor x que indica el valor a posar
	 * @post Si la posicio es correcta, i no s incompleixen les restriccions, s introdueix el nou valor a la posicio indicada
	 * @param i = Posicio vertical de la cassella
	 * @param j = Posicio horitzontal de la cassella																								
	 * @param x = Valor de la cassella
	 * @throws ExceptionOutOfRangePosition 
	 * @throws ExceptionOutOfRangeValue 
	 * @return true si el valor s ha pogut posar perque no violava cap restriccio, altrament false
	 */
	
	public boolean setCellValue(int i, int j, int x) throws ExceptionOutOfRangeValue, ExceptionOutOfRangePosition {
		if(i < 0 || i >= g.getBoard().getRows() || j < 0 || j >= g.getBoard().getColumns()) throw new ExceptionOutOfRangePosition();
		int act = g.getBoard().getValue(i,j);
		g.getBoard().setValue(i, j, x);
		if(x != 0 && !(CR.ruleViolations(i,j))) {
			g.getBoard().setValue(i,j,act);
			return false;
		}
		return true;
	}

}
