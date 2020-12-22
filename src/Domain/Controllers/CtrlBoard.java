/** @file CtrlBoard.java */

package Domain.Controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import Domain.Classes.Board;
import Exceptions.ExceptionBoardSolution;
import Exceptions.ExceptionExistingBoard;
import Exceptions.ExceptionInvalidBoard;
import Exceptions.ExceptionInvalidSize;
import Persistence.CtrlPersistenceBoard;

/** @class CtrlBoard
 *  @brief Aquesta classe representa el controlador de la classe Board i s'encarrega de gestionar els taulers del repositori i validar els taulers.
 * 
 *
 */

public class CtrlBoard {
	
//attributes
	
	/** @brief Instancia de la classe CtrlPersistenceBoard */
	private CtrlPersistenceBoard CP;
	/** @brief Instancia del controlador CtrlGenerator*/
	private CtrlGenerator CG;
	/** @brief Instancia de la classe CtrlSolver */
	private CtrlSolver CS;
	/** @brief Instancia de la classe Board */
	private Board b;
	/** @brief Identificador del tauler associat */
	private String id;

//constructors
	
	/**@brief Constructora per defecte
	 * @pre cert
	 * @post Es genera una instancia de CtrlBoard amb una instancia de CtrlPersistenceBoard associada. Les instancies de CtrlSolver, CtrlGenerator i Board es defineixen inicialment nules 
	 * 	
	 */
	
	public CtrlBoard() {
		CP = new CtrlPersistenceBoard();
		CS = null;
		CG = null;
		b = null;
	}

//getters
	
	/** @brief Metode que retorna la id assignada a un tauler
	 * @pre Cert
	 * @post Es retorna la id del tauler
	 */
	public String getId() { return id; }
	
	/** @brief Metode que retorna la llista de taulers guardats al repositori
	 * @pre Cert
	 * @post Es retorna la llista de taulers guardats al repositori
	 */
	
	public ArrayList<String> getList(String level) {
		return CP.getList(level);
	}
	
	/** @brief Metode que retorna un tauler dels que es troben guardats al repositori
	 * @pre Donat un int id que identifica un dels taulers de repositori
	 * @post Es retorna una instancia de la classe Board que representa el tauler amb identificador = id
	 * @param id = Identificador del tauler de repositori
	 * @throws Exception
	 */
	
	public Board getBoard(String id,String level) throws Exception {
		this.id = id;
		b = CP.getBoard(id,level);
		return b;
	}
	
	/**@brief Metode que canvia l atribut privat b 
	 * @pre Donat un tauler valid brd
	 * @post L atribut b de la classe apunta a brd
	 * @param brd
	 */
	
	public void setBoard(String id,Board brd) {
		this.id = id;
		b = brd;
	}
	
//methods
	
	/** @throws ExceptionInvalidSize 
	 * @brief Metode per generar un tauler
	 * @pre El nombre de columnes i files es major que 4
	 * @post Es genera un tauler amb tamany rows x cols i del nivell indicat
	 */
	
	public Board generateKakuro(int rows, int cols, String level) throws ExceptionBoardSolution, ExceptionInvalidSize {
		CG = new CtrlGenerator();
		b = CG.generateBoard(rows,cols,level);
		assignId();
		boolean valid = false;
		while(!valid) {
			try {
				CP.storeBoard(b,id);
				valid = true;
			} catch (ExceptionExistingBoard e) {
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return b;
	}
	
	/** @brief Metode que valida un tauler introduit per terminal
	 * @pre Donat un ArrayList de Strings que representa la informacio necessaria per crear un tauler
	 * @post Si el tauler proposat te solucio unica es retorna una instancia de la classe Board que representa el tauler proposat per l ArrayList de Strings
	 * @param input = ArrayList que conte la informacio necessaria per generar un tauler
	 * @throws ExceptionBoardSolution 
	 * @throws ExceptionInvalidSize 
	 * @throws ExceptionInvalidBoard 
	 * @throws ExceptionExistingBoard 
	 * @throws Exception
	 */
	
	public Board validateBoard(ArrayList<String> input) throws ExceptionBoardSolution, ExceptionInvalidBoard, ExceptionInvalidSize, ExceptionExistingBoard {
		b = new Board(input);
		CS = new CtrlSolver(b);
		if(!CS.solveKakuro()) {
			throw new ExceptionBoardSolution();
		}
		b.determineDifficulty();
		assignId();
		try {
			CP.storeBoard(b,id);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return b;
	}
	
	/** @brief Metode per assignar una id al tauler
	 * @pre 
	 * @post Al tauler se li assigna una id enica
	 */
	
	private void assignId() {
		boolean valid = false;
		ArrayList<String> ids = CP.getList();
		if(ids == null) {
			id = Integer.toString(ThreadLocalRandom.current().nextInt(1, 10000));
		} else {
			while(!valid) {
				int idaux = ThreadLocalRandom.current().nextInt(1, 10000);
				if(!ids.contains(Integer.toString(idaux))) {
					id = (Integer.toString(idaux));
					valid = true;
				}
			}
		}
	}

	/** @brief Metode per exportar un tauler a un arxiu de text en el format estandard
	 * @pre cert
	 * @post S ha exportat el tauler
	 */
	
	public void exportBoard() {
		try {
			CP.exportBoard(b,id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
