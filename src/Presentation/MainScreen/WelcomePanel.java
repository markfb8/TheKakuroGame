/** @file WelcomePanel.java */
package Presentation.MainScreen;

/** @class WelcomePanel
 */

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

@SuppressWarnings("serial")
public class WelcomePanel extends JPanel {

	private String username;
	
	public WelcomePanel(String user) {
		username = user;
		initialize();
	}

	private void initialize() {
		setPreferredSize(new Dimension(700,700));
		this.setBackground(Color.WHITE);
		SpringLayout sl_this = new SpringLayout();
		this.setLayout(sl_this);
		
		JLabel lblHello = new JLabel("Hello");
		lblHello.setPreferredSize(new Dimension(400,150));
		lblHello.setForeground(Color.DARK_GRAY);
		sl_this.putConstraint(SpringLayout.NORTH, lblHello, 440, SpringLayout.NORTH, this);
		sl_this.putConstraint(SpringLayout.WEST, lblHello, 40, SpringLayout.WEST, this);
		lblHello.setFont(new Font("Segoe UI", Font.BOLD, 80));
		this.add(lblHello);
		
		JLabel lblName = new JLabel(username+".");
		lblName.setPreferredSize(new Dimension(500,100));
		lblName.setForeground(Color.DARK_GRAY);
		sl_this.putConstraint(SpringLayout.NORTH, lblName, 530, SpringLayout.NORTH, this);
		sl_this.putConstraint(SpringLayout.WEST, lblName, 45, SpringLayout.WEST, this);
		lblName.setFont(new Font("Segoe UI", Font.BOLD, 40));
		this.add(lblName);
		
		JLabel lblSamurai = new JLabel("");
		lblSamurai.setIcon(new ImageIcon(WelcomePanel.class.getResource("/Presentation/img/graySamurai.png")));
		sl_this.putConstraint(SpringLayout.SOUTH, lblSamurai, 100, SpringLayout.SOUTH, this);
		sl_this.putConstraint(SpringLayout.EAST, lblSamurai, 50, SpringLayout.EAST, this);
		this.add(lblSamurai);
		
		JLabel lblBG = new JLabel("");
		lblBG.setIcon(new ImageIcon(WelcomePanel.class.getResource("/Presentation/img/redDot.png")));
		sl_this.putConstraint(SpringLayout.SOUTH, lblBG, -100, SpringLayout.SOUTH, this);
		sl_this.putConstraint(SpringLayout.EAST, lblBG, 250, SpringLayout.EAST, this);
		this.add(lblBG);
		
	}
}
