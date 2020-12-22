/** @file CtrlDomain.java */

package Presentation;

import Domain.CtrlDomain;
import Domain.Controllers.CtrlGame;
import Domain.Controllers.CtrlRanking;
import Exceptions.ExceptionShortPassword;
import Presentation.LoginScreen.LoginScreen;

/** @class CtrlPresentation
 * @brief Aquesta classe representa el controlador de la capa de presentacio. 
 * Aquest controlador es comunicara amb el del domini per a que l'usuari pugui accedir a les funcionalitats del sistema
 *
 */

public class CtrlPresentation {

//attributes
	
	/** @brief Instancia de la classe CtrlDomain */
	private CtrlDomain CD;
	
//creators
	
	/** @brief Constructora per defecte
	 * @pre cert
	 * @post Es genera una instancia de la classe CtrlPresentation amb la instancia de CtrlDomain associada.
	 */
	@SuppressWarnings("unused")
	public CtrlPresentation() {
		CD = new CtrlDomain();
		LoginScreen MS = new LoginScreen(this);
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
		return CD.checkPassword(u,p);
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
		return CD.changePassword(u,oldPass,newPass);
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
		return CD.addUser(u, p);
	}
	
//game methods
	
	/**@brief Metode per crear una instancia del CtrlGame
	 * @pre L usuari identificat per username existeix
	 * @param username = String identificador d'un usuari
	 * @post es retorna la instancia de CtrlGame associada al usuari identificat per username
	 */
	
	public CtrlGame createGame(String username) {
		return CD.createGame(username);
	}
	
//ranking methods
	
	/**@brief Metode per crear una instancia del CtrlRanking
	 * @pre L usuari identificat per username existeix
	 * @post es retorna la instancia de CtrlRanking associada al usuari identificat per username
	 */
	
	public CtrlRanking createRanking() {
		return CD.createRanking();
	}
	
}
