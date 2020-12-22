/** @file BlackCell.java */

package Domain.Classes.Cell;

/** @class BlackCell
 * @brief Aquesta classe representa les cel·les negres del tauler. 
 * Aquestes cel·les tenen dos atributs, la suma vertical i la suma horitzontal, que indiquen el que han de sumar les cel·les blanques que es troben a sota i a la dreta,respectivament
 */

public class BlackCell extends Cell {
	
//attributes
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8382253116182364464L;
	/** @brief Atribut que indica la suma horitzontal de la cel·la negra */
	private int numH;
	/** @brief Atribut que indica la suma vertical de la cel·la negra */
	private int numV;
	
//constructors
	
	/** @brief Constructora per defecte de la classe 
	 * @pre cert
	 * @post Es genera una cel·la negra amb sumes verticals i horitzontals amb valor 0
	 */
	
	public BlackCell() {
		numH = 0;
		numV = 0;
	}
	
	/** @brief Constructora amb parametres 
	 * @pre Donat un int nV >= 0 i un int nH >= 0
	 * @post Es genera una cel·la negra amb numH = nH i numV =nV
	 * @param nV = Suma vertical de la cel·la
	 * @param nH = Suma horitzontal de la cel·la
	 */
	
	public BlackCell(int nV, int nH) {
		numH = nH;
		numV = nV;
	}
	
//setters
	
	/** @brief Modificadora de la suma horitzontal
	 * @pre Donat un int nH >= 0
	 * @post Es modifica l'atribut numH de la cel·la amb el nou valor nH
	 * @param nH = Nova suma horitzontal de la cel·la
	 */
	
	public void setHNum(int nH) { numH = nH; }
	
	/** @brief Modificadora de la suma vertical
	 * @pre Donat un int nV >= 0
	 * @post Es modifica l'atribut numV de la cel·la amb el nou valor nV
	 * @param nV = Nova suma vertical de la cel·la
	 */
	
	public void setVNum(int nV) { numV = nV; }
	
//getters
	
	/** @brief Consultora de la suma horitzontal
	 * @pre cert
	 * @post Es retorna el valor de la suma horitzontal de la cel·la
	 */
	
	public int getHNum() { return numH; }
	
	/** @brief Consultora de la suma vertical 
	 * @pre cert
	 * @post Es retorna el valor de la suma vertical de la cel·la
	 */
	
	public int getVNum() { return numV; }
	
}
