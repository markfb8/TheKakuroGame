/** @file CtrlGenerator.java */

package Domain.Controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.concurrent.ThreadLocalRandom;

import Domain.Classes.Board;
import Domain.Classes.Cell.Cell;
import Exceptions.ExceptionInvalidSize;
import Exceptions.ExceptionOutOfRangePosition;
import Exceptions.ExceptionOutOfRangeValue;

/** @class CtrlGenerator
 * @brief  Aquest controlador s encarrega de generar taulers de manera aleatoria 
 */

public class CtrlGenerator {

//attributes
	
	/** @brief Instancia de la classe Board que representa el tauler que generarem*/
	private Board b;
	/** @brief Instancia de la classe CtrlSolver amb l atribut b per utilitzar el solucionador */
	private CtrlSolver CS;
	/** @brief Atribut que indica el nombre de files que tindra el tauler de la classe Board */
	private int rows;
	/** @brief Atribut que indica el nombre de columnes que tindra el tauler de la classe Board */
	private int columns;
	/** @brief Atribut que indica el nivell de dificultat que tindra el tauler */
	private String level;
	
//constructors
	
	public CtrlGenerator() {}
	
//methods
	
	/**@brief Metode que genera el tauler 
	 * @pre Donat uns enter r,c i un string level que pren valors (EASY, MEDIUM, HARD)
	 * @post Es retorna un tauler generat aleatoriament i que es valid per jugar 
	 * @param r = NOmbre de files del tauler
	 * @param c = Nombre de columnes del tauler 
	 * @param level = Nivell de dificultat del tauler
	 * @throws ExceptionInvalidSize 
	 */
	
	public Board generateBoard(int r, int c, String level) throws ExceptionInvalidSize {
		b = new Board(r,c);
		b.setLevel(level);
		rows = r;
		columns = c;
		this.level=level;
		generatePattern(level);
		setNumbers();
		return b;
	}
	
	/**@brief Metode que inicialitza les casselles del tauler
	 * @pre cert
	 * @post La primera fila i columna son casselles negres, la resta son blanques
	 */
	
	private void initialize() {
		for (int i=0;i<rows;++i) {
			for (int j=0;j<columns;++j) {
				if(i==0 || j==0) b.setBlack(i, j, 0, 0);
				else b.setWhite(i, j, 0, false);
			}
		}
	}
	
	/**@brief Metode que genera un patro aleatori de casselles blanques i negres
	 * @pre Donat un string level que indica el nivell de dificultat
	 * @post Es genera un patro aleatori de casselles blanques i negres
	 * @param level = Nivell de dificultat del tauler
	 */
	
	private void generatePattern(String level) {
		double prob = 0; //DO NOT SET UNDER 50!!!
		switch(level) {
		case "EASY":
			prob = 85;
			break;
		case "MEDIUM":
			prob = 70;
			break;
		case "HARD":
			prob = 55;
			if(rows > 15 || columns > 15) prob = 70;
			if(rows > 18 || columns > 18) prob = 80;
			break;
		}
		double centerProb = prob * 0.7;
		int halfRow = (rows-1)/2;
		int halfCols = (columns-1)/2;
		boolean valid = false;
		while(!valid) { //set the value of the upper half of the board without 1 cell sequences
			initialize();
			for(int i = 1; i < rows; ++i) {
				if(rows % 2 != 0 || (rows % 2 == 0 && i != halfRow+1)) {
					for(int j = 1; j < columns; ++j) {
						if(i <= halfRow) {
							double randomValue = Math.random()*100;
							if(i > 1 && j > 1) {
								if(b.getValue(i, j-1) != -1 && b.getValue(i, j-2) == -1) randomValue = 100;
								else if(b.getValue(i-1, j) != -1 && b.getValue(i-2, j) == -1) randomValue = 100;
							} else if(i == 1) {
								if(b.getValue(i, j-1) != -1 && b.getValue(i, j-2) == -1) randomValue = 100;
							} else if (j == 1){
								if(b.getValue(i-1, j) != -1 && b.getValue(i-2, j) == -1) randomValue = 100;
							}
							
							if(i < halfRow/2 || j < halfCols/2 || j > columns-(halfCols/2)) {
								if(randomValue <= prob) b.setBlack(i,j,0,0);
							} else {
								if(randomValue <= centerProb) b.setBlack(i,j,0,0);
							}
							
							if(j == columns-1 && b.getValue(i, j) != -1) {
								if(i == 1) {
									if(b.getValue(i, j-1) == -1) b.setBlack(i,j,0,0);
								}
								if(b.getValue(i,j-1) == -1) {
									if(b.getValue(i-1, j) != -1 && b.getValue(i-2, j) == -1) b.setWhite(i,j-1,0,false);
									else b.setBlack(i, j, 0, 0);
								}
							}
						} else {
							if(b.getValue(rows-i,columns-j) == -1) b.setBlack(i,j,0,0);
						}
					}
				}
			}
			
			//we set the middle row if it exists
			if(rows % 2 == 0) {
				int i = halfRow+1;
				for(int j = 1; j < columns; ++j) {
					if(j < halfCols+1) {
						double randomValue = Math.random()*100;
						if(j == 1) {
							if(b.getValue(i-1,j) != -1 && b.getValue(i-2, j) == -1) randomValue = 100;
							else if(b.getValue(i+1, j) != -1 && b.getValue(i+2, j) == -1) randomValue = 100;
							else if(b.getValue(i-1, j) == -1 && b.getValue(i+1, j) == -1) randomValue = 0;
						}
						else {
							if(b.getValue(i, j-1) != -1 && b.getValue(i, j-2) == -1) randomValue = 100;
							else if(b.getValue(i-1,j) != -1 && b.getValue(i-2, j) == -1) randomValue = 100;
							else if(b.getValue(i+1, j) != -1 && b.getValue(i+2, j) == -1) randomValue = 100;
							else if(b.getValue(i-1, j) == -1 && b.getValue(i+1, j) == -1) randomValue = 0;
						}
						if(j < halfCols/2 || j > (columns-halfCols/2)) {
							if(randomValue <= prob) b.setBlack(i,j,0,0);
						} else {
							if(randomValue <= centerProb) b.setBlack(i,j,0,0);
						}
					} else {
						if(b.getValue(i,columns-j) == -1) b.setBlack(i,j,0,0);
					}
				}
				if(columns % 2 == 0) {
					if(b.getValue(i, halfCols) != -1 && b.getValue(i-1, halfCols) != -1) b.setWhite(i,halfCols+1,0,false);
					else b.setBlack(i,halfCols+1,0,0);
				}
			}
			
			valid = whiteCellsConnected();
			if(valid && (rows > 11 || columns > 11)) valid = checkBigSequences();
			//if there is no middle row we check the mirror
			if(valid) {
				if(rows % 2 != 0) {
					int i = halfRow;
					for(int j = 1; j < columns && valid; ++j) {
						if(b.getValue(i, j) != -1 && b.getValue(i-1,j) == -1 && b.getValue(i+1,j) == -1) valid = false;
					}
				}
			}
			b.countCells();
			
			if(valid && (rows<7 || columns<7)) {
				boolean foundV = false;
				boolean foundH = false;
				for(int i = 1; i < rows && !foundV; ++i) {
					if(b.getValue(i,1)!=-1) foundV = true;
				}
				for(int j = 1; j < columns && !foundH; ++j) {
					if(b.getValue(1,j)!=-1) foundH = true;
				}
				valid = foundH && foundV;
			}
			
			//last check to see if it matches the level
			switch(level) {
			case "EASY":
				if(b.getWhiteCells() < ((rows-1)*(columns-1))/3) valid = false;
				break;
			case "MEDIUM":
				if(rows+columns>12) {
					if(b.getBlackCells() > rows*columns/2) valid = false;
				}
				break;
			case "HARD":
				if(rows+columns>12) {
					if(b.getBlackCells() > rows*columns/2) valid = false;
				}
				break;
			}
		}
	}
	
