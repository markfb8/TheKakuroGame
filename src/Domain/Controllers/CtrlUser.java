/** @file CtrlUser.java */

package Domain.Controllers;

import java.util.ArrayList;

import Domain.Classes.User;
import Exceptions.ExceptionShortPassword;
import Persistence.CtrlPersistenceUser;

/** @class CtrlUser
 * @brief Aquesta classe representa el controlador de la classe User i s'encarregara de gestionar la informacio referent als usuaris del sistema.
 * 
 *
 */

public class CtrlUser {

//attributes
	
	/** @brief Instancia de la classe CtrlPersistenceUser */
	private static CtrlPersistenceUser CP;
	/** @brief ArrayList d usuaris que representa els usuaris que hi ha al sistema ara mateix */
	private static ArrayList<User> users;
	
//constructors
	
	/**@brief Constructora per defecte
	 * @pre cert
	 * @pro Es genera una instancia de Ctrluser amb una instancia de CtrlPersistenceUser associada i una ArrayList de users amb els usuaris emmagatzemats al sistema.
	 */
	
	public CtrlUser() {
		CP = new CtrlPersistenceUser();
		users = new ArrayList<User>();
		loadUsers();
	}
	
//getters
	
	/** @brief Metode que obte un usuari
	 * @pre Donada una string u que indica el nom d un usuari
	 * @post Si existeix, es retorna la instancia d usuari que te nom u
	 * @param u = Username de l'usuari
	 * @return l'usuari si existeix, else null
	 */
	
	public User getUser(String u) {
		for(User user : users) {
			if((user.getUsername()).equals(u)) return user;
		}
		return null;
	}
	
//methods
	
	/** @throws Exception 
	 * @brief Metode que carrega els usuaris guardats a l arraylist users 
	 * @pre cert
	 * @post L arraylist de users conte la informacio emmagatzemada dels usuaris
	 */
	
	private void loadUsers() {
		try {
			users = CP.load();
		} catch (Exception e) {
			System.out.println("An error occured when loading users");
		}
	}
	
	/** @brief Metode que guarda la informacio dels usuaris que tenim a l arraylist
	 * @pre cert
	 * @post La informacio emmagatzemada dels usuaris s ha actualitzat
	 */
	
	private void storeUsers() {
		try {
			CP.store(users);
		} catch (Exception e) {
			System.out.println("An error occurred when storing users");
		}
	}
	
	/** @brief Metode que afegeix un nou usuari al sistema
	 * @pre Donada una string u que representa el nom del usuari i una string p que representa el password
	 * @post S afegeix un nou usuari al sistema amb nom u i password p
	 * @param u = Username de l'usuari
	 * @param p = contrasenya de l'usuari
	 * @return true si s'ha afegit, false si no
	 * @throws ExceptionShortPassword 
	 */
	
	public boolean addUser(String u, String p) throws ExceptionShortPassword {
		if(p.length()<8) throw new ExceptionShortPassword();
		User newuser = getUser(u);
		if(newuser != null) return false;
		newuser = new User(u,p);
		users.add(newuser);
		storeUsers();
		return true;
	}
	
	/** @brief Metode que comprova la contrasenya d un usuari
	 * @pre Donada una string u que indica el nom d un usuari i una string p que indica un password
	 * @post Es retorna true si la password del usuari amb nom u coincideix amb la password p
	 * @param u =  Username de l'usuari
	 * @param p = contrasenya de l'usuari
	 * @return true si la contrasenya coincideix, false altrament
	 */
	
	public boolean checkPassword(String u, String p) {
		for(User user : users) {
			if((user.getUsername()).equals(u)) {
				if((user.getPassword().equals(p))) return true;
			}
		}
		return false;
	}
	
	/** @brief Metode que canvia el password d un usuari
	 * @pre Donada una string u que representa el nom d un usuari i unes string s oldPass i newPass que represente la contrasenya antiga i nova, respectivament
	 * @post Si la contrasenya antiga del usuari u coincideix amb oldPass, es modifica la seva contrasenya amb la contrasenya newPass. Si es modifica es retorna true.
	 * @param u = Username de l'usuari
	 * @param oldPass = contrasenya antiga de l'usuari
	 * @param newPass = contrasenya nova de l'usuari
	 * @return true si la contrasenya s'ha canviat, false altrament
	 * @throws ExceptionShortPassword 
	 */
	
	public boolean changePassword(String u, String oldPass, String newPass) throws ExceptionShortPassword {
		User user = getUser(u);
		if(user == null) return false;
		if(!oldPass.equals(user.getPassword())) return false;
		if(newPass.length()<8) throw new ExceptionShortPassword();
		users.remove(user);
		user.setPassword(newPass);
		users.add(user);
		storeUsers();
		return true;
	}

}
