/** @file PlayMenuPanel.java */
package Presentation.MainScreen.Menus;

/** @class PlayMenuPanel
 */

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Presentation.MainScreen.MainScreen;
import Presentation.MainScreen.PlayPanel.PlayPanel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class PlayMenuPanel extends JPanel implements ActionListener {
	
	private MainScreen father;
	private PlayPanel playPanel;
	
	private JButton btnHint;
	private JButton btnSave;
	private JButton btnExit;
	private JButton btnExport;

	public PlayMenuPanel(PlayPanel playPanel, MainScreen window) {
		this.playPanel = playPanel;
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
		
		JLabel lblGameMenu = new JLabel("GAME MENU");
		lblGameMenu.setBackground(SystemColor.windowBorder);
		lblGameMenu.setOpaque(true);
		lblGameMenu.setHorizontalAlignment(SwingConstants.CENTER);
		lblGameMenu.setPreferredSize(new Dimension(300,70));
		lblGameMenu.setForeground(Color.WHITE);
		lblGameMenu.setFont(new Font("Segoe UI", Font.BOLD, 36));
		this.add(lblGameMenu);
		
		JPanel spacer3 = new JPanel();
		spacer3.setPreferredSize(new Dimension(290, 5));
		spacer3.setBackground(Color.DARK_GRAY);
		this.add(spacer3);
		
		btnHint = new JButton("HINT");
		btnHint.setHorizontalTextPosition(SwingConstants.LEFT);
		btnHint.setIcon(new ImageIcon(PlayMenuPanel.class.getResource("/Presentation/img/hintIcon.png")));
		btnHint.setFocusPainted(false);
		btnHint.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnHint.setBackground(Color.WHITE);
				btnHint.setForeground(Color.BLACK);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnHint.setBackground(Color.DARK_GRAY);
				btnHint.setForeground(Color.WHITE);
			}
		});
		btnHint.setBorder(null);
		btnHint.setBackground(Color.DARK_GRAY);
		btnHint.setForeground(Color.WHITE);
		btnHint.setFont(new Font("Segoe UI", Font.BOLD, 18));
		btnHint.setPreferredSize(new Dimension(300, 60));
		btnHint.addActionListener(this);
		this.add(btnHint);
		
		JPanel spacer4 = new JPanel();
		spacer4.setPreferredSize(new Dimension(290, 5));
		spacer4.setBackground(Color.DARK_GRAY);
		this.add(spacer4);
		
		btnSave = new JButton("SAVE");
		btnSave.setHorizontalTextPosition(SwingConstants.LEFT);
		btnSave.setIcon(new ImageIcon(PlayMenuPanel.class.getResource("/Presentation/img/saveIcon.png")));
		btnSave.setFocusPainted(false);
		btnSave.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnSave.setBackground(Color.WHITE);
				btnSave.setForeground(Color.BLACK);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnSave.setBackground(Color.DARK_GRAY);
				btnSave.setForeground(Color.WHITE);
			}
		});
		btnSave.setPreferredSize(new Dimension(300, 60));
		btnSave.setForeground(Color.WHITE);
		btnSave.setFont(new Font("Segoe UI", Font.BOLD, 18));
		btnSave.setBorder(null);
		btnSave.setBackground(Color.DARK_GRAY);
		btnSave.addActionListener(this);
		this.add(btnSave);
		
		JPanel spacer5 = new JPanel();
		spacer5.setPreferredSize(new Dimension(290, 5));
		spacer5.setBackground(Color.DARK_GRAY);
		this.add(spacer5);
		
		btnExport = new JButton("EXPORT");
		btnExport.setHorizontalTextPosition(SwingConstants.LEFT);
		btnExport.setIcon(new ImageIcon(PlayMenuPanel.class.getResource("/Presentation/img/exportIcon.png")));
		btnExport.setFocusPainted(false);
		btnExport.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnExport.setBackground(Color.WHITE);
				btnExport.setForeground(Color.BLACK);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnExport.setBackground(Color.DARK_GRAY);
				btnExport.setForeground(Color.WHITE);
			}
		});
		btnExport.setPreferredSize(new Dimension(300, 60));
		btnExport.setForeground(Color.WHITE);
		btnExport.setFont(new Font("Segoe UI", Font.BOLD, 18));
		btnExport.setBorder(null);
		btnExport.setBackground(Color.DARK_GRAY);
		btnExport.addActionListener(this);
		this.add(btnExport);
		
		JPanel spacer6 = new JPanel();
		spacer6.setPreferredSize(new Dimension(290, 5));
		spacer6.setBackground(Color.DARK_GRAY);
		this.add(spacer6);
		
		btnExit = new JButton("EXIT");
		btnExit.setHorizontalTextPosition(SwingConstants.LEFT);
		btnExit.setIcon(new ImageIcon(PlayMenuPanel.class.getResource("/Presentation/img/logOutIcon.png")));
		btnExit.setFocusPainted(false);
		btnExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnExit.setBackground(Color.WHITE);
				btnExit.setForeground(Color.BLACK);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				if(!btnExit.isFocusOwner()) {
					btnExit.setBackground(Color.DARK_GRAY);
					btnExit.setForeground(Color.WHITE);
				}
			}
		});
		btnExit.setPreferredSize(new Dimension(300, 60));
		btnExit.setForeground(Color.WHITE);
		btnExit.setFont(new Font("Segoe UI", Font.BOLD, 18));
		btnExit.setBorder(null);
		btnExit.setBackground(Color.DARK_GRAY);
		btnExit.addActionListener(this);
		this.add(btnExit);
		
	}
		
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnHint) {
			playPanel.getHint();
		}
		else if(e.getSource() == btnSave) {
			playPanel.saveGame();
		}
		else if(e.getSource() == btnExport) {
			playPanel.exportBoard();
		}
		else {
			int response = JOptionPane.showConfirmDialog(father, "Make sure to save progress before leaving!\nDo you want to exit?","Confirm exit game",JOptionPane.YES_NO_OPTION,JOptionPane.OK_CANCEL_OPTION);
			if(response == JOptionPane.YES_OPTION) {
				father.setMainScreen();
			}
			btnExit.setBackground(Color.DARK_GRAY);
			btnExit.setForeground(Color.WHITE);
		}
		father.getContentPane().requestFocusInWindow();
	}

}