	/** @brief Metode que indica si totes les casselles blanques del tauler es troben connectades
	 * @pre cert
	 * @post Es retorna true si totes les casselles blanques del tauler es troben connectades, false altrament
	 */
	
	private boolean whiteCellsConnected() {
		boolean board[][] = new boolean[rows][columns];
		boolean blanques=false;
		for(int i=0;i<rows;++i) { //black cells as visited
			for(int j=0;j<columns;++j) {
				if(b.getValue(i,j)==-1) board[i][j]=true;
				else {
					board[i][j]=false;
					blanques=true;
				}
			}
		}
		if(!blanques) return false;
		int[] pos = {0,0};
		b.findNextWhiteCell(pos);
		
	    dfs(board,pos[0],pos[1]); //dfs to visit all white cells
	    for(int i=0;i<rows;++i) {
			for(int j=0;j<columns;++j) {
				if(!board[i][j]) {
	                return false; //return false if any was not visited
				}
			 }
	    }	
	    return true;
	}
	
	/**@brief Metode auxiliar recursiu per la funcio whiteCellsConnected
	 * @pre Donada una matriu de booleans board, i uns enters i,j (0<=i<rows, 0<=j<columns)
	 * @post Les poscions accessibles des de la inicial es marquen a la matriu de booleans com a true
	 * @param board = Matriu de booleans que representa el tauler
	 * @param i = Posicio vertical de la cassella
	 * @param j = Posicio horitzontal de la cassella
	 */
	
	private void dfs(boolean board[][],int i,int j) {
		board[i][j]=true;
	    if(i>0 && !board[i-1][j]) dfs(board,i-1,j);
	    if(i<rows-1 && !board[i+1][j]) dfs(board,i+1,j);
	    if(j>0 && !board[i][j-1]) dfs(board,i,j-1);
	    if(j<columns-1 && !board[i][j+1]) dfs(board,i,j+1);
	}
	
	/**@brief Metode que elimina les sequencies de 10 o mes casselles del tauler
	 * @pre cert
	 * @post Es retorna true si no existeixen sequencies de 10 o mes casselles al tauler, false altrament
	 */
	
