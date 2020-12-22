/** @file SelectPanel.java */
package Presentation.MainScreen;

/** @class SelectPanel
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
public class SelectPanel extends JPanel implements ActionListener {
	
	@SuppressWarnings("unused")
	private CtrlPresentation CP;
	private CtrlGame CG;
	private MainScreen father;
	private KakuroPanel KP;
	private ArrayList<String> ids;
	private String level;
	private JPanel optPanel;
	private SpringLayout sl_optPanel;
	private JLabel idBoard;
	private int selected;
	private JButton btnNext;
	private JButton btnPrev;
	private JButton btnEasy;
	private JButton btnMedium;
	private JButton btnHard;

	public SelectPanel(CtrlPresentation CP, MainScreen window, String username) {
		father = window;
		this.CP = CP;
		CG = CP.createGame(username);
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
		
		JLabel lblSel = new JLabel("SELECT");
		sl_this.putConstraint(SpringLayout.SOUTH, lblSel, 17, SpringLayout.NORTH, optPanel);
		sl_this.putConstraint(SpringLayout.WEST, lblSel, 96, SpringLayout.WEST, this);
		lblSel.setIcon(new ImageIcon(MainScreen.class.getResource("/Presentation/img/iconRedDot.png")));
		lblSel.setForeground(Color.DARK_GRAY);
		lblSel.setFont(new Font("Segoe UI", Font.BOLD, 60));
		this.add(lblSel);
		
		idBoard = new JLabel("Choose a level:");
		idBoard.setPreferredSize(new Dimension(500,30));
		idBoard.setForeground(Color.WHITE);
		idBoard.setHorizontalAlignment(SwingConstants.CENTER);
		idBoard.setFont(new Font("Segoe UI", Font.BOLD, 30));
		sl_optPanel.putConstraint(SpringLayout.NORTH, idBoard, 50, SpringLayout.NORTH, optPanel);
		sl_optPanel.putConstraint(SpringLayout.WEST, idBoard, 0, SpringLayout.WEST, optPanel);
		optPanel.add(idBoard);
		
		btnEasy = new JButton("EASY");
		btnEasy.setIconTextGap(-8);
		sl_optPanel.putConstraint(SpringLayout.NORTH, btnEasy, 140, SpringLayout.NORTH, optPanel);
		sl_optPanel.putConstraint(SpringLayout.WEST, btnEasy, 100, SpringLayout.WEST, optPanel);
		btnEasy.setIcon(new ImageIcon(MainScreen.class.getResource("/Presentation/img/easyIcons.png")));
		btnEasy.setHorizontalTextPosition(SwingConstants.LEADING);
		btnEasy.setFocusPainted(false);
		btnEasy.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnEasy.setBackground(Color.WHITE);
				btnEasy.setForeground(Color.BLACK);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnEasy.setBackground(Color.DARK_GRAY);
				btnEasy.setForeground(Color.WHITE);
			}
		});
		btnEasy.setBorder(new MatteBorder(1,1,1,1,Color.WHITE));
		btnEasy.setBackground(Color.DARK_GRAY);
		btnEasy.setForeground(Color.WHITE);
		btnEasy.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		btnEasy.setPreferredSize(new Dimension(300, 60));
		btnEasy.addActionListener(this);
		optPanel.add(btnEasy);

		btnMedium = new JButton("MEDIUM");
		btnMedium.setIconTextGap(0);
		sl_optPanel.putConstraint(SpringLayout.NORTH, btnMedium, 240, SpringLayout.NORTH, optPanel);
		sl_optPanel.putConstraint(SpringLayout.WEST, btnMedium, 100, SpringLayout.WEST, optPanel);
		btnMedium.setIcon(new ImageIcon(MainScreen.class.getResource("/Presentation/img/medIcons.png")));
		btnMedium.setHorizontalTextPosition(SwingConstants.LEADING);
		btnMedium.setFocusPainted(false);
		btnMedium.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnMedium.setBackground(Color.WHITE);
				btnMedium.setForeground(Color.BLACK);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnMedium.setBackground(Color.DARK_GRAY);
				btnMedium.setForeground(Color.WHITE);
			}
		});
		btnMedium.setBorder(new MatteBorder(1,1,1,1,Color.WHITE));
		btnMedium.setBackground(Color.DARK_GRAY);
		btnMedium.setForeground(Color.WHITE);
		btnMedium.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		btnMedium.setPreferredSize(new Dimension(300, 60));
		btnMedium.addActionListener(this);
		optPanel.add(btnMedium);
		
		btnHard = new JButton("HARD");
		btnHard.setIconTextGap(10);
		sl_optPanel.putConstraint(SpringLayout.NORTH, btnHard, 340, SpringLayout.NORTH, optPanel);
		sl_optPanel.putConstraint(SpringLayout.WEST, btnHard, 100, SpringLayout.WEST, optPanel);
		btnHard.setIcon(new ImageIcon(MainScreen.class.getResource("/Presentation/img/hardIcons.png")));
		btnHard.setHorizontalTextPosition(SwingConstants.LEADING);
		btnHard.setFocusPainted(false);
		btnHard.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnHard.setBackground(Color.WHITE);
				btnHard.setForeground(Color.BLACK);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnHard.setBackground(Color.DARK_GRAY);
				btnHard.setForeground(Color.WHITE);
			}
		});
		btnHard.setBorder(new MatteBorder(1,1,1,1,Color.WHITE));
		btnHard.setBackground(Color.DARK_GRAY);
		btnHard.setForeground(Color.WHITE);
		btnHard.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		btnHard.setPreferredSize(new Dimension(300, 60));
		btnHard.addActionListener(this);
		optPanel.add(btnHard);
		
		JLabel lblBG = new JLabel("");
		lblBG.setIcon(new ImageIcon(SelectPanel.class.getResource("/Presentation/img/redSignBG.png")));
		sl_this.putConstraint(SpringLayout.SOUTH, lblBG, 0, SpringLayout.SOUTH, this);
		sl_this.putConstraint(SpringLayout.EAST, lblBG, 0, SpringLayout.EAST, this);
		this.add(lblBG);
	}

	private void updatePreview() {
		optPanel.remove(KP);
		idBoard.setText("Kakuro#"+ids.get(selected));
		CG.selectGame(ids.get(selected),level);
		KP = new KakuroPanel(CG,300,true);
		sl_optPanel.putConstraint(SpringLayout.NORTH, KP, 90, SpringLayout.NORTH, optPanel);
		sl_optPanel.putConstraint(SpringLayout.WEST, KP, 100, SpringLayout.WEST, optPanel);
		optPanel.add(KP);
		revalidate();
		repaint();
	}
	
	private void setSelectionMode() {
		
		optPanel.remove(idBoard);
		optPanel.remove(btnEasy);
		optPanel.remove(btnHard);
		optPanel.remove(btnMedium);
		father.requestFocus();
		
		ids = CG.getBoardList(level);
		selected = 0;
		if(ids == null || ids.size() == 0) {
			idBoard = new JLabel("There are no boards to be shown :(");
			idBoard.setPreferredSize(new Dimension(500,30));
			idBoard.setForeground(Color.WHITE);
			idBoard.setHorizontalAlignment(SwingConstants.CENTER);
			idBoard.setFont(new Font("Segoe UI", Font.BOLD, 20));
			sl_optPanel.putConstraint(SpringLayout.NORTH, idBoard, 210, SpringLayout.NORTH, optPanel);
			sl_optPanel.putConstraint(SpringLayout.WEST, idBoard, 0, SpringLayout.WEST, optPanel);
			optPanel.add(idBoard);
			
			JLabel idBoard2 = new JLabel("Try generating one!");
			idBoard2.setPreferredSize(new Dimension(500,30));
			idBoard2.setForeground(Color.WHITE);
			idBoard2.setHorizontalAlignment(SwingConstants.CENTER);
			idBoard2.setFont(new Font("Segoe UI", Font.BOLD, 16));
			sl_optPanel.putConstraint(SpringLayout.NORTH, idBoard2, 240, SpringLayout.NORTH, optPanel);
			sl_optPanel.putConstraint(SpringLayout.WEST, idBoard2, 0, SpringLayout.WEST, optPanel);
			optPanel.add(idBoard2);
		} else {
			CG.selectGame(ids.get(selected),level);
			
			idBoard = new JLabel("Kakuro#"+ids.get(selected));
			idBoard.setPreferredSize(new Dimension(500,30));
			idBoard.setForeground(Color.WHITE);
			idBoard.setHorizontalAlignment(SwingConstants.CENTER);
			idBoard.setFont(new Font("Segoe UI", Font.BOLD, 30));
			sl_optPanel.putConstraint(SpringLayout.NORTH, idBoard, 30, SpringLayout.NORTH, optPanel);
			sl_optPanel.putConstraint(SpringLayout.WEST, idBoard, 0, SpringLayout.WEST, optPanel);
			optPanel.add(idBoard);
			
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
		}
		
		revalidate();
		repaint();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnEasy) {
			level = "EASY";
			setSelectionMode();
		}
		else if(e.getSource() == btnMedium) {
			level = "MEDIUM";
			setSelectionMode();
		}
		else if(e.getSource() == btnHard) {
			level = "HARD";
			setSelectionMode();
		} else {
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
}
