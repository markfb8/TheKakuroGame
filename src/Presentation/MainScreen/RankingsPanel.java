/** @file RankingsPanel.java */
package Presentation.MainScreen;

/** @class RankingsPanel
 */

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;

import Domain.Controllers.CtrlGame;
import Domain.Controllers.CtrlRanking;
import Presentation.CtrlPresentation;
import Presentation.MainScreen.Menus.MainMenuPanel;
import Presentation.MainScreen.PlayPanel.KakuroPanel;

@SuppressWarnings("serial")
public class RankingsPanel extends JPanel implements ActionListener {

	@SuppressWarnings("unused")
	private CtrlPresentation CP;
	private CtrlGame CG;
	private CtrlRanking CRK;
	private String username;
	private ArrayList<ArrayList<String>> rank;
	private SpringLayout sl_this;
	private JPanel optPanel;
	private SpringLayout sl_optPanel;
	
	private JButton btnGeneral;
	private JButton btnScores;
	
	private JLabel idBoard;
	private KakuroPanel KP;
	private ArrayList<String> ids;
	private int selected;
	private JButton btnPrev;
	private JButton btnNext;
	private JButton btnRank;
	private JButton btnBack;
	
	public RankingsPanel(CtrlPresentation CP, String username) {
		this.CP = CP;
		this.username = username;
		CG = CP.createGame(username);
		CRK = CP.createRanking();
		ids = CG.getCompletedList();
		initialize();
	}
	