	private boolean checkBigSequences() {
		int iter=0;
		int halfRow = (rows-1)/2;
		for(int i=1;i<halfRow+2;++i) {
	    	for(int j=0;j<columns;++j) {
	    	    if(b.getValue(i, j) == -1) {
	    	    	++j;
	    	    	int ini = j;
	    	    	while(j<columns && b.getValue(i, j) != -1) {
	    	    		++iter;
	    	    		++j;
	    	    	}
	    	    	--j;
	    	    	
	    	    	if(i == halfRow+1 && iter > 9) return false;
	    	    	
		    	    if(iter>9) {
		    	    	int iseq = ini+2;
		    	    	int jseq = j-2;
		    	    	if(iter > 13) {
		    	    		while(j-iseq > 8) ++iseq;
		    	    		while(jseq-ini > 8) --jseq;
		    	    		if(iseq > 8 || j-jseq > 8) return false;
		    	    	}
		    	    	boolean valid = false;
		    	    	int randomNum = 0;
		    	    	boolean[] tried = new boolean[(jseq-iseq)+1];
		    	    	while(!valid) {
		    	    		boolean all = true;
		    	    		for(int k = 0; k < tried.length && all; ++k) {
		    	    			if(!tried[k]) all = false;
		    	    		}
		    	    		if(!all) {
			    	    		randomNum = ThreadLocalRandom.current().nextInt(iseq,jseq);
			    	    		if(!tried[randomNum-iseq]) {
			    	    			tried[randomNum-iseq] = true;
				    	    		b.setBlack(i, randomNum, 0,0);
				    	    		if(i == 1) {
				    	    			if(b.getValue(i+1, randomNum) != -1 && b.getValue(i+2, randomNum) == -1) b.setWhite(i, randomNum , 0, false);
				    	    			else valid = true;
				    	    		} else if(rows % 2 == 0 && i == halfRow-1) {
				    	    			if(!whiteCellsConnected()) b.setWhite(i, randomNum , 0, false);
				    	    			else if(b.getValue(i+1,randomNum) != -1) b.setWhite(i, randomNum , 0, false);
				    	    			else valid = true;
				    	    		} else {
					    	    		if(!whiteCellsConnected()) b.setWhite(i, randomNum , 0, false);
					    	    		else if(b.getValue(i-1,randomNum) != -1 && b.getValue(i-2, randomNum) == -1) b.setWhite(i, randomNum , 0, false);
										else if(b.getValue(i+1, randomNum) != -1 && b.getValue(i+2, randomNum) == -1) b.setWhite(i, randomNum , 0, false);
										else valid = true;
				    	    		}
			    	    		}
		    	    		}
		    	    		return false;
		    	    	}
		    	    	b.setBlack(rows-i,columns-randomNum,0,0);
		    	    }
		    	    iter=0;
	    	    }
	    	}
		}
		
		int halfCol = (columns-1)/2;
		for(int j=1;j<halfCol+2;++j) {
			for(int i=0;i<rows;++i) {
	    	    if(b.getValue(i, j) == -1) {
	    	    	++i;
	    	    	int ini = i;
	    	    	while(i<rows && b.getValue(i, j) != -1) {
	    	    		++iter;
	    	    		++i;
	    	    	}
	    	    	--i;
	    	    	
	    	    	if(j == halfRow+1 && iter > 9) return false;
	    	    	
		    	    if(iter>9) {
		    	    	int iseq = ini+2;
		    	    	int jseq = i-2;
		    	    	if(iter > 13) {
		    	    		while(j-iseq > 8) ++iseq;
		    	    		while(jseq-ini > 8) --jseq;
		    	    		if(iseq > 8 || j-jseq > 8) return false;
		    	    	}
		    	    	boolean valid = false;
		    	    	int randomNum = 0;
		    	    	boolean[] tried = new boolean[(jseq-iseq)+1];
		    	    	while(!valid) {
		    	    		boolean all = true;
		    	    		for(int k = 0; k < tried.length && all; ++k) {
		    	    			if(!tried[k]) all = false;
		    	    		}
		    	    		if(!all) {
			    	    		randomNum = ThreadLocalRandom.current().nextInt(iseq,jseq);
			    	    		if(!tried[randomNum-iseq]) {
			    	    			tried[randomNum-iseq] = true;
				    	    		b.setBlack(randomNum, j, 0,0);
				    	    		if(j == 1) {
				    	    			if(b.getValue(randomNum, j+1) != -1 && b.getValue(randomNum, j+2) == -1) b.setWhite(randomNum,j , 0, false);
				    	    			else valid = true;
				    	    		} else if(columns % 2 == 0 && j == halfCol-1) {
				    	    			if(!whiteCellsConnected()) b.setWhite(randomNum, j , 0, false);
				    	    			else if(b.getValue(randomNum,j+1) != -1) b.setWhite(randomNum,j, 0, false);
				    	    			else valid = true;
				    	    		} else {
					    	    		if(!whiteCellsConnected()) b.setWhite(randomNum, j , 0, false);
					    	    		else if(b.getValue(randomNum,j-1) != -1 && b.getValue(randomNum, j-2) == -1) b.setWhite(randomNum, j , 0, false);
					    	    		else if(b.getValue(randomNum,j+1) != -1 && b.getValue(randomNum, j+2) == -1) b.setWhite(randomNum, j , 0, false);
										else valid = true;
				    	    		}
			    	    		}
		    	    		}
		    	    		return false;
		    	    	}
		    	    	if(i < halfRow) {
		    	    		b.setBlack(rows-randomNum,columns-j,0,0);
		    	    	} else if(i == halfRow) {
		    	    		b.setBlack(randomNum,columns-j,0,0);
		    	    	} else {
		    	    		b.setBlack(j,randomNum,0,0);
		    	    	}
		    	    }
		    	    iter=0;
	    	    }
	    	}
		}
		return true;
	}
	
	/**@brief Metode que actualitza les sumes horitzontals i verticals de les sequencies de 9
	 * @pre cert
	 * @post Les casselles negres associades a una sequencia de 9 prenen com a valor de la suma de la sequencia 45.
	 */
	
	private void setBigSequences() {
		int iter=0;
		for(int i=1;i<rows;++i) {
	    	for(int j=0;j<columns;++j) {
	    		int ini = 0;
	    	    if(b.getValue(i, j) == -1) {
	    	    	ini = j;
	    	    	++j;
	    	    	while(j<columns && b.getValue(i, j) != -1) {
	    	    		++iter;
	    	    		++j;
	    	    	}
	    	    	--j;
	    	    }
	    	    if(iter==9) b.setBlackHNumber(i, ini, 45); 
	    	    iter=0;
	    	}
	    }
		for(int j=1;j<columns;++j) {
	    	for(int i=0;i<rows;++i) {
	    		int ini = 0;
	    	    if(b.getValue(i, j) == -1) {
	    	    	ini = i;
	    	    	++i;
	    	    	while(i < rows && b.getValue(i, j) != -1) {
	    	    		++iter;
	    	    		++i;
	    	    	}
	    	    	--i;
	    	    }
	    	    if(iter==9) b.setBlackVNumber(ini, j, 45); 
	    	    iter=0;
	    	}
	    }
	}
	
