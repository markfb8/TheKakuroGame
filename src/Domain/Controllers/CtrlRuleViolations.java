/** @file CtrlRuleViolations.java */

package Domain.Controllers;

import Domain.Classes.Board;
import Domain.Classes.Cell.Cell;

/** @class CtrlRuleViolations
 * 	@brief Controlador que s'encarregara de controlar les restriccions dels taulers
 * 
 *
 */

public class CtrlRuleViolations {
	
//attributes
	
	/** @brief Instancia de la classe Board associada al controlador*/
	private Board b;
	/** @brief Matriu de caselles del Board b*/
	private Cell[][] c;
	
//constructors
	
	/** @brief Constructora per defecte
	 * @pre Donada una instancia de la classe Board.
	 * @post Es crea una instancia de la classe CtrlRuleViolations,
	 * @param board = Instancia de la classe Board
	 */
	
	public CtrlRuleViolations(Board board) {
		b = board;
		c = b.getCells();
	}

//methods
	
	/** @brief Metode que comprova si una casella incompleix les regles del joc
	 * @pre Donada una posicio i,j(0 <= i < rows, 0 <= j < columns) del tauler
	 * @post Es retorna false si el valor a la posicio i,j incompleix les restriccions, altrament es retorna true
	 * @param i = Posicio vertical de la cassella	
	 * @param j = Posicio horitzontal de la cassella
	 * @return true si la casella a (i,j) no incompleix cap restriccio, false altrament
	 */
	
	public boolean ruleViolations(int i, int j) {
		int sumV = c[i][j].getValue();
		int resV;
		boolean comV = true; //vertical sequence completed?
		int sumH = sumV;
		int resH;
		boolean comH = true; //horizontal sequence completed?
		int val = sumV;
		
		int rows = b.getRows();
		int columns = b.getColumns();
		
		//first check horizontal sequence
		int val2;
		int i1 = i;
		int j1 = j+1;
		while(j1 < columns && c[i1][j1].isWhite()) { //horizontal right
			val2 = c[i1][j1].getValue();
			if(val2 == val) { return false; } //placed value already appears in sequence
			if(val2 == 0) comH = false; //sequence not completed
			sumH += val2;
			++j1;
		}
		j1 = j-1;
		while(c[i1][j1].isWhite()) { //horizontal left
			val2 = c[i1][j1].getValue();
			if(val2 == val) { return false; } //placed value already appears in sequence
			if(val2 == 0) comH = false; //sequence not completed
			sumH += val2;
			--j1;
		}
		resH = c[i1][j1].getHNum();
		if(sumH > resH) { return false; } //sum is greater than expected
		
		//now check vertical sequence
		i1 = i+1;
		j1 = j;
		while( i1 < rows && c[i1][j1].isWhite()) { //vertical down
			val2 = c[i1][j1].getValue();
			if(val2 == val) { return false; } //placed value already appears in sequence
			if(val2 == 0) comV = false; //sequence not completed
			sumV += val2;
			++i1;
		}
		i1 = i-1;
		while(c[i1][j1].isWhite()) { //vertical up
			val2 = c[i1][j1].getValue();
			if(val2 == val) { return false; } //placed value already appears in sequence
			if(val2 == 0) comV = false; //sequence not completed
			sumV += val2;
			--i1;
		}
		resV = c[i1][j1].getVNum();
		if(sumV > resV) { return false; } //sum is greater than expected
		
		if(comH && (sumH != resH)) { return false; } //if horizontal sequence completed and sum is not correct (inferior)
		if(comV && (sumV != resV)) { return false; } //if vertical sequence completed and sum is not correct (inferior)
		
		return true;
	}

}
