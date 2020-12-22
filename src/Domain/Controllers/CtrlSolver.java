/** @file CtrlSolver.java */
package Domain.Controllers;

import java.util.Stack;

import Domain.Classes.Board;
import Exceptions.ExceptionOutOfRangePosition;
import Exceptions.ExceptionOutOfRangeValue;

/** @class CtrlSolver
 * @brief Aquesta classe controla el solucionador de kakuros
 * 
 *
 */
public class CtrlSolver {
	
//attributes
	
	/** @brief Instancia de la classe board amb el tauler a solucionar */
	private Board b;
	/** @brief Instancia de la classe CtrlRuleViolations utilitzada per comprobar restriccions */
	private CtrlRuleViolations CR;
	/** @brief Stack auxiliar d'enters utilitzat en la funcio buscaSeq */
	static Stack<Integer> s = new Stack<Integer>();
	
//constructors 
	
	/**@brief Constructora per defecte
	 * @pre cert
	 * @post Es genera una instancia de CtrlSolver amb una instancia de Board = b i una instancia de CtrlRuleViolations amb el tauler associat b
	 */
	
	public CtrlSolver(Board board) {
		b = board;
		CR = new CtrlRuleViolations(b);
	}

//methods
	
	/**@brief Funcio per resoldre kakuros
	 * @pre cert
	 * @post Retorna cert si el tauler te una, i nomes una, solucio; sino retorna fals
	 */
	
	public boolean solveKakuro() {
		int[] pos = {0,0};
		b.findNextWhiteCell(pos);
		int [] solutions = {0};
		try {
			solve(pos[0],pos[1],b.getEmptyCells(),solutions);
		} catch (Exception e) {
			e.getMessage();
		}
		return (solutions[0]==1);
	}
	
	/**@brief  Funcio recursiva auxiliar de solveKakuro()
	 * @pre Donats uns enters i,j (0 <= i < rows, 0 <= j < columns), un enter remainingcells i array d'enters solutions
	 * @post Si el parametre remainingCells es = 0, i solutions no arriba a 2 s actualitza la solucio del tauler
	 * @param i = Posicio vertical de la cassella
	 * @param j = Posicio horitzontal de la cassella
	 * @param remainingCells = Nombre de casselles que hi ha buides al tauler actualment
	 * @param solutions = Nombre de solucions trobades
	 * @throws ExceptionOutOfRangeValue
	 * @throws ExceptionOutOfRangePosition
	 */
	
	private void solve(int i, int j, int remainingCells, int[] solutions) throws ExceptionOutOfRangeValue, ExceptionOutOfRangePosition{ //should never throw exception because values and access to cells are controlled
		int actualCells = remainingCells - 1;
		if(solutions[0] == 2) return;
		
		int BlackHor=getBlackHorValue(i,j);
		int LongHor=longHorFromWhite(i,j);
		int BlackVert=getBlackVertValue(i,j);
		int LongVert=longVertFromWhite(i,j);
		
		boolean[] possiblesHor = buscaSeq(LongHor,BlackHor); 
		boolean[] possiblesVert = buscaSeq(LongVert,BlackVert); 
		for(int k = 1; k < 10; ++k) {
			b.setValue(i, j, k);
			/*System.out.println();
			b.writeBoard();
			System.out.println(actualCells);*/ //for debugging purposes
			if(CR.ruleViolations(i,j) && possiblesHor[k] && possiblesVert[k]) {
				if(actualCells == 0) {
					/*System.out.println("kakuro solved!");*/ //for debugging purposes
					//b.writeBoard();
					b.setSolution();
					++solutions[0];
				}
				else {
					int[] pos = {i,j};
					b.findNextWhiteCell(pos);
					solve(pos[0],pos[1],actualCells,solutions);
				}
			} 
			b.setValue(i, j, 0);
		}
	}
	
	/** @brief Metode per trobar la longitud d una sequencia horitzontal partint d una cassella blanca
	*	@pre Donats uns enters i,j (0 <= i < rows, 0 <= j < columns) que representen una cassella blanca
	*	@post Es retorna la longitud de la sequencia horitzontal en la que es troba la cassella blanca
	*	@param i = Posicio vertical de la cassella
	*	@param j = Posicio horitzontal de la cassella	
	*/
	
	private int longHorFromWhite(int i, int j) {
		int longitud=1;
		int j_ini=j;
		--j;
		int columns=b.getColumns();
		while(j>=0 && b.getValue(i,j)!=-1) {
			++longitud;
			--j;
		}
		j=j_ini;
		++j;
		while(j<columns && b.getValue(i,j)!=-1) {
			++longitud;
			++j;
		}
		return longitud;
		
	}
	