	/** @brief Metode que assigna valors a les casselles negres d un tauler buit
	 * @pre  cert
	 * @post Les sumes horitzontals i verticals del tauler es troben definides
	 */
	
	private void setNumbers() {
		setBigSequences();
	    boolean finish=false;
		int cont=1;
		while(!finish) {
			for(int i=1;i<rows;++i) {
				for(int j=1;j<columns;++j) {
					if((b.getValue(i,j)!=-1) && b.getSol(i, j)==0 && !completesequence(i,j)) {
						boolean trobat=false;
						int[] posesq= {-1,-1};
						int[] posdalt= {-1,-1};
						trobablackcells(i,j,posesq,posdalt);
						int mida0 = 0,mida1 = 0;
						if(posesq[0]!=-1) mida0=contatamanyhor(posesq[0],posesq[1]+1);
						if(posdalt[0]!=-1) mida1=contatamanyvert(posdalt[0]+1,posdalt[1]);
						if(posesq[0]!=-1 || posdalt[0]!=-1) {
						    ArrayList<Integer> valorshor=new ArrayList<Integer>(mida0);
						    ArrayList<Integer> valorsvert=new ArrayList<Integer>(mida1); 
						    int valorx,valory;
						    if(mida0==0) { //Si la casella horitzontal esquerra ja te valor, probem tots els valors de casella negra vertical possible
						    	establishVector(valorsvert,mida1);
						    	int pos = 0;
						    	while(pos < valorsvert.size() && !trobat) {
								    valory=valorsvert.get(pos);
								    b.setBlackVNumber(posdalt[0],posdalt[1],valory);
							    	trobat=tryToSolve(i,j);
							    	++pos;
						    	}
					        }
						    else if(mida1==0) { //Si la casella vertical ja te valor, probem tots els valors de casella negra horitzontal possible
						    	establishVector(valorshor,mida0);
						    	int pos = 0;
						    	while(pos < valorshor.size() && !trobat) {
								    valorx=valorshor.get(pos);
								    b.setBlackHNumber(posesq[0],posesq[1],valorx);
							    	trobat=tryToSolve(i,j);
							    	++pos;
							    }
						    }
						    else { //Si cap de les dues te valor, probem totes les combinacions possibles
						    	establishVector(valorsvert,mida1);
						    	establishVector(valorshor,mida0);
						    	int pos = 0;
						    	while(pos < valorshor.size() && !trobat) {
							    	valorx=valorshor.get(pos);
							    	b.setBlackHNumber(posesq[0],posesq[1],valorx);
							    	ArrayList<Integer> copia=new ArrayList<Integer>(valorsvert);
							    	int pos2 = 0;
							        while(pos2 < copia.size() && !trobat) {
								    	valory=copia.get(pos2);
								    	b.setBlackVNumber(posdalt[0],posdalt[1],valory);
								    	trobat=tryToSolve(i,j);
								    	++pos2;
								    }
							        ++pos;
						    	}
						    }
					    }
						else {
							trobat=tryToSolve(i,j);
						}
						
						if(!trobat) {
							if(posesq[0]!=-1) b.setBlackHNumber(posesq[0],posesq[1],0);
							if(posdalt[0]!=-1) b.setBlackVNumber(posdalt[0],posdalt[1],0);
						}
						try {
							b.setValue(i,j,(b.getSol(i,j)));
						} catch (ExceptionOutOfRangeValue e) {
							e.printStackTrace();
						} catch (ExceptionOutOfRangePosition e) {
							e.printStackTrace();
						}
					}
				}
			}
			if(cont==7) {
				finish=true;
			}
			++cont;
		}
		
		//clear the board after setting numbers
		int count=0;
		for(int i=0;i<rows;++i) {
			 for(int j=0;j<columns;++j) {
				if((b.getValue(i,j)!=-1)) {
					if(b.getValue(i,j)==0) ++count;
	                try {
						b.setValue(i,j,0);
					} catch (ExceptionOutOfRangeValue e) {
						e.printStackTrace();
					} catch (ExceptionOutOfRangePosition e) {
						e.printStackTrace();
					} 
				}
				else {
			        int valordr=valordre(i,j);
					if(valordr!=0) {
						b.setBlackHNumber(i,j,valordr);
				    }
					else {
					    int valorba=valorup(i,j);
						if(valorba!=0) {
							b.setBlackVNumber(i,j,valorba);
						}
					}
				}
			}
		}
		
		b.countCells();
		CS = new CtrlSolver(b);
		if(level.equals(b.determineDifficulty()) && (count<15 && CS.solveKakuro())) {}
		else {
			for(int i=0;i<rows;++i) {
				for(int j=0;j<columns;++j) {
					 if((b.getValue(i,j)!=-1)) {
							 b.setSol(i,j,0);
					 }
				}
			}
			generatePattern(level);
			setNumbers();
		}
	}
	
	/** @brief Metode que si es pot completar una sequencia la completa
	 * @pre  Donades unes i j que son una posicio valida del tauler(0 < i < rows, 0 <= j < columns)
	 * @param i = Posicio fila de la casella
	 * @param j = Posicio columna de la casella
	 * @post Retorna true i completa la sequencia amb el valor corresponent
	 */
	
	private boolean completesequence(int i,int j) {
		  return completaesquerra(i,j) || completadalt(i,j);
	}
	
	 /** @brief Metode que si el valor horitzontal de la casella negra esquerra es igual a 0 i nomes queda una casella blanca per completar la sequencia vertical, la completa amb el valor necessari
	  * @pre  Donades unes i j que son una posicio valida del tauler(0 < i < rows, 0 <= j < columns)
	  * @param i = Posicio fila de la casella
	  * @param j = Posicio columna de la casella
	  * @post Si el valor vertical de la casella negra superior es igual a 0 i nomes queda una casella blanca per completar la sequencia vertical, la completa amb el valor necessari i retorna true
	  */
	
