/** @file GeneratePanel.java */
package Presentation.MainScreen;

/** @class GeneratePanel
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

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;

import Domain.Controllers.CtrlGame;
import Exceptions.ExceptionInvalidSize;
import Presentation.CtrlPresentation;

@SuppressWarnings("serial")
public class GeneratePanel extends JPanel implements ActionListener {

	@SuppressWarnings("unused")
	private CtrlPresentation CP;
	private CtrlGame CG;
	private MainScreen father;
	
	private JTextField textRows;
	private JTextField textCols;
	private JLabel lblMessage;
	private JComboBox<String> comboBox;

	public GeneratePanel(CtrlPresentation CP, MainScreen window, String username) {
		father = window;
		this.CP = CP;
		CG = CP.createGame(username);
		initialize();
	}

	private void initialize() {
		setPreferredSize(new Dimension(700,700));
		this.setBackground(Color.WHITE);
		SpringLayout sl_this = new SpringLayout();
		this.setLayout(sl_this);
		
		JPanel optPanel = new JPanel();
		optPanel.setPreferredSize(new Dimension(500,300));
		sl_this.putConstraint(SpringLayout.NORTH, optPanel, 200, SpringLayout.NORTH, this);
		sl_this.putConstraint(SpringLayout.WEST, optPanel, 100, SpringLayout.WEST, this);
		optPanel.setBackground(Color.DARK_GRAY);
		this.add(optPanel);
		SpringLayout sl_optPanel = new SpringLayout();
		optPanel.setLayout(sl_optPanel);
		
		JLabel lblGen = new JLabel("GENERATE");
		sl_this.putConstraint(SpringLayout.SOUTH, lblGen, 17, SpringLayout.NORTH, optPanel);
		sl_this.putConstraint(SpringLayout.WEST, lblGen, 95, SpringLayout.WEST, this);
		lblGen.setIcon(new ImageIcon(MainScreen.class.getResource("/Presentation/img/iconRedDot.png")));
		lblGen.setForeground(Color.DARK_GRAY);
		lblGen.setFont(new Font("Segoe UI", Font.BOLD, 60));
		this.add(lblGen);
		
		JLabel lblSize = new JLabel("SIZE:");
		sl_optPanel.putConstraint(SpringLayout.NORTH, lblSize, 70, SpringLayout.NORTH, optPanel);
		sl_optPanel.putConstraint(SpringLayout.WEST, lblSize, 60, SpringLayout.WEST, optPanel);
		lblSize.setBackground(Color.WHITE);
		lblSize.setHorizontalAlignment(SwingConstants.CENTER);
		lblSize.setForeground(Color.WHITE);
		lblSize.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		optPanel.add(lblSize);
		
		textRows = new JTextField();
		sl_optPanel.putConstraint(SpringLayout.NORTH, textRows, 60, SpringLayout.NORTH, optPanel);
		sl_optPanel.putConstraint(SpringLayout.WEST, textRows, 40, SpringLayout.EAST, lblSize);
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
		sl_optPanel.putConstraint(SpringLayout.NORTH, lblx, 70, SpringLayout.NORTH, optPanel);
		sl_optPanel.putConstraint(SpringLayout.WEST, lblx, 20, SpringLayout.EAST, textRows);
		lblx.setForeground(Color.WHITE);
		lblx.setFont(new Font("Segoe UI", Font.BOLD, 20));
		optPanel.add(lblx);
		
		textCols = new JTextField();
		sl_optPanel.putConstraint(SpringLayout.NORTH, textCols, 60, SpringLayout.NORTH, optPanel);
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
		
		JLabel lblLevel = new JLabel("LEVEL:");
		sl_optPanel.putConstraint(SpringLayout.NORTH, lblLevel, 140, SpringLayout.NORTH, optPanel);
		sl_optPanel.putConstraint(SpringLayout.WEST, lblLevel, 60, SpringLayout.WEST, optPanel);
		lblLevel.setBackground(Color.WHITE);
		lblLevel.setHorizontalAlignment(SwingConstants.CENTER);
		lblLevel.setForeground(Color.WHITE);
		lblLevel.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		optPanel.add(lblLevel);

		comboBox = new JComboBox<String>();
		comboBox.setForeground(Color.DARK_GRAY);
		comboBox.setFocusable(false);
		sl_optPanel.putConstraint(SpringLayout.NORTH, comboBox, 140, SpringLayout.NORTH, optPanel);
		sl_optPanel.putConstraint(SpringLayout.WEST, comboBox, 25, SpringLayout.EAST, lblLevel);
		comboBox.setBorder(new MatteBorder(1, 1, 1, 1, (Color) Color.WHITE));
		comboBox.setBackground(Color.WHITE);
		comboBox.setPreferredSize(new Dimension(300, 30));
		comboBox.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"EASY", "MEDIUM", "HARD"}));
		optPanel.add(comboBox);
		
		JButton btnCreate = new JButton("CREATE");
		btnCreate.setPreferredSize(new Dimension(100,40));
		btnCreate.setFocusPainted(false);
		sl_optPanel.putConstraint(SpringLayout.SOUTH, btnCreate, -40, SpringLayout.SOUTH, optPanel);
		sl_optPanel.putConstraint(SpringLayout.EAST, btnCreate, -60, SpringLayout.EAST, optPanel);
		btnCreate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnCreate.setBackground(Color.WHITE);
				btnCreate.setForeground(Color.BLACK);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnCreate.setBackground(Color.DARK_GRAY);
				btnCreate.setForeground(Color.WHITE);
			}
		});
		btnCreate.setBorder(new MatteBorder(1,1,1,1,Color.WHITE));
		btnCreate.setBackground(Color.DARK_GRAY);
		btnCreate.setForeground(Color.WHITE);
		btnCreate.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		btnCreate.addActionListener(this);
		optPanel.add(btnCreate);
		
		lblMessage = new JLabel("");
		lblMessage.setForeground(new Color(250, 128, 114));
		lblMessage.setHorizontalAlignment(SwingConstants.CENTER);
		lblMessage.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		sl_optPanel.putConstraint(SpringLayout.SOUTH, lblMessage, -50, SpringLayout.SOUTH, optPanel);
		sl_optPanel.putConstraint(SpringLayout.WEST, lblMessage, 60, SpringLayout.WEST, optPanel);
		optPanel.add(lblMessage);
		
		JLabel lblGenBg = new JLabel("");
		lblGenBg.setIcon(new ImageIcon(MainScreen.class.getResource("/Presentation/img/diceBG.png")));
		sl_this.putConstraint(SpringLayout.SOUTH, lblGenBg, 0, SpringLayout.SOUTH, this);
		sl_this.putConstraint(SpringLayout.EAST, lblGenBg, 0, SpringLayout.EAST, this);
		this.add(lblGenBg);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		lblMessage.setText("");
		String rows = textRows.getText();
		String cols = textCols.getText();
		if(rows.isBlank() || cols.isBlank()) {
			lblMessage.setText("Please indicate a size");
		} else {
			int r = Integer.parseInt(textRows.getText());
			int c = Integer.parseInt(textCols.getText());
			String level = (String)comboBox.getSelectedItem();
			try {
				CG.generateGame(r,c,level);
			} catch (ExceptionInvalidSize e1) {
				lblMessage.setText(e1.getMessage());
				return;
			}
			father.setPlayPanel(CG);
		}
	}
}
