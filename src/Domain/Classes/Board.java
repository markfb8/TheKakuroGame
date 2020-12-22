/** @file Board.java */

package Domain.Classes;

import java.io.Serializable;
import java.util.ArrayList;

import Domain.Classes.Cell.BlackCell;
import Domain.Classes.Cell.Cell;
import Domain.Classes.Cell.WhiteCell;
import Exceptions.ExceptionInvalidBoard;
import Exceptions.ExceptionInvalidSize;
import Exceptions.ExceptionOutOfRangePosition;
import Exceptions.ExceptionOutOfRangeValue;

/** @class Board
 * @brief Aquesta classe representa els taulers. Aquests taulers tenen diversos atributs, essent el més important una matriu d'objectes de la classe Cell.
 * A part d'aquest, els taulers guarden la informacio referent a la matriu d'objectes de la classe Cell, com per exemple el nombre de caselles negres i blanques o el nombre de files i columnes, en altres atributs.
 *
 */


public class Board implements Serializable {
	
//attributes

	/**
	 * 
	 */
	private static final long serialVersionUID = 2186501815924474399L;
	/** @brief Representa el nombre de files del tauler */
	private int rows;
	/** @brief Representa el nombre de columnes del tauler */
	private int columns;
	/** @brief Representa el nombre de caselles inicials del tauler que tenen un valor correcte*/
	private int iniCells; //initial cells showing its correct value
	/** @brief Representa el nombre de caselles inicials buides */
	private int emptyCells; //initial empty cells (not showing correct value)
	/** @brief Representa el nombre de caselles negres del tauler */
	private int blackCells;
	/** @brief Representa el nombre de caselles blanques del tauler */
	private int whiteCells;
	/** @brief Representa el nivell de dificultat del tauler */
	private String level;
	/** @brief Matriu del objecte caselles, objecte que representa la informacio d'una cassella del tauler*/
	private Cell[][] b;
	
//constructors
	
	/** @brief Creador per input
	 * @pre Donada una ArrayList de strings.
	 * @post El resultat es un board amb els seus atributs inicialitzats
	 * @param input = ArrayList que conté la informació per generar un board
	 * @throws ExceptionInvalidBoard
	 * @throws ExceptionInvalidSize
	 */
	
	public Board(ArrayList<String> input) throws ExceptionInvalidBoard, ExceptionInvalidSize {
		try {
			readBoard(input);
		} catch (ExceptionInvalidSize e) {
			throw new ExceptionInvalidSize();
		} catch (Exception e) {
			throw new ExceptionInvalidBoard();
		}
	}
	

	/** @brief Creador donades les files i columnes
	 * @pre Donats dos enters positius mes grans que 2
	 * @post El resultat es un board amb les files i columnes inicialitzades
	 * @param r = nombre de files del tauler
	 * @param c = nombre de columnes del tauler
	 * @throws ExceptionInvalidSize 
	 */
	
	public Board(int r,int c) throws ExceptionInvalidSize {
		if(r < 5 || c < 5) throw new ExceptionInvalidSize();
		if(r > 20 || c > 20) throw new ExceptionInvalidSize();
		rows = r;
		columns = c;
		b = new Cell[r][c];
	}

	
//getters
	
	/** @brief Consulta les files del tauler 
	 * 	@pre Cert
	 * 	@post  Indica les files del tauler
	 * 
	 */
	
	public int getRows() { return rows; }
	
	/** @brief Consulta les columnes del tauler 
	 * 	@pre Cert
	 * 	@post  Indica les columnes del tauler
	 * 
	 */
	
	public int getColumns() { return columns; }
	
	/** @brief Consulta les caselles inicials que tenen un valor correcte
	 * 	@pre Cert
	 * 	@post  Indica les caselles inicials que tenen un valor correcte
	 * 
	 */
	
	public int getIniCells() { return iniCells; }
	
	/** @brief Consulta les caselles inicials que es troben buides
	 * 	@pre Cert
	 * 	@post  Indica les caselles inicials que es troben buides
	 * 
	 */
	
