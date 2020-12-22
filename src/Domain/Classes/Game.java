/** @file Game.java */

package Domain.Classes;

import java.io.Serializable;

/** @class Game
 * @brief Aquesta classe representa les partides, les quals tenen una instància de Board i User associades. 
 * A part tenim l'atribut temps que emmagatzema el temps que l'usuari porta jugant al tauler i l'atribut hints que actua com a contador de les pistes demanades-
 * 
 */

public class Game implements Serializable {
	
//attributes
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2989581925801099403L;
	/** @brief Tauler associat a la partida */
	private Board board;
	/** @brief Temps que l'usuari porta jugant al kakuro */
	private long time;
	/**@brief Nombre de ajudes que l'usuari ha demanat */
	private int hints;
	/**@brief Puntuacio del tauler */
	private int score;
	
//constructors
	
	/** @brief Constructora per defecte
	 * @pre cert
	 * @post Es genera una instancia de la classe game amb els atributs user i board amb valor null i els atributs time i hints inicialitzats a 0
	 */
	
	public Game() {
		board = null;
		time = 0;
		hints = 0;
		score = 0;
	}
	
//getters
	
	/**@brief Consultora que retorna el tauler
	 * @pre cert
	 * @post Es retorna la instancia de Board associada a la partida
	 */
	
	public Board getBoard() { return board; }
	
	/** @brief Consultora que retorna el temps
	 * @pre cert
	 * @post Es retorna el temps que porta l'usuari jugant
	 */
	
	public long getTime() { return time; }
	
	/** @brief Consultora de les pistes emprades
	 * @pre cert
	 * @post Es retorna el nombre de pistes que l'usuari ha demanat
	 */
	
	public int getHints() { return hints; }
	
	/** @brief Consultora de la puntuacio
	 * @pre cert
	 * @post Es retorna la puntuacio de l'usuari si ha finalitzat el tauler, altrament 0
	 */
	
	public int getScore() { return score; }

//setters
	
	/** @brief Modificadora del atribut board
	 * @pre Donada una instancia de la classe Board
	 * @post Es modifica el valor de l atribut board per el parametre
	 * @param b = Instancia de la classe Board
	 */
	
	public void setBoard(Board b) { board = b; }
	
	
	/** @brief Modificadora de l'atribut que guarda el temps
	 * @pre  Donat un long t > time
	 * @post Es modifica l'atribut temps pel valor passat per parametre
	 * @param t = Temps que porta jugant l'usuari
	 */
	public void setTime(long t) { time += t; }
	
	/** @brief Modificadora de l'atribut que guarda el nombre d'ajudes emprades
	 * @pre Donat un enter h > hints
	 * @post Es modifica l'atribut hints pel valor passat per parametre 
	 * @param h = Ajudes que ha fet servir l'usuari 
	 */
	
	public void setHints(int h) { hints = h; }
	
	/** @brief Modificadora de la puntuacio
	 * @pre Donat un enter s > 0
	 * @post Es modifica l'atribut score pel valor passat per parametre 
	 * @param s = puntuacio a la partida
	 */
	
	public void setScore(int s) { score = s; }
	
}
