/** @file CtrlPersistenceGame.java */

package Persistence;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Paths;
import java.util.ArrayList;

import Domain.Classes.Game;

/** @class CtrlPersistenceGame
 * @brief Aquesta classe representa el controlador de la capa de persistencia de Game. 
 * Aquest controlador es comunicara amb el controlador de Game per a proporcionarli les dades emmagatzemades en fitxers
 *
 */

public class CtrlPersistenceGame {
	
//attributes
	
	/** @brief String que representa el path al directori que conte les partides guardades */
	private final static String path = "data/games/";
	
//constructors
	
	/** @brief Constructora per defecte
	 * @pre cert
	 * @post Es genera una instancia de la classe CtrlPersistenceGame.
	 */
	
	public CtrlPersistenceGame() {}

//methods
	
	/** @brief Metode que llista els jocs guardats de l'usuari
	 * @pre L usuari identificat per username existeix
	 * @post Es retorna una llista de strings que conte el nom de totes les partides guardades de l'usuari
	 * @return una llista amb les partides seleccionables
	 */
	
	public ArrayList<String> getSaved(String username) {
		ArrayList<String> list = new ArrayList<String>();
		File gamesDir = new File(Paths.get(path).toAbsolutePath().toString());
		if(!gamesDir.exists()) gamesDir.mkdir();
		File userDir = new File(Paths.get(path+username+"/").toAbsolutePath().toString());
		if(!userDir.exists()) userDir.mkdir();
		File savedDir = new File(Paths.get(path+username+"/saved/").toAbsolutePath().toString());
		if(!savedDir.exists()) savedDir.mkdir();
		String files[] = savedDir.list();
		boolean empty = true;
		if(files != null) {
			empty = false;
			for (String file : files) {
				list.add(file.substring(0, file.lastIndexOf('.')));
			}
		}
		if(empty) return null;
		return list;
	}
	
	/** @brief Metode que llista els jocs finalitzats de l'usuari
	 * @pre L usuari identificat per username existeix
	 * @post Es retorna una llista de strings que conte el nom de totes les partides finalitzades de l'usuari
	 * @return una llista amb les partides finalitzades
	 */
	
	public ArrayList<String> getCompleted(String username) {
		ArrayList<String> list = new ArrayList<String>();
		File gamesDir = new File(Paths.get(path).toAbsolutePath().toString());
		if(!gamesDir.exists()) gamesDir.mkdir();
		File userDir = new File(Paths.get(path+username+"/").toAbsolutePath().toString());
		if(!userDir.exists()) userDir.mkdir();
		File completedDir = new File(Paths.get(path+username+"/completed/").toAbsolutePath().toString());
		if(!completedDir.exists()) completedDir.mkdir();
		String files2[] = completedDir.list();
		boolean empty = true;
		if(files2 != null) {
			empty = false;
			for (String file : files2) {
				list.add(file.substring(0, file.lastIndexOf('.')));
			}
		}
		if(empty) return null;
		return list;
	}
	
	/** @brief Consultora d una partida emmagatzemada en un fitxer
	 * @pre Cert
	 * @post Si existeix la partida indicada es llegeix
	 * @return Una instancia de la classe Game
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
	
	public Game getGame(String username, String id) throws IOException, ClassNotFoundException {
		FileInputStream file = new FileInputStream(Paths.get(path+username+"/saved/"+id+".bin").toAbsolutePath().toString());
		ObjectInputStream in = new ObjectInputStream(file);
		Game g = (Game) in.readObject();
		in.close();
		return g;
	}
	
	/** @brief Consultora d una partida finalitzada emmagatzemada en un fitxer
	 * @pre Cert
	 * @post Si existeix la partida finalitzada indicada es llegeix
	 * @return Una instancia de la classe Game
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
	
	public Game getCompletedGame(String username, String id) throws IOException, ClassNotFoundException {
		FileInputStream file = new FileInputStream(Paths.get(path+username+"/completed/"+id+".bin").toAbsolutePath().toString());
		ObjectInputStream in = new ObjectInputStream(file);
		Game g = (Game) in.readObject();
		in.close();
		return g;
	}
	
	/** @brief Metode que emmagatzema la partida donada en un fitxer
	 * @pre Donada una partida valida
	 * @post La partida donada s ha emmagatzemat
	 * @param g = la partida a emmagatzemar
	 * @throws IOException 
	 */
	
	public void storeGame(String username, Game g, String id) throws IOException {
		File gamesDir = new File(Paths.get(path).toAbsolutePath().toString());
		if(!gamesDir.exists()) gamesDir.mkdir();
		File userDir = new File(Paths.get(path+username+"/").toAbsolutePath().toString());
		if(!userDir.exists()) userDir.mkdir();
		File savedDir = new File(Paths.get(path+username+"/saved/").toAbsolutePath().toString());
		if(!savedDir.exists()) savedDir.mkdir();
		FileOutputStream file = new FileOutputStream(Paths.get(path+username+"/saved/"+id+".bin").toAbsolutePath().toString());
		ObjectOutputStream out = new ObjectOutputStream(file);
		out.writeObject(g);
		out.close();
	}
	
	/** @brief Metode que emmagatzema la partida finalitzada donada en un fitxer
	 * @pre Donada una partida finalitzada
	 * @post La partida finalitzada donada s ha emmagatzemat
	 * @param g = la partida finalitzada a emmagatzemar
	 * @throws IOException 
	 */
	
	public void storeCompletedGame(String username, Game g, String id) throws IOException {
		File game = new File(Paths.get(path+username+"/saved/"+id+".bin").toAbsolutePath().toString());
		if(game.exists()) game.delete();
		File completedDir = new File(Paths.get(path+username+"/completed/").toAbsolutePath().toString());
		if(!completedDir.exists()) completedDir.mkdir();
		FileOutputStream file = new FileOutputStream(Paths.get(path+username+"/completed/"+id+".bin").toAbsolutePath().toString());
		ObjectOutputStream out = new ObjectOutputStream(file);
		out.writeObject(g);
		out.close();
	}
	
}
