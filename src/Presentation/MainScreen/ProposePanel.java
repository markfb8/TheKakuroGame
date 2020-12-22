/** @file ProposePanel.java */
package Presentation.MainScreen;

/** @class ProposePanel
 */

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;

import Domain.Controllers.CtrlGame;
import Presentation.CtrlPresentation;

@SuppressWarnings("serial")
public class ProposePanel extends JPanel implements ActionListener {

	@SuppressWarnings("unused")
	private CtrlPresentation CP;
	private CtrlGame CG;
	@SuppressWarnings("unused")
	private MainScreen father;
	
	private JLabel lblMessage;
	private JTextField textRows;
	private JTextField textCols;
	private JTextArea textArea;
	private JButton btnValidate;
	private JButton btnHelp;

	public ProposePanel(CtrlPresentation CP, MainScreen window, String username) {		
		father = window;
		this.CP = CP;
		CG = CP.createGame(username);
		initialize();
	}
	
	private void initialize() {
		this.setBackground(Color.WHITE);
		SpringLayout sl_this = new SpringLayout();
		this.setLayout(sl_this);
		
		JPanel optPanel = new JPanel();
		optPanel.setBackground(Color.DARK_GRAY);
		sl_this.putConstraint(SpringLayout.NORTH, optPanel, 120, SpringLayout.NORTH, this);
		sl_this.putConstraint(SpringLayout.WEST, optPanel, 100, SpringLayout.WEST, this);
		sl_this.putConstraint(SpringLayout.SOUTH, optPanel, -120, SpringLayout.SOUTH, this);
		sl_this.putConstraint(SpringLayout.EAST, optPanel, -100, SpringLayout.EAST, this);
		this.add(optPanel);
		SpringLayout sl_optPanel = new SpringLayout();
		optPanel.setLayout(sl_optPanel); 
		
		JLabel lblValidate = new JLabel("VALIDATE");
		sl_this.putConstraint(SpringLayout.SOUTH, lblValidate, 17, SpringLayout.NORTH, optPanel);
		sl_this.putConstraint(SpringLayout.WEST, lblValidate, 95, SpringLayout.WEST, this);
		lblValidate.setIcon(new ImageIcon(MainScreen.class.getResource("/Presentation/img/iconRedDot.png")));
		lblValidate.setForeground(Color.DARK_GRAY);
		lblValidate.setFont(new Font("Segoe UI", Font.BOLD, 60));
		this.add(lblValidate);
		
		JLabel lblSize = new JLabel("SIZE:");
		sl_optPanel.putConstraint(SpringLayout.NORTH, lblSize, 40, SpringLayout.NORTH, optPanel);
		sl_optPanel.putConstraint(SpringLayout.WEST, lblSize, 60, SpringLayout.WEST, optPanel);
		lblSize.setBackground(Color.WHITE);
		lblSize.setHorizontalAlignment(SwingConstants.CENTER);
		lblSize.setForeground(Color.WHITE);
		lblSize.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		optPanel.add(lblSize);
		
		textRows = new JTextField();
		sl_optPanel.putConstraint(SpringLayout.NORTH, textRows, 30, SpringLayout.NORTH, optPanel);
		sl_optPanel.putConstraint(SpringLayout.WEST, textRows, 20, SpringLayout.EAST, lblSize);
		textRows.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		textRows.setPreferredSize(new Dimension(20, 40));
		optPanel.add(textRows);
		textRows.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				char c = e.getKeyChar();
				if(!Character.isDigit(c) && !Character.isISOControl(c)) {
					textRows.setEditable(false);
				} else {
					textRows.setEditable(true);
				}
			}
		});
		textRows.setColumns(10);
		
		JLabel lblx = new JLabel("x");
		sl_optPanel.putConstraint(SpringLayout.NORTH, lblx, 40, SpringLayout.NORTH, optPanel);
		sl_optPanel.putConstraint(SpringLayout.WEST, lblx, 20, SpringLayout.EAST, textRows);
		lblx.setForeground(Color.WHITE);
		lblx.setFont(new Font("Segoe UI", Font.BOLD, 20));
		optPanel.add(lblx);
		
		textCols = new JTextField();
		sl_optPanel.putConstraint(SpringLayout.NORTH, textCols, 30, SpringLayout.NORTH, optPanel);
		sl_optPanel.putConstraint(SpringLayout.WEST, textCols, 20, SpringLayout.EAST, lblx);
		textCols.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		textCols.setPreferredSize(new Dimension(20, 40));
		textCols.setColumns(10);
		textCols.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				char c = e.getKeyChar();
				if(!Character.isDigit(c) && !Character.isISOControl(c)) {
					textCols.setEditable(false);
				} else {
					textCols.setEditable(true);
				}
			}
		});
		optPanel.add(textCols);
		
		textArea = new JTextArea();
		textArea.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		JScrollPane scrollPane = new JScrollPane();
		sl_optPanel.putConstraint(SpringLayout.NORTH, scrollPane, 100, SpringLayout.NORTH, optPanel);
		sl_optPanel.putConstraint(SpringLayout.WEST, scrollPane, 60, SpringLayout.WEST, optPanel);
		sl_optPanel.putConstraint(SpringLayout.EAST, scrollPane, -60, SpringLayout.EAST, optPanel);
		sl_optPanel.putConstraint(SpringLayout.SOUTH, scrollPane, -130, SpringLayout.SOUTH, optPanel);
		scrollPane.setViewportView(textArea);
		optPanel.add(scrollPane);
		
		btnValidate = new JButton("VALIDATE");
		btnValidate.setPreferredSize(new Dimension(100,40));
		btnValidate.setFocusPainted(false);
		sl_optPanel.putConstraint(SpringLayout.SOUTH, btnValidate, -40, SpringLayout.SOUTH, optPanel);
		sl_optPanel.putConstraint(SpringLayout.EAST, btnValidate, -60, SpringLayout.EAST, optPanel);
		btnValidate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnValidate.setBackground(Color.WHITE);
				btnValidate.setForeground(Color.BLACK);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnValidate.setBackground(Color.DARK_GRAY);
				btnValidate.setForeground(Color.WHITE);
			}
		});
		btnValidate.setBorder(new MatteBorder(1,1,1,1,Color.WHITE));
		btnValidate.setBackground(Color.DARK_GRAY);
		btnValidate.setForeground(Color.WHITE);
		btnValidate.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		btnValidate.addActionListener(this);
		optPanel.add(btnValidate);
		
		btnHelp = new JButton("NEED HELP?");
		btnHelp.setPreferredSize(new Dimension(100,40));
		btnHelp.setFocusPainted(false);
		sl_optPanel.putConstraint(SpringLayout.SOUTH, btnHelp, -40, SpringLayout.SOUTH, optPanel);
		sl_optPanel.putConstraint(SpringLayout.WEST, btnHelp, 60, SpringLayout.WEST, optPanel);
		btnHelp.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnHelp.setBackground(Color.WHITE);
				btnHelp.setForeground(Color.BLACK);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnHelp.setBackground(Color.DARK_GRAY);
				btnHelp.setForeground(Color.WHITE);
			}
		});
		btnHelp.setBorder(new MatteBorder(1,1,1,1,Color.WHITE));
		btnHelp.setBackground(Color.DARK_GRAY);
		btnHelp.setForeground(Color.WHITE);
		btnHelp.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		btnHelp.addActionListener(this);
		optPanel.add(btnHelp);
		
		lblMessage = new JLabel("");
		lblMessage.setForeground(new Color(250, 128, 114));
		lblMessage.setHorizontalAlignment(SwingConstants.CENTER);
		lblMessage.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		sl_optPanel.putConstraint(SpringLayout.SOUTH, lblMessage, -95, SpringLayout.SOUTH, optPanel);
		sl_optPanel.putConstraint(SpringLayout.WEST, lblMessage, 57, SpringLayout.WEST, optPanel);
		optPanel.add(lblMessage);
		
		JLabel lblBG = new JLabel("");
		lblBG.setIcon(new ImageIcon(MainScreen.class.getResource("/Presentation/img/blankPageBG.png")));
		sl_this.putConstraint(SpringLayout.SOUTH, lblBG, 0, SpringLayout.SOUTH, this);
		sl_this.putConstraint(SpringLayout.EAST, lblBG, 0, SpringLayout.EAST, this);
		this.add(lblBG);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnHelp) {
			@SuppressWarnings("unused")
			ProposeInstructions pi = new ProposeInstructions();
		}
		else {
			String rows = textRows.getText();
			String cols = textCols.getText();
			if(rows.isBlank() || cols.isBlank()) {
				lblMessage.setText("Please indicate a size");
			} else {
				int r = Integer.parseInt(textRows.getText());
				int c = Integer.parseInt(textCols.getText());
				ArrayList<String> board = new ArrayList<String>();
				board.add(r+","+c);
				for (String line : textArea.getText().split("\\n")) board.add(line);
				try {
					String level = CG.proposeGame(board);
					textRows.setText("");
					textCols.setText("");
					textArea.setText("");
					lblMessage.setText("Your kakuro was submitted! It was classified as: "+level);
				} catch (Exception e1) {
					lblMessage.setText(e1.getMessage());
				}
			}
		}
	}

}
