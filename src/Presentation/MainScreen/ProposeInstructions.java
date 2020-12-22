/** @file ProposeInstructions.java */
package Presentation.MainScreen;

/** @class ProposeInstructions
 */

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Presentation.LoginScreen.AboutScreen;
import javax.swing.ImageIcon;

@SuppressWarnings("serial")
public class ProposeInstructions extends JFrame {

	private JPanel contentPane;

	public ProposeInstructions() {
		initialize();
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	private void initialize() {
		setResizable(false);
		setPreferredSize(new Dimension(500,350));
		setTitle("Propose instructions");
		setIconImage(Toolkit.getDefaultToolkit().getImage(AboutScreen.class.getResource("/Presentation/img/frameLogo.png")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblInstr = new JLabel();
		lblInstr.setIcon(new ImageIcon(ProposeInstructions.class.getResource("/Presentation/img/proposeInstructions.png")));
		lblInstr.setBounds(0, 0, 484, 311);
		contentPane.add(lblInstr);
	}

}