	/** @brief Metode per trobar la longitud d una sequencia vertical partint d una cassella blanca
	*	@pre Donats uns enters i,j (0 <= i < rows, 0 <= j < columns) que representen una cassella blanca
	*	@post Es retorna la longitud de la sequencia vertical en la que es troba la cassella blanca
	*	@param i = Posicio vertical de la cassella
	*	@param j = Posicio horitzontal de la cassella	
	*/	
	
	private int longVertFromWhite(int i, int j) {
		int longitud=1;
		int i_ini=i;
		--i;
		int rows=b.getRows();
		while(i>=0 && b.getValue(i,j)!=-1) {
			++longitud;
			--i;
		}
		i=i_ini;
		++i;
		while(i<rows && b.getValue(i,j)!=-1) {
			++longitud;
			++i;
		}
		return longitud;
		
		
	}
	
	/** @brief Metode que retorna el valor de la suma horitzontal on es troba la cassella blanca
	*	@pre Donats uns enters i,j (0 <= i < rows, 0 <= j < columns) que representen una cassella blanca
	*	@post Es retorna la suma horitzontal on es troba la cassella blanca
	*	@param i = Posicio vertical de la cassella
	*	@param j = Posicio horitzontal de la cassella	
	*/
	
	private int getBlackHorValue(int i,int j) {
		--j;
		while(j>=0 && b.getValue(i, j)!=-1) --j;
		return b.getHNum(i,j);
	}
	
	/** @brief Metode que retorna el valor de la suma vertical on es troba la cassella blanca
	*	@pre Donats uns enters i,j (0 <= i < rows, 0 <= j < columns) que representen una cassella blanca
	*	@post Es retorna la suma vertical on es troba la cassella blanca
	*	@param i = Posicio vertical de la cassella
	*	@param j = Posicio horitzontal de la cassella	
	*/
	
	private int getBlackVertValue(int i,int j) {
		--i;
		while(i>=0 && b.getValue(i, j)!=-1) --i;
		return b.getVNum(i,j);
	}
	
	/** @brief Metode que diu els possibles numeros que es poden trobar a una sequencia
	* 	@pre Donats un enter longitud i un enter valor
	*	@post Retorna un vector de booleans on la posicio [i] indica si el numero i pot estar o no a la sequencia
	*	@param longitud = Longitud de la sequencia
	*	@param valor = Suma de la sequencia
	*/
	
	private boolean[] buscaSeq(int longitud,int valor) {
		int aux=0;
		boolean valorsPossibles[]=new boolean[10];
		for(int i=0;i<longitud;++i) aux +=i;
		
		int stopat = (valor-aux)/longitud;
		
		while(s.size()>0) s.pop();
		
		for(int i=1;i<=stopat;++i) {
			s.push(i);
			buscaSeqRec(longitud,valor,i,i,valorsPossibles);
			s.pop();
		}
		return valorsPossibles;
	}
	
	/** @brief Metode auxiliar recursiu de buscaSeq
	* 	@pre Donats un enter longitud, un enter valor, un enter suma, un enter anterior i un vector de booleans v
	*	@post Si la mida de la pila s = longitud i suma actual = valor, per cada element de la pila i posa v[i] a true.Si no posa un nou element a la pila
	*	@param longitud = Longitud total de la sequencia
	*	@param valor = Valor total de la sequencia
	*	@param suma = Suma de tots el valors de la pila s
	*	@param anterior = Numero que hi ha a dalt de la pila s
	*	@param v = Vector de booleans on es coloca la solucio
	*/
	
	private void buscaSeqRec(int longitud,int valor,int suma,int anterior,boolean[] v) {
		for (int i=anterior+1;i<10;++i) {
			s.push(i);
			suma=suma + i;
			if(s.size() == longitud && suma == valor) {
				actu(v);
			}
			else {
				buscaSeqRec(longitud,valor,suma,i,v);
			}
			
			s.pop();
			suma -= i;
		}
	}
	
	/** @brief Metode que actualitza el vector de booleans
	*	@pre La pila s conte una possible sequencia
	*	@post S actualitza el vector de booleans v amb els valors de la pila
	*	@param v = Vector de booleans on es coloca la solucio
	*/
	
	private void actu(boolean[] v) {
		for (int i = 0; i < s.size(); i++) 
			v[s.get(i)]=true;
	}

}