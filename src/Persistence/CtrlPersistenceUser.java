/** @file CtrlPersistenceUser.java */

package Persistence;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Paths;
import java.util.ArrayList;

import Domain.Classes.User;

/** @class CtrlPersistenceUser
 * @brief Aquesta classe representa el controlador de la capa de persistencia dels usuaris. 
 * Aquest controlador es comunicara amb el controlador d'usuari per a proporcionarli les dades emmagatzemades en fitxers
 *
 */

public class CtrlPersistenceUser {

//attributes
	
	/** @brief Path on es troba el fitxer on guardar els usuaris*/
	private final static String path = "data/users.bin";
	
//constructors
	
	/** @brief Constructora per defecte
	 * @pre L'usuari amb nom username existeix
	 * @post Es genera una instancia de la classe CtrlPersistenceUser.
	 */
	
	public CtrlPersistenceUser() {}

//methods
	
	/** @brief Consultora de l'arxiu situat al directori identificat per path
	 * @pre Cert
	 * @post Si existeix el fitxer indicat es llegeix
	 * @return el text del fitxer en un ArrayList de User
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
	
	@SuppressWarnings("unchecked")
	public ArrayList<User> load() throws IOException, ClassNotFoundException {
		File dataDir = new File(Paths.get("data/").toAbsolutePath().toString());
		if(!dataDir.exists()) dataDir.mkdir();
		File f = new File(Paths.get(path).toAbsolutePath().toString());
		if(!f.exists()) {
			f.createNewFile();
			ArrayList<User> users = new ArrayList<User>();
			users.add(new User("console","827!*16%1"));
			store(users);
		}
		FileInputStream file = new FileInputStream(Paths.get(path).toAbsolutePath().toString());
		ObjectInputStream in = new ObjectInputStream(file);
		return (ArrayList<User>) in.readObject();
	}
	
	/** @brief Modificadora de l'arxiu situat al directori identificat per path
	 * @pre Donat un ArrayList de User
	 * @post Si existeix el fitxer indicat s'omple amb la informacio indicada
	 * @param info = elements a escriure al fitxer
	 */
	
	public void store(ArrayList<User> users) throws IOException {
		FileOutputStream file = new FileOutputStream(Paths.get(path).toAbsolutePath().toString());
		ObjectOutputStream out = new ObjectOutputStream(file);
		out.writeObject(users);
	}

}