	private boolean completaesquerra(int i,int j) {
		   int ii=i;
		   int jj=j;
		   while((b.getValue(i,j)!=-1)) --j;
		   int valor=b.getHNum(i,j);
		   if(valor==0) return false;
		   int countbuit=0;
		   ++j;
		   while(j<columns && (b.getValue(i,j)!=-1)) {
		     if(b.getValue(i,j)==0) ++countbuit;
		     else valor=valor-b.getValue(i,j);
		     ++j;
		   }
		   if(countbuit==1) {
		        try {
		        	b.setValue(ii,jj,valor);
				} catch (ExceptionOutOfRangeValue e) {
					e.printStackTrace();
				} catch (ExceptionOutOfRangePosition e) {
					e.printStackTrace();
				}
		        b.setSol(ii,jj,valor);
		    	return true;
		   }
		   return false;
	}
	
	/** @brief Metode que si el valor vertical de la casella negra superior es igual a 0 i nomes queda una casella blanca per completar la sequencia vertical, la completa amb el valor necessari
	 * @pre  Donades unes i j que son una posicio valida del tauler(0 < i < rows, 0 <= j < columns)
	 * @param i = Posicio fila de la casella
	 * @param j = Posicio columna de la casella
	 * @post Si el valor vertical de la casella negra superior es igual a 0 i nomes queda una casella blanca per completar la sequencia vertical, la completa amb el valor necessari i retorna true
	 */
	 
	private boolean completadalt(int i,int j) {
		   int ii=i;
		   int jj=j;
		   while((b.getValue(i,j)!=-1)) --i;
		   int valor=b.getVNum(i,j);
		   if(valor==0) return false;
		   int countbuit=0;
		   ++i;
		   while(i<rows && (b.getValue(i,j)!=-1)) {
		     if(b.getValue(i,j)==0) ++countbuit;
		     else valor=valor-b.getValue(i,j);
		     ++i;
		  }
		   if(countbuit==1) {
		      try {
				b.setValue(ii,jj,valor);
			} catch (ExceptionOutOfRangeValue e) {
				e.printStackTrace();
			} catch (ExceptionOutOfRangePosition e) {
				e.printStackTrace();
			}
		       b.setSol(ii,jj,valor);
		    	return true;
		   }
		   return false;
   }
	
   /** @brief Metode que guarda en pos0 i pos1 les posicions de la casella negra esquerra i la superior, si les caselles negres ja tenen el valor que volem establert li donem valor de -1 a la pos respectiva 
	* @pre  Donades unes i j que son una posicio valida del tauler(0 < i < rows, 0 <= j < columns)
	* @param i = Posicio fila de la casella
	* @param j = Posicio columna de la casella
	* @param pos0 = Contindra la posicio de la casella negra superior
	* @param pos1 = Contindra la posicio de la casella negra esquerra
	* @post Guarda en pos0 i pos1 les posicions de la casella negra esquerra i la superior, si les caselles negres ja tenen el valor que volem establert li donem valor de -1 a la pos respectiva
	*/ 
	
   private void trobablackcells(int i,int j,int[] pos0,int[] pos1) {
        int valorh,valorv;
        
        valorh=blackesquerra(i,j,pos0);
        if(valorh!=0) {
        	pos0[0]=-1;
        	pos0[1]=-1;
        }
        
        valorv=blackdalt(i,j,pos1);
        if(valorv!=0) {
        	pos1[0]=-1;
        	pos1[1]=-1;
        }
   }
   
   /** @brief Metode que retorna el valor vertical de la casella negra esquerra i en pos guarda la posicio d'aquesta
	* @pre  Donades unes i j que son una posicio valida del tauler(0 < i < rows, 0 <= j < columns)
	* @param i = Posicio fila de la casella
	* @param j = Posicio columna de la casella
	* @param pos = Contindra la posicio de la casella negra esquerra
	* @post Retorna el valor horitzontal de la casella negra esquerra i en pos guarda la posicio d'aquesta
	*/
   
   private int blackesquerra(int i,int j,int[] pos) {
    	while((b.getValue(i,j)!=-1)) --j;
    	pos[0]=i;
    	pos[1]=j;
    	return b.getHNum(i,j);
    }
	  
   /** @brief Metode que retorna el valor vertical de la casella negra superior i en pos guarda la posicio d'aquesta
	* @pre  Donades unes i j que son una posicio valida del tauler(0 < i < rows, 0 <= j < columns)
	* @param i = Posicio fila de la casella
	* @param j = Posicio columna de la casella
	* @param pos = Contindra la posicio de la casella negra superior
	* @post Retorna el valor vertical de la casella negra superior i en pos guarda la posicio d'aquesta
	*/
   
    private int blackdalt(int i,int j,int[] pos) {
    	while((b.getValue(i,j)!=-1)) --i;
    	pos[0]=i;
    	pos[1]=j;
    	return b.getVNum(i,j);
    }
    
    /** @brief Metode que conta el nombre de caselles blanques horitzontals consecutives des de la posicio i j
	 * @pre  Donades unes i j que son una posicio valida del tauler(0 <= i < rows, 0 <= j < columns)
	 * @param i = Posicio fila de la casella
	 * @param j = Posicio columna de la casella
	 * @post Retorna el nombre de caselles blanques horitzontals consecutives des de la posicio i j
	 */
    
    private int contatamanyhor(int i,int j) {
		++j;
		int count=1;
		while(j<columns && (b.getValue(i,j)!=-1)) {
			++count;
			++j;
		}
		return count;
    }
    
