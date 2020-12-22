/** @file PlayPanel.java */
package Presentation.MainScreen.PlayPanel;

/** @class PlayPanel
 */

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.MatteBorder;

import Domain.Controllers.CtrlGame;
import Presentation.MainScreen.MainScreen;

@SuppressWarnings("serial")
public class PlayPanel extends JPanel implements ActionListener{

	private CtrlGame CG;
	private KakuroPanel KP;
	private MainScreen father;
	
	private JButton btn1;
	private JButton btn2;
	private JButton btn3;
	private JButton btn4;
	private JButton btn5;
	private JButton btn6;
	private JButton btn7;
	private JButton btn8;
	private JButton btn9;
	private JButton btnX;
	
	private JLabel lblHints;
	private JLabel lblTimer;
	private Timer timer;
	private static final DecimalFormat dFormat = new DecimalFormat("00");
	private int seconds;
	private int minutes;
	
	public PlayPanel(CtrlGame CG, MainScreen window) {
		father = window;
		this.CG = CG;
		initialize();
	}
	
	private void initialize() {
		this.setPreferredSize(new Dimension(700, 700));
		this.setBackground(Color.WHITE);
		SpringLayout sl_this = new SpringLayout();
		this.setLayout(sl_this);
		
		KP = new KakuroPanel(CG,550,false);
		sl_this.putConstraint(SpringLayout.WEST, KP, 75, SpringLayout.WEST, this);
		sl_this.putConstraint(SpringLayout.NORTH, KP, 0, SpringLayout.NORTH, this);
		this.add(KP);
		
		JPanel numsPanel = new JPanel();
		numsPanel.setBackground(Color.DARK_GRAY);
		numsPanel.setPreferredSize(new Dimension(340,110));
		sl_this.putConstraint(SpringLayout.NORTH, numsPanel, 0, SpringLayout.SOUTH, KP);
		sl_this.putConstraint(SpringLayout.WEST, numsPanel, 180, SpringLayout.WEST, this);
		this.add(numsPanel);
		SpringLayout sl_numsPanel = new SpringLayout();
		numsPanel.setLayout(sl_numsPanel);
		
		btn3 = new JButton("3");
		btn3.setFocusPainted(false);
		btn3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btn3.setBackground(Color.WHITE);
				btn3.setForeground(Color.BLACK);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btn3.setBackground(Color.DARK_GRAY);
				btn3.setForeground(Color.WHITE);
			}
		});
		btn3.setBorder(new MatteBorder(1,1,1,1,Color.WHITE));
		btn3.setBackground(Color.DARK_GRAY);
		btn3.setForeground(Color.WHITE);
		btn3.setPreferredSize(new Dimension(40,40));
		sl_numsPanel.putConstraint(SpringLayout.NORTH, btn3, 10, SpringLayout.NORTH, numsPanel);
		sl_numsPanel.putConstraint(SpringLayout.WEST, btn3, 150, SpringLayout.WEST, numsPanel);
		btn3.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		btn3.addActionListener(this);
		numsPanel.add(btn3);
		
		btn2 = new JButton("2");
		btn2.setFocusPainted(false);
		btn2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btn2.setBackground(Color.WHITE);
				btn2.setForeground(Color.BLACK);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btn2.setBackground(Color.DARK_GRAY);
				btn2.setForeground(Color.WHITE);
			}
		});
		btn2.setBorder(new MatteBorder(1,1,1,1,Color.WHITE));
		btn2.setBackground(Color.DARK_GRAY);
		btn2.setForeground(Color.WHITE);
		btn2.setPreferredSize(new Dimension(40,40));
		sl_numsPanel.putConstraint(SpringLayout.NORTH, btn2, 10, SpringLayout.NORTH, numsPanel);
		sl_numsPanel.putConstraint(SpringLayout.EAST, btn2, -20, SpringLayout.WEST, btn3);
		btn2.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		btn2.addActionListener(this);
		numsPanel.add(btn2);
		
		btn1 = new JButton("1");
		btn1.setFocusPainted(false);
		btn1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btn1.setBackground(Color.WHITE);
				btn1.setForeground(Color.BLACK);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btn1.setBackground(Color.DARK_GRAY);
				btn1.setForeground(Color.WHITE);
			}
		});
		btn1.setBorder(new MatteBorder(1,1,1,1,Color.WHITE));
		btn1.setBackground(Color.DARK_GRAY);
		btn1.setForeground(Color.WHITE);
		btn1.setPreferredSize(new Dimension(40,40));
		sl_numsPanel.putConstraint(SpringLayout.NORTH, btn1, 10, SpringLayout.NORTH, numsPanel);
		sl_numsPanel.putConstraint(SpringLayout.EAST, btn1, -20, SpringLayout.WEST, btn2);
		btn1.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		btn1.addActionListener(this);
		numsPanel.add(btn1);
		
		btn4 = new JButton("4");
		btn4.setFocusPainted(false);
		btn4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btn4.setBackground(Color.WHITE);
				btn4.setForeground(Color.BLACK);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btn4.setBackground(Color.DARK_GRAY);
				btn4.setForeground(Color.WHITE);
			}
		});
		btn4.setBorder(new MatteBorder(1,1,1,1,Color.WHITE));
		btn4.setBackground(Color.DARK_GRAY);
		btn4.setForeground(Color.WHITE);
		btn4.setPreferredSize(new Dimension(40,40));
		sl_numsPanel.putConstraint(SpringLayout.NORTH, btn4, 10, SpringLayout.NORTH, numsPanel);
		sl_numsPanel.putConstraint(SpringLayout.WEST, btn4, 20, SpringLayout.EAST, btn3);
		btn4.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		btn4.addActionListener(this);
		numsPanel.add(btn4);
		
		btn5 = new JButton("5");
		btn5.setFocusPainted(false);
		btn5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btn5.setBackground(Color.WHITE);
				btn5.setForeground(Color.BLACK);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btn5.setBackground(Color.DARK_GRAY);
				btn5.setForeground(Color.WHITE);
			}
		});
		btn5.setBorder(new MatteBorder(1,1,1,1,Color.WHITE));
		btn5.setBackground(Color.DARK_GRAY);
		btn5.setForeground(Color.WHITE);
		btn5.setPreferredSize(new Dimension(40,40));
		sl_numsPanel.putConstraint(SpringLayout.NORTH, btn5, 10, SpringLayout.NORTH, numsPanel);
		sl_numsPanel.putConstraint(SpringLayout.WEST, btn5, 20, SpringLayout.EAST, btn4);
		btn5.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		btn5.addActionListener(this);
		numsPanel.add(btn5);
		
		btn8 = new JButton("8");
		btn8.setFocusPainted(false);
		btn8.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btn8.setBackground(Color.WHITE);
				btn8.setForeground(Color.BLACK);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btn8.setBackground(Color.DARK_GRAY);
				btn8.setForeground(Color.WHITE);
			}
		});
		btn8.setBorder(new MatteBorder(1,1,1,1,Color.WHITE));
		btn8.setBackground(Color.DARK_GRAY);
		btn8.setForeground(Color.WHITE);
		btn8.setPreferredSize(new Dimension(40,40));
		sl_numsPanel.putConstraint(SpringLayout.SOUTH, btn8, -10, SpringLayout.SOUTH, numsPanel);
		sl_numsPanel.putConstraint(SpringLayout.WEST, btn8, 150, SpringLayout.WEST, numsPanel);
		btn8.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		btn8.addActionListener(this);
		numsPanel.add(btn8);
		
		btn7 = new JButton("7");
		btn7.setFocusPainted(false);
		btn7.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btn7.setBackground(Color.WHITE);
				btn7.setForeground(Color.BLACK);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btn7.setBackground(Color.DARK_GRAY);
				btn7.setForeground(Color.WHITE);
			}
		});
		btn7.setBorder(new MatteBorder(1,1,1,1,Color.WHITE));
		btn7.setBackground(Color.DARK_GRAY);
		btn7.setForeground(Color.WHITE);
		btn7.setPreferredSize(new Dimension(40,40));
		sl_numsPanel.putConstraint(SpringLayout.SOUTH, btn7, -10, SpringLayout.SOUTH, numsPanel);
		sl_numsPanel.putConstraint(SpringLayout.EAST, btn7, -20, SpringLayout.WEST, btn8);
		btn7.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		btn7.addActionListener(this);
		numsPanel.add(btn7);
		
		btn6 = new JButton("6");
		btn6.setFocusPainted(false);
		btn6.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btn6.setBackground(Color.WHITE);
				btn6.setForeground(Color.BLACK);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btn6.setBackground(Color.DARK_GRAY);
				btn6.setForeground(Color.WHITE);
			}
		});
		btn6.setBorder(new MatteBorder(1,1,1,1,Color.WHITE));
		btn6.setBackground(Color.DARK_GRAY);
		btn6.setForeground(Color.WHITE);
		btn6.setPreferredSize(new Dimension(40,40));
		sl_numsPanel.putConstraint(SpringLayout.SOUTH, btn6, -10, SpringLayout.SOUTH, numsPanel);
		sl_numsPanel.putConstraint(SpringLayout.EAST, btn6, -20, SpringLayout.WEST, btn7);
		btn6.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		btn6.addActionListener(this);
		numsPanel.add(btn6);
		
		btn9 = new JButton("9");
		btn9.setFocusPainted(false);
		btn9.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btn9.setBackground(Color.WHITE);
				btn9.setForeground(Color.BLACK);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btn9.setBackground(Color.DARK_GRAY);
				btn9.setForeground(Color.WHITE);
			}
		});
		btn9.setBorder(new MatteBorder(1,1,1,1,Color.WHITE));
		btn9.setBackground(Color.DARK_GRAY);
		btn9.setForeground(Color.WHITE);
		btn9.setPreferredSize(new Dimension(40,40));
		sl_numsPanel.putConstraint(SpringLayout.SOUTH, btn9, -10, SpringLayout.SOUTH, numsPanel);
		sl_numsPanel.putConstraint(SpringLayout.WEST, btn9, 20, SpringLayout.EAST, btn8);
		btn9.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		btn9.addActionListener(this);
		numsPanel.add(btn9);
		
		btnX = new JButton("X");
		btnX.setFocusPainted(false);
		btnX.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnX.setBackground(Color.WHITE);
				btnX.setForeground(Color.BLACK);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnX.setBackground(Color.DARK_GRAY);
				btnX.setForeground(Color.WHITE);
			}
		});
		btnX.setBorder(new MatteBorder(1,1,1,1,Color.WHITE));
		btnX.setBackground(Color.DARK_GRAY);
		btnX.setForeground(Color.WHITE);
		btnX.setPreferredSize(new Dimension(40,40));
		sl_numsPanel.putConstraint(SpringLayout.SOUTH, btnX, -10, SpringLayout.SOUTH, numsPanel);
		sl_numsPanel.putConstraint(SpringLayout.WEST, btnX, 20, SpringLayout.EAST, btn9);
		btnX.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		btnX.addActionListener(this);
		numsPanel.add(btnX);
		
		JLabel lblHints2 = new JLabel("Hints");
		sl_this.putConstraint(SpringLayout.NORTH, lblHints2, 0, SpringLayout.NORTH, numsPanel);
		sl_this.putConstraint(SpringLayout.EAST, lblHints2, -10, SpringLayout.WEST, numsPanel);
		lblHints2.setPreferredSize(new Dimension(150,70));
		lblHints2.setHorizontalAlignment(SwingConstants.CENTER);
		lblHints2.setForeground(Color.DARK_GRAY);
		lblHints2.setFont(new Font("Segoe UI", Font.BOLD, 20));
		this.add(lblHints2);
		
		lblHints = new JLabel(String.valueOf(CG.getHints()));
		sl_this.putConstraint(SpringLayout.NORTH, lblHints, 30, SpringLayout.NORTH, numsPanel);
		sl_this.putConstraint(SpringLayout.EAST, lblHints, -10, SpringLayout.WEST, numsPanel);
		lblHints.setPreferredSize(new Dimension(150,70));
		lblHints.setHorizontalAlignment(SwingConstants.CENTER);
		lblHints.setForeground(Color.DARK_GRAY);
		lblHints.setFont(new Font("Segoe UI", Font.BOLD, 40));
		this.add(lblHints);
		
		seconds = 0;
		minutes = 0;
		timer = new Timer(1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				++seconds;
				if(seconds == 59) {
					seconds = 0;
					++minutes;
				}
				lblTimer.setText(dFormat.format(minutes)+":"+dFormat.format(seconds));
			}
		});
		timer.start();
		
		JLabel lblTimer2 = new JLabel("Time");
		sl_this.putConstraint(SpringLayout.NORTH, lblTimer2, 0, SpringLayout.NORTH, numsPanel);
		sl_this.putConstraint(SpringLayout.WEST, lblTimer2, 10, SpringLayout.EAST, numsPanel);
		lblTimer2.setPreferredSize(new Dimension(150,70));
		lblTimer2.setHorizontalAlignment(SwingConstants.CENTER);
		lblTimer2.setForeground(Color.DARK_GRAY);
		lblTimer2.setFont(new Font("Segoe UI", Font.BOLD, 20));
		this.add(lblTimer2);
		
		lblTimer = new JLabel(dFormat.format(minutes)+":"+dFormat.format(seconds));
		sl_this.putConstraint(SpringLayout.NORTH, lblTimer, 30, SpringLayout.NORTH, numsPanel);
		sl_this.putConstraint(SpringLayout.WEST, lblTimer, 10, SpringLayout.EAST, numsPanel);
		lblTimer.setPreferredSize(new Dimension(150,70));
		lblTimer.setHorizontalAlignment(SwingConstants.CENTER);
		lblTimer.setForeground(Color.DARK_GRAY);
		lblTimer.setFont(new Font("Segoe UI", Font.BOLD, 40));
		this.add(lblTimer);
		
		JLabel lblBG = new JLabel("");
		lblBG.setIcon(new ImageIcon(PlayPanel.class.getResource("/Presentation/img/redDot.png")));
		sl_this.putConstraint(SpringLayout.SOUTH, lblBG, 0, SpringLayout.SOUTH, this);
		sl_this.putConstraint(SpringLayout.EAST, lblBG, 0, SpringLayout.EAST, this);
		this.add(lblBG);
		
	}
	
	public void getHint() {
		if(!CG.isFinished()) {
			int[] pos = CG.getHint();
			KP.setHint(pos[0],pos[1],pos[2]);
			lblHints.setText(String.valueOf(CG.getHints()));
			if(CG.isFinished()) {
				timer.stop();
				JOptionPane.showMessageDialog(father, "Congratulations :)", "Kakuro finished!", JOptionPane.INFORMATION_MESSAGE);
				father.setMainScreen();
			}
		}
	}
	
	public void exportBoard() {
		CG.exportBoard();
		JOptionPane.showMessageDialog(father, "Your board was successfully exported to desktop.", "Exported successfully", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public void saveGame() {
		CG.storeGame();
		JOptionPane.showMessageDialog(father, "Your game was successfully saved.", "Saved successfully", JOptionPane.INFORMATION_MESSAGE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btn1) {
			KP.setNum(1);
		}
		else if(e.getSource() == btn2) {
			KP.setNum(2);
		}
		else if(e.getSource() == btn3) {
			KP.setNum(3);
		}
		else if(e.getSource() == btn4) {
			KP.setNum(4);
		}
		else if(e.getSource() == btn5) {
			KP.setNum(5);
		}
		else if(e.getSource() == btn6) {
			KP.setNum(6);
		}
		else if(e.getSource() == btn7) {
			KP.setNum(7);
		}
		else if(e.getSource() == btn8) {
			KP.setNum(8);
		}
		else if(e.getSource() == btn9) {
			KP.setNum(9);
		}
		else if(e.getSource() == btnX) {
			KP.setNum(0);
		}
		if(CG.isFinished()) {
			timer.stop();
			JOptionPane.showMessageDialog(father, "You got a score of: "+CG.getScore()+" points!", "Congratulations :)", JOptionPane.INFORMATION_MESSAGE);
			father.setMainScreen();
		}
	}

}
