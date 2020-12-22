/** @file CtrlPersistenceBoard.java */

package Persistence;

import java.io.BufferedInputStream;

/** @class CtrlPersistenceBoard
 * @brief Aquesta classe representa el controlador de la capa de persistencia de Board. 
 * Aquest controlador es comunicara amb el controlador de Board per a proporcionarli les dades emmagatzemades en fitxers
 *
 */

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Paths;
import java.util.ArrayList;

import Domain.Classes.Board;
import Exceptions.ExceptionExistingBoard;

public class CtrlPersistenceBoard {

//attributes
	
	/** @brief String que representa el path al directori que conte el repositori de taulers */
	private final static String path = "data/boards/";
	
//constructors
	
	/** @brief Constructora per defecte
	 * @pre cert
	 * @post Es genera una instancia de la classe CtrlPersistenceBoard.
	 */
	public CtrlPersistenceBoard() {}
	
//methods
	
	/** @brief Consultora d un tauler emmagatzemat en un fitxer
	 * @pre Cert
	 * @post Si existeix el tauler indicat es llegeix
	 * @return Una instancia de la classe Board
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */

	public Board getBoard(String id, String level) throws IOException, ClassNotFoundException {
		FileInputStream file = new FileInputStream(Paths.get(path+level+"/"+id+".bin").toAbsolutePath().toString());
		ObjectInputStream in = new ObjectInputStream(file);
		return (Board) in.readObject();
	}
	
	/** @brief Metode que comprova que el tauler donat no estigui ja emmagatzemat i si no ho esta emmagatzema el tauler donat en un fitxer
	 * @pre Donat un Board valid
	 * @post El tauler donat s ha emmagatzemat si no existia ja
	 * @param b = el tauler a emmagatzemar
	 * @param idNew = identificador del tauler a emmagatzemar
	 * @throws IOException 
	 * @throws ExceptionExistingBoard 
	 */
	
	@SuppressWarnings("resource")
	public void storeBoard(Board b,String idNew) throws IOException, ExceptionExistingBoard {
		File boardDir = new File(Paths.get(path).toAbsolutePath().toString());
		if(!boardDir.exists()) boardDir.mkdir();
		File levelDir = new File(Paths.get(path+b.getLevel()+"/").toAbsolutePath().toString());
		if(!levelDir.exists()) levelDir.mkdir();
		
		FileOutputStream fileTemp = new FileOutputStream(Paths.get(path+"/temp.bin").toAbsolutePath().toString());
		ObjectOutputStream outTemp = new ObjectOutputStream(fileTemp);
		outTemp.writeObject(b);
		outTemp.close();
		
		ArrayList<String> ids = getList(b.getLevel());
		File proposed = new File(Paths.get(path+"/temp.bin").toAbsolutePath().toString());
		if(ids != null && ids.size() != 0) {
			for(String id : ids) {
				File existing = new File(Paths.get(path+b.getLevel()+"/"+id+".bin").toAbsolutePath().toString());
				InputStream in1 = new BufferedInputStream(new FileInputStream(existing));
				InputStream in2 = new BufferedInputStream(new FileInputStream(proposed));
				int value1, value2;
				boolean diff = false;
				do {
					value1 = in1.read();
					value2 = in2.read();
					if(value1 != value2) diff = true;
				} while(value1 >= 0);
				if(!diff) throw new ExceptionExistingBoard();
				in1.close();
				in2.close();
			}
		}
	 
		FileOutputStream file = new FileOutputStream(Paths.get(path+b.getLevel()+"/"+idNew+".bin").toAbsolutePath().toString());
		ObjectOutputStream out = new ObjectOutputStream(file);
		out.writeObject(b);
		out.close();
		boardDir = new File(Paths.get(path+"/temp.bin").toAbsolutePath().toString());
		boardDir.delete();
	}
	
	/** @brief Metode que exporta el tauler a un fitxer a l escriptori en el format estandard
	 * @pre Donat un Board
	 * @post El tauler donat s exporta a un fitxer
	 * @param b = el tauler a exportar
	 * @param id = identificador del tauler a emmagatzemar
	 * @throws IOException
	 */
	
	public void exportBoard(Board b,String id) throws IOException {
		FileWriter fw = new FileWriter(new File(Paths.get(System.getProperty("user.home")+"/Desktop/Kakuro#"+id+".txt").toAbsolutePath().toString()));
		int rows = b.getRows();
		int cols = b.getColumns();
		fw.write(rows+","+cols+"\n");
		for(int i = 0; i < rows; ++i) {
			for(int j = 0; j < cols; ++j) {
				int v = b.getValue(i, j);
				if(v != -1) {
					if(v == 0) {
						fw.write("?");
					} else {
						fw.write(""+v);
					}
				} else {
					int nH = b.getHNum(i, j);
					int nV = b.getVNum(i, j);
					if(nV != 0) {
						fw.write("C"+nV);
					}
					if(nH != 0) {
						fw.write("F"+nH);
					}
					if(nH == 0 && nV == 0) {
						fw.write("*");
					}
				}
				if(j != cols-1) {
					fw.write(",");
				}
			}
			fw.write("\n");
		}
		fw.close();
	}
	
	/** @brief Metode que llista el contingut de taulers d una determinada dificultat del repositori de kakuros
	 * @pre cert
	 * @post Es retorna una llista de strings que conte el nom de tots els taulers d una determinada dificultat guardats al repositori
	 * @return una llista amb els taulers
	 */
	
	public ArrayList<String> getList(String level) {
		ArrayList<String> list = new ArrayList<String>();
		File boardDir = new File(Paths.get(path).toAbsolutePath().toString());
		if(!boardDir.exists()) boardDir.mkdir();
		File levelDir = new File(Paths.get(path+level+"/").toAbsolutePath().toString());
		if(!levelDir.exists()) levelDir.mkdir();
		String files[] = levelDir.list();
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
	
	/** @brief Metode que llista tots els taulers del repositori de kakuros 
	 * @pre cert
	 * @post Es retorna una llista de strings que conte el nom de tots els taulers guardats al repositori
	 * @return una llista amb els taulers
	 */
	
	public ArrayList<String> getList() {
		ArrayList<String> list = new ArrayList<String>();
		ArrayList<String> list1 = getList("EASY");
		boolean empty = true;
		if(list1 != null && list1.size() != 0) {
			empty = false;
			list.addAll(list1);
		}
		list1 = getList("MEDIUM");
		if(list1 != null && list1.size() != 0) {
			empty = false;
			list.addAll(list1);
		}
		list1 = getList("HARD");
		if(list1 != null && list1.size() != 0) {
			empty = false;
			list.addAll(list1);
		}
		if(empty) return null;
		return list;
	}
}