    /** @brief Metode que conta el nombre de caselles blanques verticals consecutives des de la posicio i j
	 * @pre  Donades unes i j que son una posicio valida del tauler(0 <= i < rows, 0 <= j < columns)
	 * @param i = Posicio fila de la casella
	 * @param j = Posicio columna de la casella
	 * @post Retorna el nombre de caselles blanques verticals consecutives des de la posicio i j
	 */
    
	private int contatamanyvert(int i,int j) {
		++i;
		int count=1;
		while(i<rows && (b.getValue(i,j)!=-1)) {
			++count;
			++i;
		}
		return count;
	}
		
	/** @brief Metode que plena un ArrayList en ordre aleatori amb les sumes possibles en funcio de la seva mida
	 * @pre Cert
	 * @param arr = Array que plenarem amb possibles valors en funcio de la mida
	 * @post arr queda establert en funcio de la seva mida
	 */
	
	private void establishVector(ArrayList<Integer> arr, int size) {
		int min = 0;
		int max = 0;
		for(int i = 1; i <= size; ++i) {
			min += i;
			max += 10-i;
		}
		
		ArrayList<Integer> easy = new ArrayList<Integer>();
		int s = (int) Math.round(((max-min)*0.2));
		for(int i = 0; i <= s; ++i) {
			easy.add(min+i);
			easy.add(max-i);
		}
		
		ArrayList<Integer> med = new ArrayList<Integer>();
		int s2 = (int) Math.round(((max-min)*0.35));
		for(int j = s+1; j <= s2; ++j) {
			med.add(min+j);
			med.add(max-j);
		}
		
		ArrayList<Integer> hard = new ArrayList<Integer>();
		int s3 = (int) Math.round(((max-min)*0.5));
		for(int k = s2+1; k <= s3; ++k) {
			hard.add(min+k);
			hard.add(max-k);
		}
		
		Collections.shuffle(easy);
		Collections.shuffle(med);
		Collections.shuffle(hard);
		switch(level) {
		case "EASY": 
			arr.addAll(easy);
			arr.addAll(med);
			arr.addAll(hard);
			break;
		case "MEDIUM":
			arr.addAll(med);
			easy.addAll(hard);
			Collections.shuffle(easy);
			arr.addAll(easy);
			break;
		case "HARD":
			arr.addAll(hard);
			arr.addAll(med);
			arr.addAll(easy);
			break;
		}
	}
	
	/** @brief Metode que comprova si hi ha un unic valor possible en i,j
	 * @pre  Donades unes i j que son una posicio valida del tauler(0 <= i < rows, 0 <= j < columns)
	 * @param i = Posicio fila de la casella
	 * @param j = Posicio columna de la casella
	 * @post Retorna cert si hi ha una unic valor possible en la posicio i,j
	 */
	
	private boolean tryToSolve(int i,int j) {
		if(correctgrid(i,j)) {
			try {
				b.setValue(i,j,(b.getSol(i,j)));
			} catch (ExceptionOutOfRangeValue e) {
				e.printStackTrace();
			} catch (ExceptionOutOfRangePosition e) {
				e.printStackTrace();
			}
     	   	if(atleastonesolution(1,0)) {
     		  return true;
     	   	}
     	   	else b.setSol(i,j,0);
     	}
		return false;
	}
	
	/** @brief Metode que comprova si hi ha una unica solucio possible en la casella
	 * @pre  Donades unes i j que son una posicio valida del tauler(0 <= i < rows, 0 <= j < columns)
	 * @param i = Posicio fila de la casella
	 * @param j = Posicio columna de la casella
	 * @post Retorna cert si hi ha una solucio possible en la posicio i,j
	 */
	
	private boolean correctgrid(int i,int j) {
		Cell[][] copy=b.getCells();
		int[] solutions = {0};
		solvegrid(copy,i,j,solutions);
		if (solutions[0] == 1) return true;
		else return false;
	}
	
	/** @brief Metode que comprova el nombre de solucions possible en la casella
	 * @pre  Donada una matriu de caselles bo i unes i j que son una posicio valida del tauler(0 <= i < rows, 0 <= j < columns) i un solutions que indica el nombre de solucions trobades fins al moment
	 * @param bo = Matriu de caselles
	 * @param i = Posicio fila de la casella
	 * @param j = Posicio columna de la casella
	 * @post Solutions conte el nombre de solucions trobades en la casella
	 */
	
    private void solvegrid(Cell[][] bo, int i, int j, int[] solutions) {
	    if(solutions[0] == 2) return;
		for(int k = 1; k <10; ++k) {
			bo[i][j].setValue(k);
			boolean condition=(novalorsrep(bo,i,j) && checkPuzzleViolations(bo,i,j));
			if(condition) {
				++solutions[0];
				if(solutions[0]==1) {
					b.setSol(i,j,(bo[i][j].getValue()));
				}	
				else {
					b.setSol(i,j,0);
				}
			}	    
			bo[i][j].setValue(0);
	    }
	}
    
    /** @brief Metode que comprova si hi ha alguna restriccio que no es compleix
  	 * @pre  Donada una matriu de caselles bo i unes i j que son una posicio valida del tauler(0 <= i < rows, 0 <= j < columns)
  	 * @param bo = Matriu de caselles
     * @param i = Posicio fila de la casella
     * @param j = Posicio columna de la casella
     * @post Retorna cert si es compleixen les condicions
  	 */
    
	private boolean checkPuzzleViolations(Cell[][] bo, int i, int j) { //returns false if value placed in b[i][j] violates restrictions, else true
		boolean b=(checksequenciesincorrecteshor(bo,i,j)) && (checksequenciesincorrectesvert(bo,i,j)); 
		return b;
	}
	
	/** @brief Metode que comprova si hi ha alguna sequencia possible de valors horitzontal en la sequencia per a la casella negra esquerra
	 * @pre  Donada una matriu de caselles bo i unes i j que son una posicio valida del tauler(0 <= i < rows, 0 <= j < columns)
	 * @param bo = Matriu de caselles
	 * @param i = Posicio fila de la casella
	 * @param j = Posicio columna de la casella
	 * @post Retorna cert si hi ha una sequencia en la final el valor de la qual sumada es igual al de la casella negra esquerra 
   	 */	

