/** @file CtrlRanking.java */

package Domain.Controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import Domain.Classes.Game;
import Persistence.CtrlPersistenceRankings;

/** @class CtrlRanking
 *  @brief Aquesta classe representa el controlador de la funcionalitat dels Rankings i s'encarrega de gestionar els rankings general i de cada tauler mantenint una ordenacio per punts.
 * 
 *
 */

public class CtrlRanking {
	
//attributes
	
	/** @brief Ranking general del sistema, compren tots els usuaris que tenen una partida finalitzada com a minim */
	private ArrayList<ArrayList<String> > RankingGeneral =  new ArrayList<ArrayList<String> >();
	/** @brief Ranking d un tauler concret del sistema, compren tots els usuaris que han finalitzat una partida en aquest tauler */
	private ArrayList<ArrayList<String> > RankingBoard =  new ArrayList<ArrayList<String> >();
	/** @brief Instancia de la classe CtrlPersistenceRanking */
	private CtrlPersistenceRankings CP;
	
//comparators
	
	private class CustomComparator implements Comparator<ArrayList<String>>{
		public int compare(ArrayList<String> r1,ArrayList<String >r2){
			Integer r1Score = Integer.parseInt(r1.get(0));
			Integer r2Score = Integer.parseInt(r2.get(0));
			return r2Score.compareTo(r1Score);
		}
	}
	
//constructor
	
	/**@brief Constructora per defecte
	 * @pre cert
	 * @post Es genera una instancia de CtrlRanking 
	 * 	
	 */
	
	public CtrlRanking() {
		CP = new CtrlPersistenceRankings();
	}
	
//methods
	
	/**@brief Metode que actualitza els rankings d un tauler i els rankings generals de puntuacio
	 * @pre Donats un game g i un string username que identifica un usuari del sistema
	 * @post El ranking del tauler que s ha jugat en el game g s actualitza amb la puntuacio del usuari username. El ranking general del usuari username es troba actualitzat amb la seva nova puntuacio
	 * @param g =  Instancia de la classe game
	 * @param idB = identificador del tauler associat a la partida
	 * @param username = String que identifica un usuari del sistema
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	
	public void addToRanking(Game g, String idB, String username) throws ClassNotFoundException, IOException {
		String id = idB;
		int score=g.getScore();
		String scoreS = Integer.toString(score);
		
		ArrayList<String> dades = new ArrayList<String>();
		dades.add(scoreS);
		dades.add(username);
		
		RankingBoard = CP.loadBoardRanking(id);
		RankingGeneral = CP.loadGeneralRanking();
		
		if(RankingBoard == null) {
			RankingBoard = new ArrayList<ArrayList<String>>();
			RankingBoard.add(0,dades);
		}
		else {
			RankingBoard.add(0,dades);
			Collections.sort(RankingBoard, new CustomComparator());
		}
		
		CP.storeBoardRanking(id, RankingBoard);
		
		if(RankingGeneral == null) {
			RankingGeneral = new ArrayList<ArrayList<String>>();
			RankingGeneral.add(0,dades);
		} else {
			boolean trobat = false;
			for (int i = 0; i < RankingGeneral.size() && !trobat; i++) {
				String user=RankingGeneral.get(i).get(1);
				
				if(user.equals(username)) {
					int scoreActu= Integer.parseInt((RankingGeneral.get(i).get(0)));
					score += scoreActu;
					RankingGeneral.remove(i);
					dades.set(0, Integer.toString(score));
					trobat = true;
				}
	        }
			RankingGeneral.add(0, dades);	
			Collections.sort(RankingGeneral, new CustomComparator());
		}
		
		CP.storeGeneralRanking(RankingGeneral);
	}
	
//getters
	
	/**@brief Metode que retorna el ranking general de usuaris
	 * @pre cert
	 * @post Es retorna una llista de ArrayList de strings que conte el ranking de puntuacio general de usuaris
	 * @return un ArrayList de ArrayList de String amb la informacio corresponent al ranking general
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	
	public ArrayList<ArrayList<String>> getGeneralRanking() throws ClassNotFoundException, IOException {
		return CP.loadGeneralRanking();
	} 
	
	/** @brief Metode que retorna el ranking de un tauler en concret
	 * @pre Donat un string id que identifica un tauler i un string level que indica el seu nivell de dificultat
	 * @post Es retorna una llista de ArrayList de strings que conte el ranking del tauler id
	 * @param id = String que indica el id de un tauler
	 * @return un ArrayList de ArrayList de String amb la informacio corresponent al ranking del tauler indicat (si existeix)
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	
	public ArrayList<ArrayList<String> > getBoardRanking(String id) throws ClassNotFoundException, IOException{
		return CP.loadBoardRanking(id);
	}
	
}