	public int getEmptyCells() { return emptyCells; }
	
	/** @brief Consulta el nombre de caselles blanques
	 * 	@pre Cert
	 * 	@post  Indica el nombre de caselles blanques
	 * 
	 */
	
	public int getWhiteCells() { return whiteCells; }
	
	/** @brief Consulta el nombre de caselles negres
	 * 	@pre Cert
	 * 	@post  Indica el nombre de caselles negres 
	 * 
	 */
	
	public int getBlackCells() { return blackCells; }
	
	/** @brief Consulta el nombre horitzontal de la casella negra i j
	 * 	@pre Cert
	 * 	@post  Retorna el nombre horitzontal de la casella negra
	 * 
	 */
	
	public int getHNum(int i,int j) { return b[i][j].getHNum(); }
	
	/** @brief Consulta el nombre vertical de la casella negra i j
	 * 	@pre Cert
	 * 	@post  Retorna el nombre vertical de la casella negra
	 * 
	 */
	
	public int getVNum(int i,int j) { return b[i][j].getVNum(); }
	
	/** @brief Consulta la solucio de la casella blanca a la posicio [i,j]
	 * 	@pre i > 0 i j > 0
	 * 	@post  Indica la solucio de la casella
	 * 
	 */
	
	public int getSol(int i, int j) { return b[i][j].getSol(); }
	
	/** @brief Consulta el nivell de dificultat del tauler
	 * 	@pre Cert
	 * 	@post  Indica el nivell de dificultat del tauler
	 * 
	 */
	
	public String getLevel() { return level; }
	
	/** @brief Consultora del valor d'una casella
	 * @pre Donats un enter rows > i >=0 i un enter columns > j >= 0
	 * @post Es retorna el valor de la casella si aquesta es blanca, -1 altrament
	 * @param i
	 * @param j
	 */
	
	public int getValue(int i, int j) {
		return b[i][j].getValue();
	}
	
	/**@brief Consultora de la matriu de caselles
	 * @pre cert
	 * @post Es retorna la matriu de caselles b que forma el tauler
	 */
	
	public Cell[][] getCells() {
		return b;
	}
	
// setters
	
	/** @brief Modificadora del nivell d un tauler
	 * @pre Donat un nivell valid
	 * @post S ha assignat el nivell al tauler
	 * @param lev = nivell a assignar
	 */
	
	public void setLevel(String lev) { level = lev; }
	
	/** @brief Modificadora del valor d'una cassella
	 * @pre Donats uns enters i, j, x
	 * @post Si la cassella i,j és blanca, x és el seu nou valor. L'atribut emptyCells es troba actualitzat.
	 * @param i = Posicio vertical de la cassella
	 * @param j = Posicio horitzontal de la cassella
	 * @param x = Valor a assignar a la cassella
	 * @throws ExceptionOutOfRangeValue
	 * @throws ExceptionOutOfRangePosition
	 */
	
	public void setValue(int i, int j, int x) throws ExceptionOutOfRangeValue, ExceptionOutOfRangePosition {
		if(i < 0 || i >= rows || j < 0 || j >= columns) throw new ExceptionOutOfRangePosition();
		if(x < 0 || x > 9) throw new ExceptionOutOfRangeValue();
		if(b[i][j].isBlack() || b[i][j].isPerm()) throw new ExceptionOutOfRangePosition();
		
		if(b[i][j].getValue() == 0 && x != 0) --emptyCells;
		else if(b[i][j].getValue() != 0 && x == 0) ++emptyCells;
		b[i][j].setValue(x);
	}
	
	/** @brief Modifica la soluci de la casella [i,j]
	 * @pre Donats un enters 0 <= i < rows, i un enter 0 <= i < columns que indiquen una cassella blanca i un enter value 
	 * @post Es modifica la solucio de la cassella i,j per value
	 * @param i = Posicio vertical de la cassella
	 * @param j = Posicio horitzontal de la cassella
	 * @param value = solucio de la cassella
	 */
	
	public void setSol(int i, int j, int value) {
		b[i][j].setSol(value);;
	}
	