	private boolean checksequenciesincorrecteshor(Cell[][] bo,int i,int j) {
		while(bo[i][j].isWhite()) --j;
		int total=bo[i][j].getHNum();
		if(total==0) return true;
		int mida=0;
		++j;
		PriorityQueue<Integer> cua=new PriorityQueue<Integer>();
        while(j<columns && bo[i][j].isWhite()) {
        	++mida;
        	int valor=bo[i][j].getValue();
        	if(valor!=0) {
        		total=total-valor;
        		cua.add(valor);
        	}
        	++j;
        }
        int index=0;
        int[] arr=new int[9-cua.size()];
        for(int ii=1;ii<10;++ii) {
        	if(!cua.contains(ii)) {
        		arr[index]=ii;
        		++index;
        	}
        }
        int r=mida-cua.size();
        int[] data=new int[r];
       return validvalue(arr,data,0,arr.length-1,0,mida-cua.size(),total);
       
    }
	
	/** @brief Metode que comprova si hi ha alguna sequencia possible de valors verticals en la sequencia per a la casella negra superior
	 * @pre  Donada una matriu de caselles bo i unes i j que son una posicio valida del tauler(0 <= i < rows, 0 <= j < columns)
	 * @param bo = Matriu de caselles
	 * @param i = Posicio fila de la casella
	 * @param j = Posicio columna de la casella
	 * @post Retorna cert si hi ha una sequencia en la columna el valor de la qual sumada es igual al de la casella negra superior 
	 */
	
	private boolean checksequenciesincorrectesvert(Cell[][] bo,int i,int j) {
		while(bo[i][j].isWhite()) --i;
		int total=bo[i][j].getVNum();
		if(total==0) return true;
		int mida=0;
		++i;
		PriorityQueue<Integer> cua=new PriorityQueue<Integer>();
		while(i<rows && bo[i][j].isWhite()) {
        	++mida;
        	int valor=bo[i][j].getValue();
        	if(valor!=0) {
        		total=total-valor;
        		cua.add(valor);
        		
        	}
        	++i;
        }
        int index=0;
        int[] arr=new int[9-cua.size()];
        for(int ii=1;ii<10;++ii) {
        	if(!cua.contains(ii)) {
        		arr[index]=ii;
        		++index;
        	}
        
        }
        int r=mida-cua.size();
        int[] data=new int[r];
        return validvalue(arr,data,0,arr.length-1,0,mida-cua.size(),total);
    }
	
	/** @brief Metode que comprova si com a minim hi ha combinacio de numeros que la suma del seu valor sigui igual a valor
	 * @pre  Donada una combinacio de valors en arr,un data amb les combinacions que hem provat fins al moment, uns start i end que son les posicions de arr que volem, un index que indica quantes posicions hem recorregut, una midaf que indica el tamany de la sequencia que volem i un valor que es el valor que volem aconseguir
	 * @param arr=Combinacio de valors
	 * @param data = Combinacions que hem provat fins al moment
	 * @param start = Posicio inicial de arr a recorrer
	 * @param end = Posicio final de arr a recorrer
	 * @param index = Posicio actual a consultar del vector arr que hem de recorrer
	 * @param midaf = Mida de la sequencia a comprobar
	 * @param valor = Valor de la sequencia a comprobar
	 * @post Retorna true si entre la posicio start i la posicio end de arr hi ha una combinacio que el valor de la suma sigui igual a valor
	 */

	private boolean validvalue(int[] arr,int[] data,int start,int end,int index,int midaf,int valor) {
		if(index==midaf)  {
			if(valor==0) {
				return true;
			}
			else return false;
		}
		for (int i=start; i<=end && end-i+1 >= midaf-index; i++) { 
	            data[index] = arr[i]; 
	            if(validvalue(arr, data, i+1, end, index+1, midaf,valor-arr[i])) return true; 
	    }
		return false;
	}
	
	/** @brief Metode que comprova si com a minim hi ha una solucio
	 * @pre Donades unes i j que son una posicio valida del tauler(0 <= i < rows, 0 <= j < columns)
	 * @param i = Posicio vertical de la cassella
	 * @param j = Posicio horitzontal de la cassella
	 * @post Retorna true si ha trobat una solucio
	 */
	
	private boolean atleastonesolution(int i,int j) {
    	Cell[][] copy=b.getCells();
    	int [] solutions = {0};
    	int[] pos= {i,j};
    	int casellesblanques=contabuides();
    	if(casellesblanques==0) return true;
    	findNextWhiteCell2(copy,pos);
    	int [] countot= {0};
    	solvegridone(copy,pos[0],pos[1],solutions,casellesblanques,countot);
    	int iteracions=0;
    	while(countot[0]>5000 && iteracions>0) {
    		copy=b.getCells();
    		--iteracions;
            findNextWhiteCell2(copy,pos);
    		countot[0]=0;
    		solvegridone(copy,pos[0],pos[1],solutions,casellesblanques,countot);
    	}
    	if(solutions[0]>=1) return true;
    	else return false;
	 }
	
	 /** @brief Metode que comprova si com a minim hi ha una solucio
	 * @pre  Donada una matriu de caselles, unes i j que son una posicio valida del tauler(0 <= i < rows, 0 <= j < columns),en solucions el nombre de solucions trobades fins al moment, en caselles les caselles trobades fins al moment i en count el nombre de crides realitzades a solvegridone recursivament
	 * @param bo=Matriu de caselles
	 * @param i = Posicio vertical de la cassella
     * @param j = Posicio horitzontal de la cassella
     * @param solutions = Nombre de solucions trobades
     * @param count = Nombre de crides a solvegridone realitzades
	 * @post Guarda en solutions el nombre de solucions possibles i en count el nombre de crides  a solvegridone que s'han realitzat
	 */
	
