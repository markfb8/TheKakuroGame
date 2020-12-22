/** @file SettingsPanel.java */
package Presentation.MainScreen;

/** @class SettingsPanel
 */

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.SpringLayout;
import javax.swing.border.MatteBorder;

import Exceptions.ExceptionShortPassword;
import Presentation.CtrlPresentation;

@SuppressWarnings("serial")
public class SettingsPanel extends JPanel implements ActionListener {

	private CtrlPresentation CP;
	private String username;
	private JPasswordField oldPass;
	private JPasswordField pass;
	private JPasswordField passConf;
	private JButton btnChangePass;
	private JLabel lblMessage;

	public SettingsPanel(CtrlPresentation CP, String username) {
		this.username = username;
		this.CP = CP;
		initialize();
	}
	
	private void initialize() {
		this.setPreferredSize(new Dimension(700,700));
		this.setBackground(Color.WHITE);
		SpringLayout sl_this = new SpringLayout();
		this.setLayout(sl_this);
		
		JPanel optPanel = new JPanel();
		optPanel.setPreferredSize(new Dimension(500,500));
		sl_this.putConstraint(SpringLayout.NORTH, optPanel, 80, SpringLayout.NORTH, this);
		sl_this.putConstraint(SpringLayout.WEST, optPanel, 100, SpringLayout.WEST, this);
		optPanel.setBackground(Color.WHITE);
		this.add(optPanel);
		SpringLayout sl_optPanel = new SpringLayout();
		optPanel.setLayout(sl_optPanel);
		
		JLabel lblSettings = new JLabel("SETTINGS");
		sl_this.putConstraint(SpringLayout.SOUTH, lblSettings, 17, SpringLayout.NORTH, optPanel);
		sl_this.putConstraint(SpringLayout.WEST, lblSettings, 96, SpringLayout.WEST, this);
		lblSettings.setForeground(Color.WHITE);
		lblSettings.setFont(new Font("Segoe UI", Font.BOLD, 60));
		this.add(lblSettings);
		
		JLabel lblOldPass = new JLabel("CURRENT PASSWORD");
		sl_optPanel.putConstraint(SpringLayout.NORTH, lblOldPass, 90, SpringLayout.NORTH, optPanel);
		sl_optPanel.putConstraint(SpringLayout.WEST, lblOldPass, 100, SpringLayout.WEST, optPanel);
		lblOldPass.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		optPanel.add(lblOldPass);
		
		JLabel lblPass = new JLabel("NEW PASSWORD");
		sl_optPanel.putConstraint(SpringLayout.NORTH, lblPass, 180, SpringLayout.NORTH, optPanel);
		sl_optPanel.putConstraint(SpringLayout.WEST, lblPass, 100, SpringLayout.WEST, optPanel);
		lblPass.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		optPanel.add(lblPass);
		
		JLabel lblpassConf = new JLabel("CONFIRM NEW PASSWORD");
		sl_optPanel.putConstraint(SpringLayout.NORTH, lblpassConf, 270, SpringLayout.NORTH, optPanel);
		sl_optPanel.putConstraint(SpringLayout.WEST, lblpassConf, 100, SpringLayout.WEST, optPanel);
		lblpassConf.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		optPanel.add(lblpassConf);
		
		oldPass = new JPasswordField();
		sl_optPanel.putConstraint(SpringLayout.NORTH, oldPass, 110, SpringLayout.NORTH, optPanel);
		sl_optPanel.putConstraint(SpringLayout.WEST, oldPass, 100, SpringLayout.WEST, optPanel);
		oldPass.setBackground(Color.WHITE);
		oldPass.setPreferredSize(new Dimension(300,30));
		oldPass.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		oldPass.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		oldPass.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				char c = e.getKeyChar();
				if(!Character.isSpaceChar(c)) {
					oldPass.setEditable(true);
				} else {
					oldPass.setEditable(false);
				}
			}
		});
		oldPass.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				oldPass.setBackground(Color.WHITE);
			}
		});
		optPanel.add(oldPass);
		
		pass = new JPasswordField();
		sl_optPanel.putConstraint(SpringLayout.NORTH, pass, 200, SpringLayout.NORTH, optPanel);
		sl_optPanel.putConstraint(SpringLayout.WEST, pass, 100, SpringLayout.WEST, optPanel);
		pass.setBackground(Color.WHITE);
		pass.setPreferredSize(new Dimension(300,30));
		pass.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		pass.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		pass.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				char c = e.getKeyChar();
				if(!Character.isSpaceChar(c)) {
					pass.setEditable(true);
				} else {
					pass.setEditable(false);
				}
			}
		});
		pass.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				pass.setBackground(Color.WHITE);
				passConf.setBackground(Color.WHITE);
			}
		});
		optPanel.add(pass);
		
		passConf = new JPasswordField();
		sl_optPanel.putConstraint(SpringLayout.NORTH, passConf, 290, SpringLayout.NORTH, optPanel);
		sl_optPanel.putConstraint(SpringLayout.WEST, passConf, 100, SpringLayout.WEST, optPanel);
		passConf.setBackground(Color.WHITE);
		passConf.setPreferredSize(new Dimension(300,30));
		passConf.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		passConf.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		passConf.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				char c = e.getKeyChar();
				if(!Character.isSpaceChar(c)) {
					passConf.setEditable(true);
				} else {
					passConf.setEditable(false);
				}
			}
		});
		passConf.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				pass.setBackground(Color.WHITE);
				passConf.setBackground(Color.WHITE);
			}
		});
		optPanel.add(passConf);
		
		btnChangePass = new JButton("APPLY CHANGES");
		sl_optPanel.putConstraint(SpringLayout.SOUTH, btnChangePass, -80, SpringLayout.SOUTH, optPanel);
		sl_optPanel.putConstraint(SpringLayout.EAST, btnChangePass, -100, SpringLayout.EAST, optPanel);
		btnChangePass.setPreferredSize(new Dimension(120,30));
		btnChangePass.setFocusPainted(false);
		btnChangePass.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnChangePass.setBackground(Color.DARK_GRAY);
				btnChangePass.setForeground(Color.WHITE);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnChangePass.setBackground(Color.WHITE);
				btnChangePass.setForeground(Color.DARK_GRAY);
			}
		});
		btnChangePass.setBorder(new MatteBorder(1,1,1,1,Color.DARK_GRAY));
		btnChangePass.setBackground(Color.WHITE);
		btnChangePass.setForeground(Color.DARK_GRAY);
		btnChangePass.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		btnChangePass.addActionListener(this);
		optPanel.add(btnChangePass);
		
		lblMessage = new JLabel("");
		lblMessage.setPreferredSize(new Dimension(400,20));
		sl_optPanel.putConstraint(SpringLayout.NORTH, lblMessage, 20, SpringLayout.SOUTH, passConf);
		sl_optPanel.putConstraint(SpringLayout.WEST, lblMessage, 0, SpringLayout.WEST, passConf);
		lblMessage.setForeground(new Color(250, 128, 114));
		lblMessage.setBackground(Color.WHITE);
		lblMessage.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		optPanel.add(lblMessage);
		
		JLabel lblGenBg = new JLabel("");
		lblGenBg.setIcon(new ImageIcon(MainScreen.class.getResource("/Presentation/img/cablesBG.png")));
		sl_this.putConstraint(SpringLayout.SOUTH, lblGenBg, 0, SpringLayout.SOUTH, this);
		sl_this.putConstraint(SpringLayout.EAST, lblGenBg, 0, SpringLayout.EAST, this);
		this.add(lblGenBg);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		lblMessage.setText("");
		pass.setBackground(Color.WHITE);
		passConf.setBackground(Color.WHITE);
		oldPass.setBackground(Color.WHITE);
		String old = new String(oldPass.getPassword());
		String new1 = new String(pass.getPassword());
		String new2 = new String(passConf.getPassword());
		if(!new1.equals(new2)) {
			lblMessage.setText("Passwords do not match");
			pass.setBackground(new Color(250, 128, 114));
			passConf.setBackground(new Color(250, 128, 114));
		} else if(old.isBlank() || new1.isBlank()) {
			lblMessage.setText("Please fill in all fields");
		} else if(new1.equals(old)) {
			lblMessage.setText("New password must be different from current password");
			pass.setBackground(new Color(250, 128, 114));
			passConf.setBackground(new Color(250, 128, 114));
		}
		else {
			boolean correct = false;
			try {
				correct = CP.changePassword(username, old, new1);
				if(correct) {
					lblMessage.setText("Your password was updated");
					oldPass.setText("");
					pass.setText("");
					passConf.setText("");
				} else {
					oldPass.setBackground(new Color(250, 128, 114));
					lblMessage.setText("Current password is not correct");
				}
			} catch (ExceptionShortPassword e1) {
				pass.setBackground(new Color(250, 128, 114));
				passConf.setBackground(new Color(250, 128, 114));
				lblMessage.setText(e1.getMessage());
			}
		}
	}
}
