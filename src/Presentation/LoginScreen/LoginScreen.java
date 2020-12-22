/** @file LoginScreen.java */
package Presentation.LoginScreen;

/** @class LoginScreen
 */

import javax.swing.JFrame;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.SystemColor;
import java.awt.Toolkit;
import javax.swing.border.MatteBorder;

import Exceptions.ExceptionShortPassword;
import Presentation.CtrlPresentation;
import Presentation.MainScreen.MainScreen;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

@SuppressWarnings("serial")
public class LoginScreen extends JFrame implements ActionListener {

	private CtrlPresentation CP;
	private JPanel contentPane;
	private JPanel loginPanel;
	private JPanel registerPanel;
	private JTextField userText;
	private JPasswordField passText;
	private JButton btnLogin;
	private JLabel lblMessage;
	private JTextField userRegText;
	private JPasswordField passRegText;
	private JPasswordField passRegText2;
	private JButton btnSignUp;
	private JButton btnBack;
	private JLabel lblMessage2;
	private JLabel lblAbout;

	public LoginScreen(CtrlPresentation CP) {
		this.CP = CP;
		initialize();
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	private void initialize() {
		setResizable(false);
		setPreferredSize(new Dimension(700,500));
		setTitle("The Kakuro Game");
		setIconImage(Toolkit.getDefaultToolkit().getImage(LoginScreen.class.getResource("/Presentation/img/frameLogo.png")));

		setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 687, 500);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
	//logo panel
		
		JPanel logoPanel = new JPanel();
		logoPanel.setBackground(Color.DARK_GRAY);
		logoPanel.setBounds(0, 0, 350, 461);
		contentPane.add(logoPanel);
		logoPanel.setLayout(null);
		
		JLabel lblThe = new JLabel("The");
		lblThe.setBounds(35, 70, 54, 55);
		lblThe.setForeground(Color.WHITE);
		lblThe.setFont(new Font("Segoe UI", Font.BOLD, 25));
		logoPanel.add(lblThe);
		
		JLabel lblKakuro = new JLabel("Kakuro");
		lblKakuro.setBounds(81, 32, 232, 97);
		lblKakuro.setForeground(Color.WHITE);
		lblKakuro.setFont(new Font("Segoe UI", Font.BOLD, 65));
		logoPanel.add(lblKakuro);
		
		JLabel lblGame = new JLabel("Game");
		lblGame.setBounds(35, 97, 154, 81);
		lblGame.setForeground(Color.WHITE);
		lblGame.setFont(new Font("Segoe UI", Font.BOLD, 50));
		logoPanel.add(lblGame);
		
		JLabel lblMiniLogo = new JLabel("");
		lblMiniLogo.setBounds(139, 83, 232, 248);
		lblMiniLogo.setIcon(new ImageIcon(LoginScreen.class.getResource("/Presentation/img/logoKakuro.png")));
		logoPanel.add(lblMiniLogo);
		
		lblAbout = new JLabel("About");
		lblAbout.setForeground(SystemColor.controlShadow);
		lblAbout.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblAbout.setHorizontalAlignment(SwingConstants.CENTER);
		lblAbout.setBounds(111, 406, 127, 55);
		logoPanel.add(lblAbout);
		lblAbout.addMouseListener(new MouseListener() {
			@Override
			public void mousePressed(MouseEvent e) {
				@SuppressWarnings("unused")
				AboutScreen about = new AboutScreen();
			}
			@Override
			public void mouseReleased(MouseEvent e) {
			}
			@Override
			public void mouseClicked(MouseEvent e) {
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				lblAbout.setText("<3");
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblAbout.setText("About");
			}
		});
		
	//login panel
		
		loginPanel = new JPanel();
		loginPanel.setBackground(Color.WHITE);
		loginPanel.setBounds(351, 0, 333, 461);
		contentPane.add(loginPanel);
		loginPanel.setLayout(null);
		
		JLabel lblUsername = new JLabel("USERNAME");
		lblUsername.setBounds(49, 93, 202, 46);
		loginPanel.add(lblUsername);
		lblUsername.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		
		userText = new JTextField();
		userText.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				userText.setBackground(Color.WHITE);
				passText.setBackground(Color.WHITE);
				lblMessage.setText("");
			}
		});
		userText.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		userText.setBounds(49, 130, 235, 35);
		userText.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		userText.setToolTipText("");
		userText.setColumns(10);
		userText.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				e.setKeyChar(Character.toLowerCase(c));
				if(userText.getText().length() >= 15 || (!Character.isAlphabetic(c) && !Character.isDigit(c) && !Character.isISOControl(c))) {
					e.consume();
				}
			}
		});
		loginPanel.add(userText);
		
		JLabel lblPass = new JLabel("PASSWORD");
		lblPass.setBounds(49, 168, 202, 46);
		loginPanel.add(lblPass);
		lblPass.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		
		passText = new JPasswordField();
		passText.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				userText.setBackground(Color.WHITE);
				passText.setBackground(Color.WHITE);
				lblMessage.setText("");
			}
		});
		passText.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		passText.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		passText.setToolTipText("");
		passText.setBounds(49, 206, 235, 34);
		passText.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				char c = e.getKeyChar();
				if(!Character.isSpaceChar(c)) {
					passText.setEditable(true);
				} else {
					passText.setEditable(false);
				}
			}
		});
		loginPanel.add(passText);
		
		btnLogin = new JButton("LOG IN");
		getRootPane().setDefaultButton(btnLogin);
		btnLogin.setFocusPainted(false);
		btnLogin.setBounds(208, 280, 77, 23);
		loginPanel.add(btnLogin);
		btnLogin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnLogin.setBackground(Color.DARK_GRAY);
				btnLogin.setForeground(Color.WHITE);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnLogin.setBackground(Color.WHITE);
				btnLogin.setForeground(Color.DARK_GRAY);
			}
		});
		btnLogin.setBorder(new MatteBorder(1,1,1,1,Color.DARK_GRAY));
		btnLogin.setBackground(Color.WHITE);
		btnLogin.setForeground(Color.DARK_GRAY);
		btnLogin.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		btnLogin.addActionListener(this);
		
		lblMessage = new JLabel("");
		lblMessage.setForeground(new Color(250, 128, 114));
		lblMessage.setHorizontalAlignment(SwingConstants.CENTER);
		lblMessage.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblMessage.setBounds(40, 380, 245, 35);
		loginPanel.add(lblMessage);
		
		JLabel lblRegister = new JLabel("Not registered?");
		lblRegister.setBackground(Color.WHITE);
		lblRegister.setHorizontalAlignment(SwingConstants.CENTER);
		lblRegister.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblRegister.setBounds(40, 345, 245, 35);
		lblRegister.addMouseListener(new MouseListener() {
			@Override
			public void mousePressed(MouseEvent e) {
				loginPanel.setVisible(false);
				registerPanel.setVisible(true);
				userRegText.setText("");
				userRegText.setBackground(Color.WHITE);
				passRegText.setText("");
				passRegText.setBackground(Color.WHITE);
				passRegText2.setText("");
				passRegText2.setBackground(Color.WHITE);
				lblMessage2.setText("");
				getRootPane().setDefaultButton(btnSignUp);
			}

			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				lblRegister.setText("Create my account >");
			}

			@Override
			public void mouseExited(MouseEvent e) {
				lblRegister.setText("Not registered?");
			}

			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		loginPanel.add(lblRegister);
		
		JLabel lblRedDot = new JLabel("");
		lblRedDot.setIcon(new ImageIcon(LoginScreen.class.getResource("/Presentation/img/redDot.png")));
		lblRedDot.setBounds(50, -415, 352, 795);
		loginPanel.add(lblRedDot);
		
	//register panel
		
		registerPanel = new JPanel();
		registerPanel.setBackground(Color.WHITE);
		registerPanel.setBounds(351, 0, 333, 461);
		registerPanel.setLayout(null);
		registerPanel.setVisible(false);
		contentPane.add(registerPanel);
		
		userRegText = new JTextField();
		userRegText.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		userRegText.setBounds(49, 108, 235, 35);
		userRegText.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		userRegText.setToolTipText("");
		userRegText.setColumns(10);
		userRegText.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				e.setKeyChar(Character.toLowerCase(c));
				if(userRegText.getText().length() >= 15 || (!Character.isAlphabetic(c) && !Character.isDigit(c) && !Character.isISOControl(c))) {
					e.consume();
				}
			}
		});
		registerPanel.add(userRegText);
		
		JLabel lblRegUsername = new JLabel("USERNAME");
		lblRegUsername.setBounds(49, 71, 202, 46);
		lblRegUsername.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		registerPanel.add(lblRegUsername);
		
		JLabel lblRegPass = new JLabel("PASSWORD");
		lblRegPass.setBounds(49, 143, 202, 46);
		lblRegPass.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		registerPanel.add(lblRegPass);
		
		JLabel lblRegPass2 = new JLabel("CONFIRM PASSWORD");
		lblRegPass2.setBounds(49, 212, 202, 46);
		lblRegPass2.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		registerPanel.add(lblRegPass2);
		
		passRegText = new JPasswordField();
		passRegText.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		passRegText.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		passRegText.setBounds(49, 178, 235, 34);
		passRegText.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				char c = e.getKeyChar();
				if(!Character.isSpaceChar(c)) {
					passRegText.setEditable(true);
				} else {
					passRegText.setEditable(false);
				}
			}
		});
		passRegText.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				passRegText.setBackground(Color.WHITE);
				passRegText2.setBackground(Color.WHITE);
				lblMessage2.setText("");
			}
		});
		registerPanel.add(passRegText);
		
		passRegText2 = new JPasswordField();
		passRegText2.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		passRegText2.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		passRegText2.setBounds(49, 248, 235, 34);
		passRegText2.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				char c = e.getKeyChar();
				if(!Character.isSpaceChar(c)) {
					passRegText2.setEditable(true);
				} else {
					passRegText2.setEditable(false);
				}
			}
		});
		passRegText2.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				passRegText.setBackground(Color.WHITE);
				passRegText2.setBackground(Color.WHITE);
				lblMessage2.setText("");
			}
		});
		registerPanel.add(passRegText2);
		
		btnSignUp = new JButton("SIGN UP");
		btnSignUp.setFocusPainted(false);
		btnSignUp.setBounds(199, 319, 85, 23);
		btnSignUp.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnSignUp.setBackground(Color.DARK_GRAY);
				btnSignUp.setForeground(Color.WHITE);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnSignUp.setBackground(Color.WHITE);
				btnSignUp.setForeground(Color.DARK_GRAY);
			}
		});
		btnSignUp.setBorder(new MatteBorder(1,1,1,1,Color.DARK_GRAY));
		btnSignUp.setBackground(Color.WHITE);
		btnSignUp.setForeground(Color.DARK_GRAY);
		btnSignUp.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		btnSignUp.addActionListener(this);
		registerPanel.add(btnSignUp);
		
		btnBack = new JButton("BACK");
		btnBack.setFocusPainted(false);
		btnBack.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		btnBack.setBounds(49, 319, 85, 23);
		btnBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnBack.setBackground(Color.DARK_GRAY);
				btnBack.setForeground(Color.WHITE);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnBack.setBackground(Color.WHITE);
				btnBack.setForeground(Color.DARK_GRAY);
			}
		});
		btnBack.setBorder(new MatteBorder(1,1,1,1,Color.DARK_GRAY));
		btnBack.setBackground(Color.WHITE);
		btnBack.setForeground(Color.DARK_GRAY);
		btnBack.addActionListener(this);
		registerPanel.add(btnBack);
		
		lblMessage2 = new JLabel("");
		lblMessage2.setForeground(new Color(250, 128, 114));
		lblMessage2.setHorizontalAlignment(SwingConstants.CENTER);
		lblMessage2.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblMessage2.setBounds(49, 369, 235, 35);
		registerPanel.add(lblMessage2);

		JLabel lblRedDot2 = new JLabel("");
		lblRedDot2.setIcon(new ImageIcon(LoginScreen.class.getResource("/Presentation/img/redDot.png")));
		lblRedDot2.setBounds(50, -415, 352, 795);
		registerPanel.add(lblRedDot2);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnLogin) {
			String pass = new String(passText.getPassword());
			String username = userText.getText();
			if(CP.checkPassword(username, pass)) {
				@SuppressWarnings("unused")
				MainScreen MN = new MainScreen(CP,username);
				dispose();
			} else {
				lblMessage.setText("Incorrect username or password");
				userText.setBackground(new Color(250, 128, 114));
				passText.setBackground(new Color(250, 128, 114));
			}
		}
		else if(e.getSource() == btnSignUp) {
			userRegText.setBackground(Color.WHITE);
			passRegText.setBackground(Color.WHITE);
			passRegText2.setBackground(Color.WHITE);
			String pass = new String(passRegText.getPassword());
			String pass2 = new String(passRegText2.getPassword());
			if(!pass.equals(pass2)) {
				lblMessage2.setText("Passwords do not match");
				passRegText.setBackground(new Color(250, 128, 114));
				passRegText2.setBackground(new Color(250, 128, 114));
			}
			else {
				String username = userRegText.getText();
				if(username.isBlank() || pass.isBlank()) {
					lblMessage2.setText("Please fill in all register information");
				} else {
					boolean correct = false;
					try {
						correct = CP.addUser(userRegText.getText(), pass);
						if(correct) {
							@SuppressWarnings("unused")
							MainScreen MN = new MainScreen(CP,username);
							dispose();
						} else {
							lblMessage2.setText("Username is taken");
							userRegText.setBackground(new Color(250, 128, 114));
						}
					} catch (ExceptionShortPassword e1) {
						passRegText.setBackground(new Color(250, 128, 114));
						passRegText2.setBackground(new Color(250, 128, 114));
						lblMessage2.setText(e1.getMessage());
					}
				}
			}
		}
		else if(e.getSource() == btnBack) {
			registerPanel.setVisible(false);
			loginPanel.setVisible(true);
			userText.setText("");
			userText.setBackground(Color.WHITE);
			passText.setText("");
			passText.setBackground(Color.WHITE);
			lblMessage.setText("");
			getRootPane().setDefaultButton(btnLogin);
		}
	}
}