	 private void solvegridone(Cell[][] bo,int i,int j,int[] solutions,int caselles,int[] count) {
    	++count[0];
    	if(count[0]>5000) return;
    	if(solutions[0]>=1) return;
    	if(solutions[0]==0 && caselles>0) {
    		int actc=caselles-1;
	    	for(int k = 1; k <10; ++k) {
	        bo[i][j].setValue(k);
			boolean condition=(novalorsrep(bo,i,j) && checkPuzzleViolations(bo,i,j));
			if(condition) {
				if(actc==0) {
					++solutions[0];
		        }
				else {
				    int[] pos = {i,j};
				    findNextWhiteCell2(bo,pos);
				    solvegridone(bo,pos[0],pos[1],solutions,actc,count);
			    }
			}	    
			bo[i][j].setValue(0);
	       }
    	}
	 }
	 
	 /** @brief Metode que retorna cert si el valor de la casella blanca en la posicio i,j esta repetit en la fila o la columna
	  * @pre  La i la j son una posicio valida del tauler(0 <= i < rows, 0 <= j < columns)
	  * @param bo=Matriu de caselles
	  * @param i = Posicio vertical de la cassella
      * @param j = Posicio horitzontal de la cassella
      * @post Retorna cert si el valor en la posicio i,j no esta repetit en la fila i o la columna j de la matriu de caselles bo
      */  
	 
	 private boolean novalorsrep(Cell[][] bo,int i,int j) {
			int val = bo[i][j].getValue();
			
			//first check horizontal sequence
			int val2;
			int i1 = i;
			int j1 = j+1;
			while(j1 < columns && bo[i1][j1].isWhite()) { //horizontal right
				val2 = bo[i1][j1].getValue();
				if(val2== val) { return false; } //placed value already appears in sequence
				++j1;
			}
			j1 = j-1;
			while(bo[i1][j1].isWhite()) { //horizontal left
				val2 = bo[i1][j1].getValue();
				if(val2 == val) { return false; } //placed value already appears in sequence
				--j1;
			}
			i1 = i+1;
			j1 = j;
			while( i1 < rows && bo[i1][j1].isWhite()) { //vertical down
				val2 = bo[i1][j1].getValue();
				if(val2 == val) { return false; } //placed value already appears in sequence
				++i1;
			}
			i1 = i-1;
			while(bo[i1][j1].isWhite()) { //vertical up
				val2 = bo[i1][j1].getValue();
				if(val2 == val) { return false; } //placed value already appears in sequence
			    --i1;
			}
			return true;
	 }
		 
	 /** @brief Metode que retorna el valor horitzontal que ha de contenir una casella negra
	  * @pre  La i la j son una posicio valida del tauler(0 <= i < rows, 0 <= j < columns)
	  * @post Retorna el valor horitzontal que ha de tenir la casella negra
	  */ 
	 
	 private int valordre(int i,int j) {
		   int valor=0;
		   ++j;
		   while(j<columns && (b.getValue(i,j)!=-1)) {
			   int act=b.getValue(i,j);
			   if(act==0) return 0;
			   valor=valor+act;
			   ++j;
		   }
		   return valor;
	 }
	 
	  /** @brief Metode que retorna el valor vertical que ha de contenir una casella negra
	   * @pre  La i la j son una posicio valida del tauler(0 <= i < rows, 0 <= j < columns)
	   * @post Retorna el valor vertical que ha de tenir la casella negra
	   */
	 
	 private int valorup(int i,int j) {
		   int valor=0;
		   ++i;
		   while(i<rows && (b.getValue(i,j)!=-1)) {
			   int act=b.getValue(i,j);
			   if(act==0) return 0;
			   valor=valor+act;
			   ++i;
		   }
		   return valor;
	 }
	 
	  /** @brief Metode per contar el nombre de caselles blanques amb valor==0 que te el tauler
	   * @pre  
	   * @post Retorna el nombre de caselles blanques amb valor==0 que te el tauler
	   */ 
	 
	 private int contabuides() {
    	int count=0;
    	for(int i=1;i<rows;++i) {
    		for(int j=1;j<columns;++j) {
    			if((b.getValue(i,j)!=-1) && b.getValue(i,j)==0) ++count;
    		}
    	}
    	return count;
    }
		    
	/** @brief Metode per a retornar la seguent casella blanca lliure en el tauler
	 * @pre Donada una matriu de caselles amb com a minim una casella blanca amb valor==0 i una posicio pos del tauler associat (0 <= pos[0] < rows, 0 <= pos[1] < columns), 
	 * @post Guarda en pos la seguent posicio amb una casella blanca buida
	 * @param bo= Matriu de caselles
	 * @param pos = Posicio horitzontal de la casella inicial
	 * @throws ExceptionOutOfRangePosition
	 */
	 
	 private void findNextWhiteCell2(Cell[][] bo, int[] pos) { //looks for next available white cell
    	if(pos[1] < (columns-1)) {
    		++pos[1];
    	}
    	else if(pos[0]<(rows-1)){
    		pos[1] = 0;
    		++pos[0];
    	}
    	else {
    		pos[0]=1;
    		pos[1]=0;
    	}
    	while(!bo[pos[0]][pos[1]].isWhite() || bo[pos[0]][pos[1]].getValue()!=0) {
    	   if(pos[1] < (columns-1)) {
    			++pos[1];
    		} else if(pos[0]<(rows-1)) {
    			++pos[0];
    			pos[1] = 0;
    		}
    		else {
    			pos[0]=1;
    			pos[1]=0;
    		}
    	}
    }		
}