	/**@brief Modificadora de la solucio de tot el tauler
	 * @pre cert
	 * @post Les caselles blanques del tauler actualitzen la seva solucio amb el seu valor actual
	 */
	
	public void setSolution() {
		for(int m = 0; m < rows; ++m) {
			for(int n = 0; n < columns; ++n) {
				if(b[m][n].isWhite() && !b[m][n].isPerm()) {
					b[m][n].setSol(b[m][n].getValue());
				}
			}
		}
	}
	
	/** @brief Converteix una casella buida en una casella negra
	 * @pre Donats uns enters 0 <= i < rows, 0 <= j < columns i uns enters c i r
	 * @post La cassella i,j es torna una cassella negra amb suma horitzontal c i suma vertical r
	 * @param i = Posicio vertical de la cassella
	 * @param j =  Posicio horitzontal de la cassella
	 * @param c = Suma horitzontal de la cassella
	 * @param r = Suma vertical de la cassella
	 */
	
	public void setBlack(int i, int j, int c, int r) { //converts type Cell into subclass BlackCell and sets its values
		BlackCell bl = new BlackCell(c,r);
		b[i][j] = bl;
	}
	
	/** @brief Modificadora de la suma horitzontal d'una cassella negra
	 * @pre Donats un enters 0 <= i < rows, i un enter 0 <= i < columns que indiquen una cassella negra i un enter value 
	 * @post Es modifica la suma horitzontal de la cassella i,j per value
	 * @param i = Posicio vertical de la cassella
	 * @param j = Posicio horitzontal de la cassella
	 * @param value = Suma horitzontal de la cassella
	 */
	
	public void setBlackHNumber(int i, int j, int value) {
		b[i][j].setHNum(value);
	}
	
	/** @brief Modificadora de la suma vertical d'una cassella negra
	 * @pre Donats un enters 0 <= i < rows, i un enter 0 <= i < columns que indiquen una cassella negra i un enter value 
	 * @post Es modifica la suma vertical de la cassella i,j per value
	 * @param i = Posicio vertical de la cassella
	 * @param j = Posicio horitzontal de la cassella
	 * @param value = Suma vertical de la cassella
	 */
	
	public void setBlackVNumber(int i, int j, int value) {
		b[i][j].setVNum(value);
	}
	
	/** @brief Converteix una casella buida en una casella blanca
	 * @pre Donats uns enters 0 <= i < rows, 0 <= j < columns, un enter v i un boolea p
	 * @post La cassella i,j es torna una cassella blanca, amb valor v i s indica si es permanent amb el boolea p
	 * @param i = Posicio vertical de la cassella
	 * @param j =  Posicio horitzontal de la cassella
	 * @param v = Valor de la cassella
 	 * @param p = Boolea que indica la permanencia 
	 */
	
	public void setWhite(int i, int j, int v, boolean p) { //converts type Cell into subclass WhiteCell and sets its values
		WhiteCell wh = new WhiteCell(v,p);
		b[i][j] = wh;
	}
	
//methods
	
	private double[] getSeqSums(int size) {
		double[] sums = {0,0};
		for(int i = 1; i <= size; ++i) {
			sums[0] += i;
			sums[1] += 10-i;
		}
		return sums;
	}
	
	/**@brief Metode per determinar la dificultat d'un tauler
	 * @pre cert
	 * @post s ha assignat la dificultat al tauler
	 */
	
