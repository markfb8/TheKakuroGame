/** @file Cell.java */

package Domain.Classes.Cell;

import java.io.Serializable;

/** @class Cell
 * @brief En aquesta classe representem una de les celles del tauler, la cual pot ser blanca o negra (subclasses) i assignals'hi un valor temporal o permantent, aixi com assginar-hi un valor solució.
 * Aquesta classe esta practicament buida i les operacions les exlpiquem a WhiteCell i a BlackCell segons correspongui.
 */

public class Cell implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7821295353115586165L;

	/** @brief Creadora per defecte
	 *  @pre Cert
	 *  @post crea una Cell buida
	 */
	
//constructors
	
	public Cell() {}
	
//getters
	
	public int getValue() { return -1; }
	public int getSol() { return -1; }
	public boolean isPerm() { return true; }
	public int getHNum() { return -1; }
	public int getVNum() { return -1; }

//setters
	
	public void setValue(int v) {}
	public void setSol(int s) {}
	public void setPerm(boolean p) {}
	public void setHNum(int nH) {}
	public void setVNum(int nV) {}
	
//methods
	
	/** @brief Ens diu si la cassella és blanca
	 * @pre Cert
	 * @post Retorna true si la cassella és blanca, si no retorna false
	 */
	
	public boolean isWhite() {
		return (WhiteCell.class.isInstance(this));
	}
	
	/** @brief Ens diu si la cassella és negra
	 * @pre Cert
	 * @post Retorna true si la cassella és negra, si no retorna false
	 */
	
	public boolean isBlack() {
		return (BlackCell.class.isInstance(this));
	}
	
}