/** @file LoadGamePanel.java */
package Presentation.MainScreen;

/** @class LoadGamePanel
 */

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;

import Domain.Controllers.CtrlGame;
import Presentation.CtrlPresentation;
import Presentation.MainScreen.Menus.MainMenuPanel;
import Presentation.MainScreen.PlayPanel.KakuroPanel;

@SuppressWarnings("serial")
public class LoadGamePanel extends JPanel implements ActionListener {

	@SuppressWarnings("unused")
	private CtrlPresentation CP;
	private CtrlGame CG;
	private MainScreen father;
	private KakuroPanel KP;
	private ArrayList<String> ids;
	
	private JPanel optPanel;
	private SpringLayout sl_optPanel;
	private JLabel idBoard;
	private int selected;
	private JButton btnNext;
	private JButton btnPrev;
	
	public LoadGamePanel(CtrlPresentation CP, MainScreen window, String username) {
		father = window;
		this.CP = CP;
		CG = CP.createGame(username);
		ids = CG.getGamesList();
		initialize();
	}
	
	private void initialize() {
		this.setBackground(Color.WHITE);
		SpringLayout sl_this = new SpringLayout();
		this.setLayout(sl_this);
		
		optPanel = new JPanel();
		optPanel.setBackground(Color.DARK_GRAY);
		optPanel.setPreferredSize(new Dimension(500,500));
		sl_this.putConstraint(SpringLayout.NORTH, optPanel, 100, SpringLayout.NORTH, this);
		sl_this.putConstraint(SpringLayout.WEST, optPanel, 100, SpringLayout.WEST, this);
		this.add(optPanel);
		sl_optPanel = new SpringLayout();
		optPanel.setLayout(sl_optPanel);
		
		JLabel lblLoad = new JLabel("LOAD GAME");
		sl_this.putConstraint(SpringLayout.SOUTH, lblLoad, 17, SpringLayout.NORTH, optPanel);
		sl_this.putConstraint(SpringLayout.WEST, lblLoad, 95, SpringLayout.WEST, this);
		lblLoad.setIcon(new ImageIcon(MainScreen.class.getResource("/Presentation/img/iconRedDot.png")));
		lblLoad.setForeground(Color.DARK_GRAY);
		lblLoad.setFont(new Font("Segoe UI", Font.BOLD, 60));
		this.add(lblLoad);
		
		if(ids != null && ids.size() != 0) {
			idBoard = new JLabel();
			idBoard.setPreferredSize(new Dimension(500,30));
			idBoard.setForeground(Color.WHITE);
			idBoard.setHorizontalAlignment(SwingConstants.CENTER);
			idBoard.setFont(new Font("Segoe UI", Font.BOLD, 30));
			sl_optPanel.putConstraint(SpringLayout.NORTH, idBoard, 30, SpringLayout.NORTH, optPanel);
			sl_optPanel.putConstraint(SpringLayout.WEST, idBoard, 0, SpringLayout.WEST, optPanel);
			optPanel.add(idBoard);
			
			selected = 0;
			CG.loadGame(ids.get(selected));
			idBoard.setText("Kakuro#"+ids.get(selected));

			KP = new KakuroPanel(CG,300,true);
			sl_optPanel.putConstraint(SpringLayout.NORTH, KP, 90, SpringLayout.NORTH, optPanel);
			sl_optPanel.putConstraint(SpringLayout.WEST, KP, 100, SpringLayout.WEST, optPanel);
			optPanel.add(KP);
			
			btnPrev = new JButton();
			btnPrev.setFocusPainted(false);
			btnPrev.setBorder(null);
			btnPrev.setIcon(new ImageIcon(MainMenuPanel.class.getResource("/Presentation/img/leftArrowIcon.png")));
			btnPrev.setPreferredSize(new Dimension(50,50));
			sl_optPanel.putConstraint(SpringLayout.NORTH, btnPrev, 215, SpringLayout.NORTH, optPanel);
			sl_optPanel.putConstraint(SpringLayout.WEST, btnPrev, 25, SpringLayout.WEST, optPanel);
			btnPrev.setBackground(Color.DARK_GRAY);
			btnPrev.addActionListener(this); 
			optPanel.add(btnPrev);
			
			btnNext = new JButton();
			btnNext.setFocusPainted(false);
			btnNext.setBorder(null);
			btnNext.setIcon(new ImageIcon(MainMenuPanel.class.getResource("/Presentation/img/rightArrowIcon.png")));
			btnNext.setPreferredSize(new Dimension(50,50));
			sl_optPanel.putConstraint(SpringLayout.NORTH, btnNext, 215, SpringLayout.NORTH, optPanel);
			sl_optPanel.putConstraint(SpringLayout.EAST, btnNext, -25, SpringLayout.EAST, optPanel);
			btnNext.setBackground(Color.DARK_GRAY);
			btnNext.addActionListener(this); 
			optPanel.add(btnNext);
			
			JButton btnSelect = new JButton("PLAY");
			btnSelect.setPreferredSize(new Dimension(100,40));
			btnSelect.setFocusPainted(false);
			sl_optPanel.putConstraint(SpringLayout.SOUTH, btnSelect, -40, SpringLayout.SOUTH, optPanel);
			sl_optPanel.putConstraint(SpringLayout.EAST, btnSelect, -200, SpringLayout.EAST, optPanel);
			btnSelect.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent e) {
					btnSelect.setBackground(Color.WHITE);
					btnSelect.setForeground(Color.BLACK);
				}
				@Override
				public void mouseExited(MouseEvent e) {
					btnSelect.setBackground(Color.DARK_GRAY);
					btnSelect.setForeground(Color.WHITE);
				}
			});
			btnSelect.setBorder(new MatteBorder(1,1,1,1,Color.WHITE));
			btnSelect.setBackground(Color.DARK_GRAY);
			btnSelect.setForeground(Color.WHITE);
			btnSelect.setFont(new Font("Segoe UI", Font.PLAIN, 14));
			btnSelect.addActionListener(this); 
			optPanel.add(btnSelect);
		} else {
			idBoard = new JLabel();
			idBoard.setPreferredSize(new Dimension(500,30));
			idBoard.setForeground(Color.WHITE);
			idBoard.setHorizontalAlignment(SwingConstants.CENTER);
			idBoard.setFont(new Font("Segoe UI", Font.BOLD, 20));
			sl_optPanel.putConstraint(SpringLayout.NORTH, idBoard, 210, SpringLayout.NORTH, optPanel);
			sl_optPanel.putConstraint(SpringLayout.WEST, idBoard, 0, SpringLayout.WEST, optPanel);
			optPanel.add(idBoard);
			idBoard.setText("You do not have any saved games :(");
		}
		
		JLabel lblBG = new JLabel("");
		lblBG.setIcon(new ImageIcon(SelectPanel.class.getResource("/Presentation/img/gameBG.png")));
		sl_this.putConstraint(SpringLayout.SOUTH, lblBG, 0, SpringLayout.SOUTH, this);
		sl_this.putConstraint(SpringLayout.EAST, lblBG, 0, SpringLayout.EAST, this);
		this.add(lblBG);
	}
	
	private void updatePreview() {
		optPanel.remove(KP);
		idBoard.setText("Kakuro#"+ids.get(selected));
		CG.loadGame(ids.get(selected));
		KP = new KakuroPanel(CG,300,true);
		sl_optPanel.putConstraint(SpringLayout.NORTH, KP, 90, SpringLayout.NORTH, optPanel);
		sl_optPanel.putConstraint(SpringLayout.WEST, KP, 100, SpringLayout.WEST, optPanel);
		optPanel.add(KP);
		revalidate();
		repaint();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnNext) {
			if(selected < ids.size()-1) ++selected;
			else selected = 0;
			updatePreview();
		} else if(e.getSource() == btnPrev) {
			if(selected > 0) --selected; 
			else selected = ids.size()-1;
			updatePreview();
		} else {
			father.setPlayPanel(CG);
		}
	}

}