	public String determineDifficulty() {
		double sum = 0;
		double seqs = 0;
		for(int i = 0; i < rows; ++i) {
			for(int j = 0; j < columns; ++j) {
				if(b[i][j].getValue()==-1) {
					if(b[i][j].getHNum()!=0) {
						++seqs;
						double num = b[i][j].getHNum();
						int jaux = j+1;
						int size = 0;
						while(jaux < columns && b[i][jaux].getValue()!=-1) {
							++size;
							++jaux;
						}
						double[] sums = getSeqSums(size);
						if(size != 9) {
							double temp = (num-sums[0])/(sums[1]-sums[0]);
							if(temp > 0.5) sum+=(1-temp);
							else sum += temp;
						} else {
							sum+=0.5;
						}
					}
					if(b[i][j].getVNum()!=0) {
						++seqs;
						double num = b[i][j].getVNum();
						int iaux = i+1;
						int size = 0;
						while(iaux < rows && b[iaux][j].getValue()!=-1) {
							++size;
							++iaux;
						}
						double[] sums = getSeqSums(size);
						if(size != 9) {
							double temp = (num-sums[0])/(sums[1]-sums[0]);
							if(temp > 0.5) sum+=(1-temp);
							else sum += temp;
						} else {
							sum+=0.5;
						}
					}
				}
			}
		}
		
		double mean = sum/seqs;
	
		if(rows+columns <= 30) {
			if(mean < 0.15) level = "EASY";
			else if(mean < 0.2) level = "MEDIUM";
			else level = "HARD";
		} else {
			if(mean < 0.17) level = "EASY";
			else if(mean < 0.22) level = "MEDIUM";
			else level = "HARD";
		}
		return level;
	}
	
	/**@brief Metode que troba la seguent casella blanca a partir d una posicio
	 * @pre Donada una posicio pos[](0 <= pos[0] < rows i 0 <= pos[1] <columns) que indica una casella blanca
	 * @post La posicio pos[] es la posicio de la seguent cassella blanca mes propera
	 * @param pos = Posicio d'una cassella del tauler
	 */
	
	public void findNextWhiteCell(int[] pos) { //looks for next available white cell
		if(pos[1] < (columns-1)) ++pos[1];
		else {
			pos[1] = 0;
			++pos[0];
		}
		while(!b[pos[0]][pos[1]].isWhite() || b[pos[0]][pos[1]].isPerm()) {
			if(pos[1] < (columns-1)) {
				++pos[1];
			} else {
				++pos[0];
				pos[1] = 0;
			}
		}
	}
	
	/** @brief Metode que converteix cert input en una matriu de caselles
	 * @pre Donada una ArrayList de strings amb la informacio necessaria per generar un board
	 * @post Es llegeix un Board d aquest input
	 * @param input 
	 * @throws Exception
	 */
	
	private void readBoard(ArrayList<String> input) throws Exception { //reads board from input
		String str = input.get(0);
		String[] s = str.split(",");
		rows = Integer.parseInt(s[0]);
		columns = Integer.parseInt(s[1]);
		if(rows < 5 || columns < 5) throw new ExceptionInvalidSize();
		b = new Cell[rows][columns];
		
		for(int i = 1; i < input.size(); ++i) {
			processLine(i-1, input.get(i));
		}
		countCells();
	}
	
	/**@brief Metode que processa una linia d input 
	 * @pre Donada un int 0 <= r < rows que indica la fila, i una string str que conte la fila en questio
	 * @post Es converteix el contingut de la string str en una fila de la matriu de caselles b
	 * @param r = Posicio de la fila
	 * @param str = Contingut de la fila
	 * @throws Exception
	 */
	
	private void processLine(int r, String str) throws Exception {
		char aux;
		int i = 0;
		for(int c = 0; c < columns; ++c) {
			aux = str.charAt(i);
			++i;
			if(aux != ',') {
				if(aux == '*') setBlack(r,c,0,0);
				else if(aux == '?') setWhite(r,c,0,false);
				else if(aux >= '1' && aux <= '9') {
					setWhite(r,c,Character.getNumericValue(aux),true);
				}
				else if(aux == 'C') {
					int valC = 0;
					int valF = 0;
					while(i < str.length() && str.charAt(i) >= '0' && str.charAt(i) <= '9') {
						aux = str.charAt(i);
						++i;
						valC *= 10;
						valC += Character.getNumericValue(aux);
					}
					if(i < str.length() && str.charAt(i) == 'F') {
						aux = str.charAt(i);
						++i;
						while(i < str.length() && str.charAt(i) >= '0' && str.charAt(i) <= '9') {
							aux = str.charAt(i);
							++i;
							valF *= 10;
							valF += Character.getNumericValue(aux);
						}
					}
					setBlack(r,c,valC,valF);
				}
				else if(aux == 'F') {
					int val = 0;
					while(i < str.length() && str.charAt(i) >= '0' && str.charAt(i) <= '9') {
						aux = str.charAt(i);
						++i;
						val *=10;
						val += Character.getNumericValue(aux);
					}
					setBlack(r,c,0,val);
				}
			} else {
				--c;
			}
		}
	}
	
