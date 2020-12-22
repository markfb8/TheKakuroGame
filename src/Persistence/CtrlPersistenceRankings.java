/** @file CtrlPersistenceRankings.java */

package Persistence;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Paths;
import java.util.ArrayList;

/** @class CtrlPersistenceRankings
 * @brief Aquesta classe representa el controlador de la capa de persistencia dels Rankings. 
 * Aquest controlador es comunicara amb altres controladors de la capa de domini per a proporcionarlos les dades emmagatzemades en fitxers
 *
 */

public class CtrlPersistenceRankings {
	
//attributes
	
	/** @brief Path on es troba el directori on guardar els rankings*/
	private static final String path = "data/rankings";
	/** @brief Path on es troba el directori on guardar el ranking de cada tauler*/
	private static final String pathBoards = "data/rankings/boards/";
	/** @brief Path on es troba el directori on guardar el ranking general*/
	private static final String pathGeneral = "data/rankings/general.bin";
	
//constructors
		
	/** @brief Constructora per defecte
	 * @pre cert
	 * @post Es genera una instancia de la classe CtrlPersistenceRankings.
	 */
	
	public CtrlPersistenceRankings() {};
		
//methods
		
	/** @brief Metode que carrega el ranking d un tauler en una ArrayList de Arraylist de Strings
	 * @pre Donat un identificador d un tauler i el seu nivell
	 * @post Si existeix el fitxer indicat es llegeix el seu contingut, sino es crea un fitxer nou.
	 * @param id = identificador del tauler
	 * @param level = nivell del tauler indicat
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
	
	@SuppressWarnings("unchecked")
	public ArrayList<ArrayList<String>> loadBoardRanking(String id) throws ClassNotFoundException, IOException {
		File rankDir = new File(Paths.get(path).toAbsolutePath().toString());
		if(!rankDir.exists()) rankDir.mkdir();
		File boardsDir = new File(Paths.get(pathBoards).toAbsolutePath().toString());
		if(!boardsDir.exists()) boardsDir.mkdir();
		File rank = new File(Paths.get(pathBoards+id+".bin").toAbsolutePath().toString());
		if(!rank.exists()) return null;
		FileInputStream file = new FileInputStream(Paths.get(pathBoards+id+".bin").toAbsolutePath().toString());
		ObjectInputStream in = new ObjectInputStream(file);
		ArrayList<ArrayList<String>> arr = (ArrayList<ArrayList<String>>) in.readObject();
		in.close();
		return arr;
	}
	
	/** @brief Metode que emmagatzema el ranking d un tauler
	 * @pre Donat un string id i un ArrayList de Arraylist de Strings info
	 * @post Si existeix el fitxer  indicat  al directori pathBoards, s omple amb la informacio indicada per info
	 * @param id = identificador del tauler
	 * @param level = nivell del tauler indicat
	 * @param info = elements a escriure al fitxer
	 * @throws IOException 
	 */
	
	public void storeBoardRanking(String id, ArrayList<ArrayList<String>> info) throws IOException {
		FileOutputStream file = new FileOutputStream(Paths.get(pathBoards+id+".bin").toAbsolutePath().toString());
		ObjectOutputStream out = new ObjectOutputStream(file);
		out.writeObject(info);
		out.close();
	}
	
	/**@brief Metode que carrega el ranking general en una ArrayList de Arraylist de Strings
	 * @pre Cert
	 * @post Si existeix el fitxer indicat es llegeix el seu contingut, sino es crea un fitxer nou.
	 * @return un ArrayList de ArrayList de String amb el ranking general
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
	
	@SuppressWarnings("unchecked")
	public ArrayList<ArrayList<String>> loadGeneralRanking() throws ClassNotFoundException, IOException {
		File rankDir = new File(Paths.get(path).toAbsolutePath().toString());
		if(!rankDir.exists()) rankDir.mkdir();
		File generalDir = new File(Paths.get(pathGeneral).toAbsolutePath().toString());
		if(!generalDir.exists()) return null;
		FileInputStream file = new FileInputStream(Paths.get(pathGeneral).toAbsolutePath().toString());
		ObjectInputStream in = new ObjectInputStream(file);
		ArrayList<ArrayList<String>> arr = (ArrayList<ArrayList<String>>) in.readObject();
		in.close();
		return arr;
	}
	
	/** @brief Metode que guarda la informacio pasada per l arraylist en un arxiu identificat amb user
	 * @pre Donat un string user que identifica un usuari del sistema i un ArrayList de Strings info
	 * @post Si existeix el fitxer indicat al directori pathUsers, s omple amb la informacio indicada per info
	 * @param user
	 * @param info
	 * @throws IOException
	 */
	
	public void storeGeneralRanking(ArrayList<ArrayList<String>> info) throws IOException {
		FileOutputStream file = new FileOutputStream(Paths.get(pathGeneral).toAbsolutePath().toString());
		ObjectOutputStream out = new ObjectOutputStream(file);
		out.writeObject(info);
		out.close();
	}
}