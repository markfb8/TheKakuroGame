/** @file CtrlDomain.java */

package Domain;

import Domain.Controllers.CtrlGame;
import Domain.Controllers.CtrlRanking;
import Domain.Controllers.CtrlUser;
import Exceptions.ExceptionShortPassword;

/** @class CtrlDomain
 * @brief Aquesta classe representa el controlador de la capa de Domini. 
 * Aquest controlador es comunicara amb els altres per tal de proporcionar les funcionalitats principals de la capa de Domini.
 *
 */

public class CtrlDomain {
	
//attributes
	
	/** @brief Instancia de la classe CtrlUser */
	private CtrlUser CU;
	/** @brief Instancia de la classe CtrlGame */
	private CtrlGame CG;
	/** @brief Instancia de la classe CtrlRanking */
	private CtrlRanking CRK;
	
//constructors
	
	/** @brief Constructora per defecte
	 * @pre cert
	 * @post Es genera una instancia de la classe CtrlDomain amb les instancies de CtrlUser i CtrlGame associades.
	 */
	
	public CtrlDomain() {
		CU = new CtrlUser();
		CG = null;
	}
	
//user methods
	
	/** @brief Consultora del password d'un usuari
	 * @pre Donat un string u i un string p.
	 * @post Si existeix l'usuari amb nom d'usuari u, es retorna true si la seva contrassenya coincideix amb l'string p
	 * @param u = Username de l usuari
	 * @param p = Password de l usuari
	 * @return true si els passwords coincideixen, false altrament
	 */
	
	public boolean checkPassword(String u, String p) {
		return CU.checkPassword(u,p);
	}
	
	/** @brief Modificadora del password d'un usuari
	 * @pre Donat un string u, un string oldPass i un string newPass
	 * @post Si oldPass coincideix amb la contrassenya antiga de l'usuari u, es retorna true si es modifica la contrassenya.
	 * @param u = Username de l'usuari
	 * @param oldPass = Contrassenya antiga de l'usuari 
	 * @param newPass = Contrassenya nova de l'usuari 
	 * @return true si s'ha canviat el password, false altrament
	 * @throws ExceptionShortPassword 
	 */
	
	public boolean changePassword(String u, String oldPass, String newPass) throws ExceptionShortPassword {
		return CU.changePassword(u,oldPass,newPass);
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
		return CU.addUser(u, p);
	}

//game methods
	
	/** @brief Metode per crear una instancia del controlador de partida
	 * @pre L'usuari identificat per username existeix
	 * @post Es crea una instancia del controlador de partida amb cap joc associat
	 * @param username = nom de l'usuari
	 * @return el controlador de partida
	 */
	
	public CtrlGame createGame(String username) {
		CG = new CtrlGame(username);
		return CG;
	}
	
	/**@brief Metode per crear una instancia del CtrlRanking
	 * @pre L usuari identificat per username existeix
	 * @post es retorna la instancia de CtrlRanking associada al usuari identificat per username
	 */
	
	public CtrlRanking createRanking() {
		CRK = new CtrlRanking();
		return CRK;
	}
	
}
