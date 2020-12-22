/** @file blackCellPanel.java */
package Presentation.MainScreen.PlayPanel;

/** @class blackCellPanel
 */

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

import Presentation.MainScreen.MainScreen;

@SuppressWarnings("serial")
public class blackCellPanel extends JPanel {

	public blackCellPanel(int nH, int nV, int numSize) {
		this.setBackground(Color.DARK_GRAY);
		SpringLayout sl_this = new SpringLayout();
		this.setLayout(sl_this);
		
		if(nH != 0) {
			JLabel numH = new JLabel(Integer.toString(nH));
			numH.setFont(new Font("Segoe UI", Font.BOLD, numSize));
			numH.setForeground(Color.WHITE);
			sl_this.putConstraint(SpringLayout.NORTH, numH, 2,SpringLayout.NORTH, this);
			sl_this.putConstraint(SpringLayout.EAST, numH, -2,SpringLayout.EAST, this);
			this.add(numH);
		}
		
		if(nV != 0) {
			JLabel numV = new JLabel(Integer.toString(nV));
			numV.setFont(new Font("Segoe UI", Font.BOLD, numSize));
			numV.setForeground(Color.WHITE);
			sl_this.putConstraint(SpringLayout.WEST, numV, 2,SpringLayout.WEST, this);
			sl_this.putConstraint(SpringLayout.SOUTH, numV, -2,SpringLayout.SOUTH, this);
			this.add(numV);
		}
		
		if(nH != 0 || nV != 0) {
			JLabel lblBG = new JLabel("");
			lblBG.setIcon(new ImageIcon(MainScreen.class.getResource("/Presentation/img/cellLine2.png")));
			sl_this.putConstraint(SpringLayout.SOUTH, lblBG, 0, SpringLayout.SOUTH, this);
			sl_this.putConstraint(SpringLayout.EAST, lblBG, 0, SpringLayout.EAST, this);
			this.add(lblBG);
		}
	}

}
