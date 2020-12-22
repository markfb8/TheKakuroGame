/** @mainpage
 * 
 * 	En el fitxer KakuroMain.java es troba el programa principal.
 */

/** @file KakuroMain.java
 * @brief Programa principal
 * <em>Aplicacio per jugar a kakuros amb diverses funcionalitats</em>.
 */

import Presentation.CtrlPresentation;

public class KakuroMain {
	@SuppressWarnings("unused")
	private static CtrlPresentation CP;
	
	public static void main(String[] args) {
		CP = new CtrlPresentation();
	}
}