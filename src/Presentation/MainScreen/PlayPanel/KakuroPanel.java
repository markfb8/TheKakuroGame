/** @file KakuroPanel.java */
package Presentation.MainScreen.PlayPanel;

/** @class KakuroPanel
 */

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;

import Domain.Controllers.CtrlGame;

@SuppressWarnings("serial")
public class KakuroPanel extends JPanel implements ActionListener {
	
	private CtrlGame CG;
	private ArrayList<ArrayList<String[]>> board;
	private JLabel[][] whiteCells;
	private JPanel[][] blackCells;
	private int rows;
	private int cols;
	private int size;
	private int fontSize;
	private boolean preview;
	private int selectedi;
	private int selectedj;
	
	public KakuroPanel(CtrlGame CG, int size, boolean preview) {
		this.CG = CG;
		board = CG.getCellsInfo();
		this.size = size;
		this.preview = preview;
		rows = board.size();
		cols = board.get(0).size();
		whiteCells = new JLabel[rows][cols];
		blackCells = new JPanel[rows][cols];
		if(size == 550) fontSize = Math.min(150/rows,150/cols); //font size of black cells
		else fontSize = Math.min(80/rows,80/cols);
		selectedi = 0;
		selectedj = 0;
		initialize();
	}

	private void initialize() {
		this.setOpaque(false);
		if(rows < cols) {
			this.setPreferredSize(new Dimension(size,(size/cols)*rows));
		} else if(cols < rows) {
			this.setPreferredSize(new Dimension((size/rows)*cols,size));
		} else {
			this.setPreferredSize(new Dimension(size,size));
		}
		
		this.setBackground(Color.WHITE);
		this.setLayout(new GridLayout(rows, cols, 0, 0));
		
		for(int i = 0; i < rows; ++i) {
			for(int j = 0; j < cols; ++j) {
				String[] cell = board.get(i).get(j);
				if(cell[0] == "B") {
					blackCells[i][j] = new blackCellPanel(Integer.parseInt(cell[1]),Integer.parseInt(cell[2]),fontSize);
					blackCells[i][j].setBorder(new MatteBorder(1, 1, 1, 1, Color.BLACK));
					this.add(blackCells[i][j]);
				} else {
					whiteCells[i][j] = new JLabel("");
					JLabel aux = whiteCells[i][j];
					aux.setFont(new Font("Segoe UI", Font.PLAIN, fontSize*2));
					aux.setVerticalAlignment(SwingConstants.CENTER);
					aux.setHorizontalAlignment(SwingConstants.CENTER);
					aux.setBorder(new MatteBorder(1, 1, 1, 1, Color.BLACK));
					aux.setBackground(Color.WHITE);
					aux.setOpaque(true);
					aux.setFocusable(false);
					if(!preview) {
						if(cell[2].equals("NP")) {
							aux.setFocusable(true);
							aux.addFocusListener(new FocusAdapter() {
								@Override
								public void focusGained(FocusEvent e) {
									for(int i = 0; i < whiteCells.length; ++i) {
										for(int j = 0; j < whiteCells[0].length; ++j) {
											if(aux.equals(whiteCells[i][j])) {
												selectedi = i;
												selectedj = j;
											}
										}
									}
									aux.setBackground(new Color(143,188,143));
								}
								@Override
								public void focusLost(FocusEvent e) {
									aux.setBackground(Color.WHITE);
								}
							});
							aux.addMouseListener(new MouseAdapter() {
								@Override
								public void mousePressed(MouseEvent e) {
									aux.requestFocus();
								}
							});
						} else {
							aux.setForeground(new Color(143,188,143));
						}
					} else {
						if(cell[2].equals("P")) {
							aux.setForeground(new Color(143,188,143));
						}
					}
					int v = Integer.parseInt(cell[1]);
					if(v == 0) whiteCells[i][j].setText(""); 
					else whiteCells[i][j].setText(Integer.toString(v));
					this.add(aux);
				}
			}
		}
	}
	
	public void setNum(int v) {
		if(selectedi != 0 && selectedj != 0) {
			whiteCells[selectedi][selectedj].requestFocus();
			try {
				if(CG.setCellValue(selectedi,selectedj,v)) {
					if(v == 0) whiteCells[selectedi][selectedj].setText(""); 
					else whiteCells[selectedi][selectedj].setText(Integer.toString(v));
				}
			} catch (Exception e) {
				e.getMessage();
			}
		}
	}
	
	public void setHint(int i, int j, int v) {
		whiteCells[i][j].setForeground(new Color(143,188,143));
		whiteCells[i][j].setFocusable(false);
		whiteCells[i][j].setText(Integer.toString(v));
	}

	@Override
	public void actionPerformed(ActionEvent e) {}
}