	/** @brief Metode que incialitza els atributs referents als comptadors de caselles
	 * @pre cert
	 * @post S incialitzen els atributs de la classe referents als comptadors de caselles
	 * @throws ExceptionInvalidBoard
	 */
	
	public void countCells() {
		whiteCells = 0;
		blackCells = 0;
		iniCells = 0;
		emptyCells = 0;
		for(int i = 0; i < rows; ++i) {
			for(int j = 0; j < columns; ++j) {
				if(b[i][j].isWhite()) {
					++whiteCells;
					if(b[i][j].getValue() == 0) ++emptyCells;
				}
				else {
					++blackCells;
				}
			}
		}
		//if(whiteCells == 0) throw new ExceptionInvalidBoard();
		iniCells = whiteCells - emptyCells;
	}
	
	/** @brief Metode per imprimir el contingut de la matriu de caselles
	 * @pre cert
	 * @post S escriu el contingut de la matriu de caselles b per pantalla
	 */
	
	public void writeBoard() {
		for(int i = 0; i < rows; ++i) {
			for(int j = 0; j < columns; ++j) {
				if(b[i][j].isWhite()) {
					if(b[i][j].getValue() == 0) {
						System.out.print("?");
					} else {
						System.out.print(b[i][j].getValue());
					}
				} else {
					int nH = b[i][j].getHNum();
					int nV = b[i][j].getVNum();
					if(nV != 0) {
						System.out.print("C"+nV);
					}
					if(nH != 0) {
						System.out.print("F"+nH);
					}
					if(nH == 0 && nV == 0) {
						System.out.print("*");
					}
				}
				if(j != columns-1) {
					System.out.print(",");
				}
			}
			System.out.print("\n");
		}
		System.out.println();
	}

	/** @brief Metode per imprimir el contingut de la matriu de caselles
	 * @pre cert
	 * @post S escriu el contingut de la matriu de caselles b per pantalla
	 */
	
	public void writeSolution() {
		for(int i = 0; i < rows; ++i) {
			for(int j = 0; j < columns; ++j) {
				if(b[i][j].isWhite()) {
					System.out.print(b[i][j].getSol());
				} else {
					int nH = b[i][j].getHNum();
					int nV = b[i][j].getVNum();
					if(nV != 0) {
						System.out.print("C"+nV);
					}
					if(nH != 0) {
						System.out.print("F"+nH);
					}
					if(nH == 0 && nV == 0) {
						System.out.print("*");
					}
				}
				if(j != columns-1) {
					System.out.print(",");
				}
			}
			System.out.print("\n");
		}
		System.out.println();
	}
	
	/** @brief Metode que modifica el valor d una cassella aleatoria blanca i introdueix la seva solucio
	 *  @pre cert
	 *  @post Una cassella blanca aleatoria del tauler que no estigues resolta ara es troba resolta.
	 */
	
	public int[] getHint() {
		int i;
		int j;
		while(true) {
			i = (int) ((Math.random() * (rows - 1))+ 1);
			j = (int) ((Math.random() * (columns - 1))+ 1);
			if(b[i][j].isWhite() && !b[i][j].isPerm()) {
				if(b[i][j].getValue() != b[i][j].getSol()) {
					try {
						setValue(i,j,b[i][j].getSol());
					} catch (Exception e) {
						System.out.println("An error occurred when showing hint");
					}
					b[i][j].setPerm(true);
					int[] pos = {i,j,b[i][j].getSol()};
					return pos;
				}
			}
		}
	}
	
}
