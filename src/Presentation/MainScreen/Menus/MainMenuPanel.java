/** @file MainMenuPanel.java */
package Presentation.MainScreen.Menus;

/** @class MainMenuPanel
 */

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Presentation.MainScreen.MainScreen;
import javax.swing.SwingConstants;
import java.awt.SystemColor;

@SuppressWarnings("serial")
public class MainMenuPanel extends JPanel implements ActionListener {
	
	private MainScreen father;
	
	private JButton btnGenerateKakuro;
	private JButton btnSelectKakuro;
	private JButton btnProposeKakuro;
	private JButton btnLoadGame;
	private JButton btnLogOut;
	private JButton btnRank;
	private JButton btnSettings;
	
	public MainMenuPanel(MainScreen window) {
		father = window;
		initialize();
	}
	
	private void initialize() {

		this.setBackground(Color.DARK_GRAY);
		this.setPreferredSize(new Dimension(300,700));
		this.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JPanel spacer = new JPanel();
		spacer.setBackground(Color.DARK_GRAY);
		spacer.setPreferredSize(new Dimension(290, 10));
		this.add(spacer);
		
		JLabel lblMenu = new JLabel("MENU");
		lblMenu.setBackground(SystemColor.windowBorder);
		lblMenu.setOpaque(true);
		lblMenu.setHorizontalAlignment(SwingConstants.CENTER);
		lblMenu.setPreferredSize(new Dimension(300,70));
		lblMenu.setForeground(Color.WHITE);
		lblMenu.setFont(new Font("Segoe UI", Font.BOLD, 36));
		this.add(lblMenu);
		
		JPanel spacer3 = new JPanel();
		spacer3.setPreferredSize(new Dimension(290, 5));
		spacer3.setBackground(Color.DARK_GRAY);
		this.add(spacer3);
		
		btnGenerateKakuro = new JButton("GENERATE");
		btnGenerateKakuro.setHorizontalTextPosition(SwingConstants.LEFT);
		btnGenerateKakuro.setIcon(new ImageIcon(MainMenuPanel.class.getResource("/Presentation/img/generateIcon.png")));
		btnGenerateKakuro.setFocusPainted(false);
		btnGenerateKakuro.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnGenerateKakuro.setBackground(Color.WHITE);
				btnGenerateKakuro.setForeground(Color.BLACK);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				if(!btnGenerateKakuro.isFocusOwner()) {
					btnGenerateKakuro.setBackground(Color.DARK_GRAY);
					btnGenerateKakuro.setForeground(Color.WHITE);
				}
			}
		});
		btnGenerateKakuro.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				btnGenerateKakuro.setBackground(Color.WHITE);
				btnGenerateKakuro.setForeground(Color.BLACK);
			}
			@Override
			public void focusLost(FocusEvent e) {
				btnGenerateKakuro.setBackground(Color.DARK_GRAY);
				btnGenerateKakuro.setForeground(Color.WHITE);
			}
		});
		btnGenerateKakuro.setBorder(null);
		btnGenerateKakuro.setBackground(Color.DARK_GRAY);
		btnGenerateKakuro.setForeground(Color.WHITE);
		btnGenerateKakuro.setFont(new Font("Segoe UI", Font.BOLD, 17));
		btnGenerateKakuro.setPreferredSize(new Dimension(300, 60));
		btnGenerateKakuro.addActionListener(this);
		this.add(btnGenerateKakuro);
		
		JPanel spacer4 = new JPanel();
		spacer4.setPreferredSize(new Dimension(290, 5));
		spacer4.setBackground(Color.DARK_GRAY);
		this.add(spacer4);
		
		btnSelectKakuro = new JButton("SELECT");
		btnSelectKakuro.setHorizontalTextPosition(SwingConstants.LEFT);
		btnSelectKakuro.setIcon(new ImageIcon(MainMenuPanel.class.getResource("/Presentation/img/selectIcon.png")));
		btnSelectKakuro.setFocusPainted(false);
		btnSelectKakuro.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnSelectKakuro.setBackground(Color.WHITE);
				btnSelectKakuro.setForeground(Color.BLACK);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				if(!btnSelectKakuro.isFocusOwner()) {
					btnSelectKakuro.setBackground(Color.DARK_GRAY);
					btnSelectKakuro.setForeground(Color.WHITE);
				}
			}
		});
		btnSelectKakuro.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				btnSelectKakuro.setBackground(Color.WHITE);
				btnSelectKakuro.setForeground(Color.BLACK);
			}
			@Override
			public void focusLost(FocusEvent e) {
				btnSelectKakuro.setBackground(Color.DARK_GRAY);
				btnSelectKakuro.setForeground(Color.WHITE);
			}
		});
		btnSelectKakuro.setPreferredSize(new Dimension(300, 60));
		btnSelectKakuro.setForeground(Color.WHITE);
		btnSelectKakuro.setFont(new Font("Segoe UI", Font.BOLD, 17));
		btnSelectKakuro.setBorder(null);
		btnSelectKakuro.setBackground(Color.DARK_GRAY);
		btnSelectKakuro.addActionListener(this);
		this.add(btnSelectKakuro);
		
		JPanel spacer5 = new JPanel();
		spacer5.setPreferredSize(new Dimension(290, 5));
		spacer5.setBackground(Color.DARK_GRAY);
		this.add(spacer5);
		
		btnProposeKakuro = new JButton("VALIDATE");
		btnProposeKakuro.setHorizontalTextPosition(SwingConstants.LEFT);
		btnProposeKakuro.setIcon(new ImageIcon(MainMenuPanel.class.getResource("/Presentation/img/validateIcon.png")));
		btnProposeKakuro.setFocusPainted(false);
		btnProposeKakuro.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnProposeKakuro.setBackground(Color.WHITE);
				btnProposeKakuro.setForeground(Color.BLACK);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				if(!btnProposeKakuro.isFocusOwner()) {
					btnProposeKakuro.setBackground(Color.DARK_GRAY);
					btnProposeKakuro.setForeground(Color.WHITE);
				}
			}
		});
		btnProposeKakuro.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				btnProposeKakuro.setBackground(Color.WHITE);
				btnProposeKakuro.setForeground(Color.BLACK);
			}
			@Override
			public void focusLost(FocusEvent e) {
				btnProposeKakuro.setBackground(Color.DARK_GRAY);
				btnProposeKakuro.setForeground(Color.WHITE);
			}
		});
		btnProposeKakuro.setPreferredSize(new Dimension(300, 60));
		btnProposeKakuro.setForeground(Color.WHITE);
		btnProposeKakuro.setFont(new Font("Segoe UI", Font.BOLD, 17));
		btnProposeKakuro.setBorder(null);
		btnProposeKakuro.setBackground(Color.DARK_GRAY);
		btnProposeKakuro.addActionListener(this);
		this.add(btnProposeKakuro);
		
		JPanel spacer6 = new JPanel();
		spacer6.setPreferredSize(new Dimension(290, 5));
		spacer6.setBackground(Color.DARK_GRAY);
		this.add(spacer6);
		
		btnLoadGame = new JButton("LOAD GAME");
		btnLoadGame.setHorizontalTextPosition(SwingConstants.LEFT);
		btnLoadGame.setIcon(new ImageIcon(MainMenuPanel.class.getResource("/Presentation/img/loadGameIcon.png")));
		btnLoadGame.setFocusPainted(false);
		btnLoadGame.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnLoadGame.setBackground(Color.WHITE);
				btnLoadGame.setForeground(Color.BLACK);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				if(!btnLoadGame.isFocusOwner()) {
					btnLoadGame.setBackground(Color.DARK_GRAY);
					btnLoadGame.setForeground(Color.WHITE);
				}
			}
		});
		btnLoadGame.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				btnLoadGame.setBackground(Color.WHITE);
				btnLoadGame.setForeground(Color.BLACK);
			}
			@Override
			public void focusLost(FocusEvent e) {
				btnLoadGame.setBackground(Color.DARK_GRAY);
				btnLoadGame.setForeground(Color.WHITE);
			}
		});
		btnLoadGame.setPreferredSize(new Dimension(300, 60));
		btnLoadGame.setForeground(Color.WHITE);
		btnLoadGame.setFont(new Font("Segoe UI", Font.BOLD, 17));
		btnLoadGame.setBorder(null);
		btnLoadGame.setBackground(Color.DARK_GRAY);
		btnLoadGame.addActionListener(this);
		this.add(btnLoadGame);
		
		JPanel spacer7 = new JPanel();
		spacer7.setPreferredSize(new Dimension(290, 5));
		spacer7.setBackground(Color.DARK_GRAY);
		this.add(spacer7);
		
		btnRank = new JButton("RANKING");
		btnRank.setIcon(new ImageIcon(MainMenuPanel.class.getResource("/Presentation/img/trophyIcon.png")));
		btnRank.setHorizontalTextPosition(SwingConstants.LEFT);
		btnRank.setFocusPainted(false);
		btnRank.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnRank.setBackground(Color.WHITE);
				btnRank.setForeground(Color.BLACK);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				if(!btnRank.isFocusOwner()) {
					btnRank.setBackground(Color.DARK_GRAY);
					btnRank.setForeground(Color.WHITE);
				}
			}
		});
		btnRank.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				btnRank.setBackground(Color.WHITE);
				btnRank.setForeground(Color.BLACK);
			}
			@Override
			public void focusLost(FocusEvent e) {
				btnRank.setBackground(Color.DARK_GRAY);
				btnRank.setForeground(Color.WHITE);
			}
		});
		btnRank.setPreferredSize(new Dimension(300, 60));
		btnRank.setForeground(Color.WHITE);
		btnRank.setFont(new Font("Segoe UI", Font.BOLD, 17));
		btnRank.setBorder(null);
		btnRank.setBackground(Color.DARK_GRAY);
		btnRank.addActionListener(this);
		this.add(btnRank);
		
		JPanel spacer8 = new JPanel();
		spacer8.setPreferredSize(new Dimension(290, 5));
		spacer8.setBackground(Color.DARK_GRAY);
		this.add(spacer8);
		
		btnSettings = new JButton("SETTINGS");
		btnSettings.setHorizontalTextPosition(SwingConstants.LEFT);
		btnSettings.setIcon(new ImageIcon(MainMenuPanel.class.getResource("/Presentation/img/configIcon.png")));
		btnSettings.setFocusPainted(false);
		btnSettings.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnSettings.setBackground(Color.WHITE);
				btnSettings.setForeground(Color.BLACK);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				if(!btnSettings.isFocusOwner()) {
					btnSettings.setBackground(Color.DARK_GRAY);
					btnSettings.setForeground(Color.WHITE);
				}
			}
		});
		btnSettings.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				btnSettings.setBackground(Color.WHITE);
				btnSettings.setForeground(Color.BLACK);
			}
			@Override
			public void focusLost(FocusEvent e) {
				btnSettings.setBackground(Color.DARK_GRAY);
				btnSettings.setForeground(Color.WHITE);
			}
		});
		btnSettings.setPreferredSize(new Dimension(300, 60));
		btnSettings.setForeground(Color.WHITE);
		btnSettings.setFont(new Font("Segoe UI", Font.BOLD, 17));
		btnSettings.setBorder(null);
		btnSettings.setBackground(Color.DARK_GRAY);
		btnSettings.addActionListener(this);
		this.add(btnSettings);
		
		JPanel spacer9 = new JPanel();
		spacer9.setPreferredSize(new Dimension(290, 5));
		spacer9.setBackground(Color.DARK_GRAY);
		this.add(spacer9);
		
		btnLogOut = new JButton("LOG OUT");
		btnLogOut.setHorizontalTextPosition(SwingConstants.LEFT);
		btnLogOut.setIcon(new ImageIcon(MainMenuPanel.class.getResource("/Presentation/img/logOutIcon.png")));
		btnLogOut.setFocusPainted(false);
		btnLogOut.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnLogOut.setBackground(Color.WHITE);
				btnLogOut.setForeground(Color.BLACK);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				if(!btnLogOut.isFocusOwner()) {
					btnLogOut.setBackground(Color.DARK_GRAY);
					btnLogOut.setForeground(Color.WHITE);
				}
			}
		});
		btnLogOut.setPreferredSize(new Dimension(300, 60));
		btnLogOut.setForeground(Color.WHITE);
		btnLogOut.setFont(new Font("Segoe UI", Font.BOLD, 17));
		btnLogOut.setBorder(null);
		btnLogOut.setBackground(Color.DARK_GRAY);
		btnLogOut.addActionListener(this);
		btnLogOut.setFocusable(false);
		this.add(btnLogOut);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() != btnLogOut) {
			if(e.getSource() == btnGenerateKakuro) {
				father.setGeneratePanel();
			}
			else if(e.getSource() == btnSelectKakuro) {
				father.setSelectPanel();
			}
			else if(e.getSource() == btnProposeKakuro) {
				father.setProposePanel();
			}
			else if(e.getSource() == btnLoadGame) {
				father.setLoadGamePanel();
			}
			else if(e.getSource() == btnRank) {
				father.setRankPanel();
			}
			else if(e.getSource() == btnSettings) {
				father.setSettingsPanel();
			}
		}
		else {
			int response = JOptionPane.showConfirmDialog(father, "We're sad to see you go :(\nAre you sure you want to exit?","Confirm log out",JOptionPane.YES_NO_OPTION,JOptionPane.OK_CANCEL_OPTION);
			if(response == JOptionPane.YES_OPTION) {
				father.dispose();
			}
			btnLogOut.setBackground(Color.DARK_GRAY);
			btnLogOut.setForeground(Color.WHITE);
		}
	}

}