	private void initialize() {
		this.setBackground(Color.WHITE);
		sl_this = new SpringLayout();
		this.setLayout(sl_this);
		
		optPanel = new JPanel();
		optPanel.setBackground(Color.DARK_GRAY);
		optPanel.setPreferredSize(new Dimension(400,270));
		sl_this.putConstraint(SpringLayout.NORTH, optPanel, 215, SpringLayout.NORTH, this);
		sl_this.putConstraint(SpringLayout.WEST, optPanel, 150, SpringLayout.WEST, this);
		this.add(optPanel);
		sl_optPanel = new SpringLayout();
		optPanel.setLayout(sl_optPanel);
		
		JLabel lblRanking = new JLabel("RANKING");
		sl_this.putConstraint(SpringLayout.SOUTH, lblRanking, 17, SpringLayout.NORTH, optPanel);
		sl_this.putConstraint(SpringLayout.WEST, lblRanking, 145, SpringLayout.WEST, this);
		lblRanking.setForeground(Color.DARK_GRAY);
		lblRanking.setFont(new Font("Segoe UI", Font.BOLD, 60));
		this.add(lblRanking);
		
		btnGeneral = new JButton("GLOBAL RANKING");
		sl_optPanel.putConstraint(SpringLayout.NORTH, btnGeneral, 50, SpringLayout.NORTH, optPanel);
		sl_optPanel.putConstraint(SpringLayout.WEST, btnGeneral, 50, SpringLayout.WEST, optPanel);
		btnGeneral.setHorizontalTextPosition(SwingConstants.CENTER);
		btnGeneral.setFocusPainted(false);
		btnGeneral.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnGeneral.setBackground(Color.WHITE);
				btnGeneral.setForeground(Color.BLACK);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnGeneral.setBackground(Color.DARK_GRAY);
				btnGeneral.setForeground(Color.WHITE);
			}
		});
		btnGeneral.setBorder(new MatteBorder(1,1,1,1,Color.WHITE));
		btnGeneral.setBackground(Color.DARK_GRAY);
		btnGeneral.setForeground(Color.WHITE);
		btnGeneral.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		btnGeneral.setPreferredSize(new Dimension(300, 60));
		btnGeneral.addActionListener(this);
		optPanel.add(btnGeneral);

		btnScores = new JButton("MY SCORES");
		sl_optPanel.putConstraint(SpringLayout.NORTH, btnScores, 160, SpringLayout.NORTH, optPanel);
		sl_optPanel.putConstraint(SpringLayout.WEST, btnScores, 50, SpringLayout.WEST, optPanel);
		btnScores.setHorizontalTextPosition(SwingConstants.CENTER);
		btnScores.setFocusPainted(false);
		btnScores.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnScores.setBackground(Color.WHITE);
				btnScores.setForeground(Color.BLACK);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnScores.setBackground(Color.DARK_GRAY);
				btnScores.setForeground(Color.WHITE);
			}
		});
		btnScores.setBorder(new MatteBorder(1,1,1,1,Color.WHITE));
		btnScores.setBackground(Color.DARK_GRAY);
		btnScores.setForeground(Color.WHITE);
		btnScores.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		btnScores.setPreferredSize(new Dimension(300, 60));
		btnScores.addActionListener(this);
		optPanel.add(btnScores);
		
		JLabel lblGenBg = new JLabel("");
		lblGenBg.setIcon(new ImageIcon(MainScreen.class.getResource("/Presentation/img/winnerBG.png")));
		sl_this.putConstraint(SpringLayout.SOUTH, lblGenBg, 0, SpringLayout.SOUTH, this);
		sl_this.putConstraint(SpringLayout.EAST, lblGenBg, 0, SpringLayout.EAST, this);
		this.add(lblGenBg);
		
		revalidate();
		repaint();
		requestFocus();
	}
	
	private void updatePreview() {
		optPanel.remove(KP);
		idBoard.setText("Kakuro#"+ids.get(selected));
		CG.selectCompleted(ids.get(selected));
		KP = new KakuroPanel(CG,300,true);
		sl_optPanel.putConstraint(SpringLayout.NORTH, KP, 90, SpringLayout.NORTH, optPanel);
		sl_optPanel.putConstraint(SpringLayout.WEST, KP, 100, SpringLayout.WEST, optPanel);
		optPanel.add(KP);
		revalidate();
		repaint();
	}
	
	private void setSelectionMode() {
		
		optPanel.removeAll();
		optPanel.setPreferredSize(new Dimension(500,500));
		sl_this.putConstraint(SpringLayout.NORTH, optPanel, 100, SpringLayout.NORTH, this);
		sl_this.putConstraint(SpringLayout.WEST, optPanel, 100, SpringLayout.WEST, this);
		selected = 0;
		
		if(ids == null || ids.size() == 0) {
			idBoard = new JLabel("There are no rankings to be shown :(");
			idBoard.setPreferredSize(new Dimension(500,30));
			idBoard.setForeground(Color.WHITE);
			idBoard.setHorizontalAlignment(SwingConstants.CENTER);
			idBoard.setFont(new Font("Segoe UI", Font.BOLD, 20));
			sl_optPanel.putConstraint(SpringLayout.NORTH, idBoard, 210, SpringLayout.NORTH, optPanel);
			sl_optPanel.putConstraint(SpringLayout.WEST, idBoard, 0, SpringLayout.WEST, optPanel);
			optPanel.add(idBoard);
			
			JLabel idBoard2 = new JLabel("Try completing a kakuro!");
			idBoard2.setPreferredSize(new Dimension(500,30));
			idBoard2.setForeground(Color.WHITE);
			idBoard2.setHorizontalAlignment(SwingConstants.CENTER);
			idBoard2.setFont(new Font("Segoe UI", Font.BOLD, 16));
			sl_optPanel.putConstraint(SpringLayout.NORTH, idBoard2, 240, SpringLayout.NORTH, optPanel);
			sl_optPanel.putConstraint(SpringLayout.WEST, idBoard2, 0, SpringLayout.WEST, optPanel);
			optPanel.add(idBoard2);
		} else {
			CG.selectCompleted(ids.get(selected));
			
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
			
			btnRank = new JButton("SEE SCORE");
			btnRank.setPreferredSize(new Dimension(100,40));
			btnRank.setFocusPainted(false);
			sl_optPanel.putConstraint(SpringLayout.SOUTH, btnRank, -40, SpringLayout.SOUTH, optPanel);
			sl_optPanel.putConstraint(SpringLayout.EAST, btnRank, -200, SpringLayout.EAST, optPanel);
			btnRank.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent e) {
					btnRank.setBackground(Color.WHITE);
					btnRank.setForeground(Color.BLACK);
				}
				@Override
				public void mouseExited(MouseEvent e) {
					btnRank.setBackground(Color.DARK_GRAY);
					btnRank.setForeground(Color.WHITE);
				}
			});
			btnRank.setBorder(new MatteBorder(1,1,1,1,Color.WHITE));
			btnRank.setBackground(Color.DARK_GRAY);
			btnRank.setForeground(Color.WHITE);
			btnRank.setFont(new Font("Segoe UI", Font.PLAIN, 14));
			btnRank.addActionListener(this); 
			optPanel.add(btnRank);
		}
		revalidate();
		repaint();
		requestFocus();
	}
	
	private void showRanking() {
		optPanel.removeAll();
		optPanel.setPreferredSize(new Dimension(500,500));
		sl_this.putConstraint(SpringLayout.NORTH, optPanel, 100, SpringLayout.NORTH, this);
		sl_this.putConstraint(SpringLayout.WEST, optPanel, 100, SpringLayout.WEST, this);
		
		if(rank != null) {
			if(rank.size() >= 1) {
				JLabel lbl1 = new JLabel(rank.get(0).get(1)+" ("+rank.get(0).get(0)+"pts)");
				lbl1.setPreferredSize(new Dimension(500,50));
				sl_optPanel.putConstraint(SpringLayout.NORTH, lbl1, 20, SpringLayout.NORTH, optPanel);
				sl_optPanel.putConstraint(SpringLayout.WEST, lbl1, 0, SpringLayout.WEST, optPanel);
				lbl1.setForeground(Color.YELLOW);
				lbl1.setFont(new Font("Segoe UI", Font.BOLD, 34));
				lbl1.setIcon(new ImageIcon(RankingsPanel.class.getResource("/Presentation/img/goldMedalIcon.png")));
				lbl1.setHorizontalTextPosition(SwingConstants.RIGHT);
				lbl1.setHorizontalAlignment(SwingConstants.CENTER);
				optPanel.add(lbl1);
			}
			
			if(rank.size() >= 2) {
				JLabel lbl2 = new JLabel(rank.get(1).get(1)+" ("+rank.get(1).get(0)+"pts)");
				lbl2.setPreferredSize(new Dimension(250,50));
				sl_optPanel.putConstraint(SpringLayout.NORTH, lbl2, 90, SpringLayout.NORTH, optPanel);
				sl_optPanel.putConstraint(SpringLayout.WEST, lbl2, 0, SpringLayout.WEST, optPanel);
				lbl2.setForeground(Color.LIGHT_GRAY);
				lbl2.setFont(new Font("Segoe UI", Font.BOLD, 24));
				lbl2.setIcon(new ImageIcon(RankingsPanel.class.getResource("/Presentation/img/silverMedalIcon.png")));
				lbl2.setHorizontalTextPosition(SwingConstants.RIGHT);
				lbl2.setHorizontalAlignment(SwingConstants.CENTER);
				optPanel.add(lbl2);
			}
			
			if(rank.size() >= 3) {
				JLabel lbl3 = new JLabel(rank.get(2).get(1)+" ("+rank.get(2).get(0)+"pts)");
				lbl3.setPreferredSize(new Dimension(250,50));
				sl_optPanel.putConstraint(SpringLayout.NORTH, lbl3, 90, SpringLayout.NORTH, optPanel);
				sl_optPanel.putConstraint(SpringLayout.WEST, lbl3, 250, SpringLayout.WEST, optPanel);
				lbl3.setForeground(new Color(205,127,50));
				lbl3.setFont(new Font("Segoe UI", Font.BOLD, 24));
				lbl3.setIcon(new ImageIcon(RankingsPanel.class.getResource("/Presentation/img/bronzeMedalIcon.png")));
				lbl3.setHorizontalTextPosition(SwingConstants.RIGHT);
				lbl3.setHorizontalAlignment(SwingConstants.CENTER);
				optPanel.add(lbl3);
			}
			
			if(rank.size() >= 4) {
				JLabel lbl4 = new JLabel("4. "+rank.get(3).get(1)+" ("+rank.get(3).get(0)+"pts)");
				lbl4.setPreferredSize(new Dimension(250,50));
				sl_optPanel.putConstraint(SpringLayout.NORTH, lbl4, 150, SpringLayout.NORTH, optPanel);
				sl_optPanel.putConstraint(SpringLayout.WEST, lbl4, 0, SpringLayout.WEST, optPanel);
				lbl4.setForeground(Color.WHITE);
				lbl4.setFont(new Font("Segoe UI", Font.BOLD, 16));
				lbl4.setHorizontalAlignment(SwingConstants.CENTER);
				optPanel.add(lbl4);
			}
			
			if(rank.size() >= 5) {
				JLabel lbl5 = new JLabel("5. "+rank.get(4).get(1)+" ("+rank.get(4).get(0)+"pts)");
				lbl5.setPreferredSize(new Dimension(250,50));
				sl_optPanel.putConstraint(SpringLayout.NORTH, lbl5, 190, SpringLayout.NORTH, optPanel);
				sl_optPanel.putConstraint(SpringLayout.WEST, lbl5, 0, SpringLayout.WEST, optPanel);
				lbl5.setForeground(Color.WHITE);
				lbl5.setFont(new Font("Segoe UI", Font.BOLD, 16));
				lbl5.setHorizontalAlignment(SwingConstants.CENTER);
				optPanel.add(lbl5);
			}
				
			if(rank.size() >= 6) {
				JLabel lbl6 = new JLabel("6. "+rank.get(5).get(1)+" ("+rank.get(5).get(0)+"pts)");
				lbl6.setPreferredSize(new Dimension(250,50));
				sl_optPanel.putConstraint(SpringLayout.NORTH, lbl6, 230, SpringLayout.NORTH, optPanel);
				sl_optPanel.putConstraint(SpringLayout.WEST, lbl6, 0, SpringLayout.WEST, optPanel);
				lbl6.setForeground(Color.WHITE);
				lbl6.setFont(new Font("Segoe UI", Font.BOLD, 16));
				lbl6.setHorizontalAlignment(SwingConstants.CENTER);
				optPanel.add(lbl6);
			}
			
			if(rank.size() >= 7) {
				JLabel lbl7 = new JLabel("7. "+rank.get(6).get(1)+" ("+rank.get(6).get(0)+"pts)");
				lbl7.setPreferredSize(new Dimension(250,50));
				sl_optPanel.putConstraint(SpringLayout.NORTH, lbl7, 150, SpringLayout.NORTH, optPanel);
				sl_optPanel.putConstraint(SpringLayout.WEST, lbl7, 250, SpringLayout.WEST, optPanel);
				lbl7.setForeground(Color.WHITE);
				lbl7.setFont(new Font("Segoe UI", Font.BOLD, 16));
				lbl7.setHorizontalAlignment(SwingConstants.CENTER);
				optPanel.add(lbl7);
			}
			
			if(rank.size() >= 8) {
				JLabel lbl8 = new JLabel("8. "+rank.get(7).get(1)+" ("+rank.get(7).get(0)+"pts)");
				lbl8.setPreferredSize(new Dimension(250,50));
				sl_optPanel.putConstraint(SpringLayout.NORTH, lbl8, 190, SpringLayout.NORTH, optPanel);
				sl_optPanel.putConstraint(SpringLayout.WEST, lbl8, 250, SpringLayout.WEST, optPanel);
				lbl8.setForeground(Color.WHITE);
				lbl8.setFont(new Font("Segoe UI", Font.BOLD, 16));
				lbl8.setHorizontalAlignment(SwingConstants.CENTER);
				optPanel.add(lbl8);
			}
			
			if(rank.size() >= 9) {
				JLabel lbl9 = new JLabel("9. "+rank.get(8).get(1)+" ("+rank.get(8).get(0)+"pts)");
				lbl9.setPreferredSize(new Dimension(250,50));
				sl_optPanel.putConstraint(SpringLayout.NORTH, lbl9, 235, SpringLayout.NORTH, optPanel);
				sl_optPanel.putConstraint(SpringLayout.WEST, lbl9, 250, SpringLayout.WEST, optPanel);
				lbl9.setForeground(Color.WHITE);
				lbl9.setFont(new Font("Segoe UI", Font.BOLD, 16));
				lbl9.setHorizontalAlignment(SwingConstants.CENTER);
				optPanel.add(lbl9);
				
				JLabel lblDots = new JLabel("...");
				lblDots.setPreferredSize(new Dimension(500,20));
				sl_optPanel.putConstraint(SpringLayout.NORTH, lblDots, 290, SpringLayout.NORTH, optPanel);
				sl_optPanel.putConstraint(SpringLayout.WEST, lblDots, 0, SpringLayout.WEST, optPanel);
				lblDots.setHorizontalAlignment(SwingConstants.CENTER);
				lblDots.setForeground(Color.WHITE);
				lblDots.setFont(new Font("Segoe UI", Font.BOLD, 24));
				optPanel.add(lblDots);
			}
			
			int iaux = -1;
			String sc = "";
			for(int i = 0; i < rank.size(); ++i) {
				if(rank.get(i).get(1).equals(username)) {
					iaux = i;
					sc = rank.get(i).get(0);
					break;
				}
			}
			if(iaux != -1) {
				JLabel lblTop = new JLabel("You are top "+(iaux+1)+" with "+sc+"pts!");
				lblTop.setPreferredSize(new Dimension(500,60));
				sl_optPanel.putConstraint(SpringLayout.NORTH, lblTop, 330, SpringLayout.NORTH, optPanel);
				sl_optPanel.putConstraint(SpringLayout.WEST, lblTop, 0, SpringLayout.WEST, optPanel);
				lblTop.setHorizontalAlignment(SwingConstants.CENTER);
				lblTop.setOpaque(true);
				lblTop.setBackground(Color.WHITE);
				lblTop.setForeground(Color.BLACK);
				lblTop.setFont(new Font("Segoe UI", Font.BOLD, 26));
				optPanel.add(lblTop);
			}
			
		} else {
			idBoard = new JLabel("There are no rankings to be shown :(");
			idBoard.setPreferredSize(new Dimension(500,30));
			idBoard.setForeground(Color.WHITE);
			idBoard.setHorizontalAlignment(SwingConstants.CENTER);
			idBoard.setFont(new Font("Segoe UI", Font.BOLD, 20));
			sl_optPanel.putConstraint(SpringLayout.NORTH, idBoard, 210, SpringLayout.NORTH, optPanel);
			sl_optPanel.putConstraint(SpringLayout.WEST, idBoard, 0, SpringLayout.WEST, optPanel);
			optPanel.add(idBoard);
			
			JLabel idBoard2 = new JLabel("Try completing a kakuro!");
			idBoard2.setPreferredSize(new Dimension(500,30));
			idBoard2.setForeground(Color.WHITE);
			idBoard2.setHorizontalAlignment(SwingConstants.CENTER);
			idBoard2.setFont(new Font("Segoe UI", Font.BOLD, 16));
			sl_optPanel.putConstraint(SpringLayout.NORTH, idBoard2, 240, SpringLayout.NORTH, optPanel);
			sl_optPanel.putConstraint(SpringLayout.WEST, idBoard2, 0, SpringLayout.WEST, optPanel);
			optPanel.add(idBoard2);
		}
		
		btnBack = new JButton("BACK");
		btnBack.setPreferredSize(new Dimension(100,40));
		btnBack.setFocusPainted(false);
		sl_optPanel.putConstraint(SpringLayout.SOUTH, btnBack, -40, SpringLayout.SOUTH, optPanel);
		sl_optPanel.putConstraint(SpringLayout.EAST, btnBack, -200, SpringLayout.EAST, optPanel);
		btnBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnBack.setBackground(Color.WHITE);
				btnBack.setForeground(Color.BLACK);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnBack.setBackground(Color.DARK_GRAY);
				btnBack.setForeground(Color.WHITE);
			}
		});
		btnBack.setBorder(new MatteBorder(1,1,1,1,Color.WHITE));
		btnBack.setBackground(Color.DARK_GRAY);
		btnBack.setForeground(Color.WHITE);
		btnBack.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		btnBack.addActionListener(this); 
		optPanel.add(btnBack);
		
		revalidate();
		repaint();
		requestFocus();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnGeneral) {
			try {
				rank = CRK.getGeneralRanking();
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			showRanking();
		} else if(e.getSource() == btnScores) {
			setSelectionMode();
		} else if(e.getSource() == btnNext) {
			if(selected < ids.size()-1) ++selected;
			else selected = 0;
			updatePreview();
		} else if(e.getSource() == btnPrev) {
			if(selected > 0) --selected; 
			else selected = ids.size()-1;
			updatePreview();
		} else if(e.getSource() == btnRank) {
			try {
				rank = CRK.getBoardRanking(ids.get(selected));
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			showRanking();
		} else {
			this.removeAll();
			initialize();
		}
	}
	
}