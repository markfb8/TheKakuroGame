/** @file WhiteCell.java */
package Domain.Classes.Cell;

/** @class WhiteCell
 * @brief En aquesta classe representem una casella blanca del tauler. A aquesta li podem assignar un valor, una valor solucio que contindra el numero que ha de tenir el valor anterior perque sigui correcte i la podem marcar com a permanent perque l'usuari no la pugui modificar
 */

public class WhiteCell extends Cell {
	
//attributes

	/**
	 * 
	 */
	private static final long serialVersionUID = -5112999857507889336L;
	/** @brief Representa el valor que introdueix l'usuari a la casella o el que esta possat per defecte (si la casella es permanent) */
	private int value;
	/** @brief Representa el valor correcte que te la casella a la solucio del tauler */
	private int sol;
	/** @brief Ens diu si la casella es permanent (no es pot modificar) o no */
	private boolean perm;
	
//constructors
	
	/** @brief Creador per nivell de dificultat
	 * @pre Cert
	 * @post Crea una casella no permanent amb valor 0 i sense solucio
	 */
	
	public WhiteCell() {
		value = 0;
		sol = -1;
		perm = false;
	}
	
	/** @brief
	 * @pre 0 <= x <= 9
	 * @post genera una casella blanca amb valor x, amb perm = p i si es permanent amb solucio = x, sino solucio = 0
	 * @param x = valor que assignem a la casella
	 * @param p = permanencia de la casella
	 */
	
	public WhiteCell(int x, boolean p) {
		value = x;
		perm = p;
		sol = 0;
		if(p) sol = x;
	}

//getters
	
	/** @brief
	 * @pre Cert
	 * @post retorna el valor actual de la casella
	 */
	
	public int getValue() { return value; }
	
	/** @brief
	 * @pre Cert
	 * @post retorna el valor solucio de la casella
	 */
	
	public int getSol() { return sol; }
	
	/** @brief
	 * @pre Cert
	 * @post retorna true si la casella es permanent, sino retorna fals
	 */
	
	public boolean isPerm() { return perm; }
	
//setters
	
	/** @brief
	 * @pre 0 <= v <= 9
	 * @post Assigna v a la casella blanca
	 */
	
	public void setValue(int v){ value = v; }
	
	/** @brief
	 * @pre 0 <= s <= 9 i s es el valor correcta de la casella
	 * @post Assigna s com a solucio de la casella
	 */
	
	public void setSol(int s) { sol = s; }
	
	/** @brief
	 * @pre Cert
	 * @post Assigna p a perm
	 */
	
	public void setPerm(boolean p) { perm = p; }
	
}