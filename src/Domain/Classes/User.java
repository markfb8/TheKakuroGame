/** @file User.java */

package Domain.Classes;

import java.io.Serializable;

/** @class User
 * @brief Representa els usuaris i les operacions associades a aquests 
 * 
 */

public class User implements Serializable {

//attributes

	/**
	 * 
	 */
	private static final long serialVersionUID = -407777798404819167L;
	/** @brief Representa el nom d usuari  */
	private String username;
	/** @brief Representa la password */
	private String password;
	
//constructors
	
	/** @brief Constructora per defecte
	 * @pre cert
	 * @post El resultat es un usuari amb els seus atributs buits
	 */
	
	public User() {
		username = null;
		password = null;
	}
	
	/** @brief Constructora amb parametres
	 * @pre Donada una string u que indica el username, i una string p que indica el password
	 * @post El resultat es un usuaria amb username u i password p
	 * @param u
	 * @param p
	 */
	
	public User(String u, String p) {
		username = u;
		password = p;
	}
	
//getters
	
	/** @brief Consultora del username
	 * @pre cert
	 * @post Indica el username del usuari
	 */
	
	public String getUsername() { return username; }
	
	/** @brief Consultora del password
	 * @pre cert
	 * @post Indica el password del usuari
	 */
	
	public String getPassword() { return password; }
	
//setters
	
	/** @brief Modificadora del password
	 * @pre Donada una string newPass que indica la contrassenya
	 * @post newPass es la nova contrassenya
	 * @param newPass
	 */
	
	public void setPassword(String newPass) { password = newPass; }
	
}
