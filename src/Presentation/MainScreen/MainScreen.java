/** @file MainScreen.java */
package Presentation.MainScreen;

/** @class MainScreen
 */

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Domain.Controllers.CtrlGame;

import java.awt.Toolkit;
import Presentation.CtrlPresentation;
import Presentation.MainScreen.Menus.MainMenuPanel;
import Presentation.MainScreen.Menus.PlayMenuPanel;
import Presentation.MainScreen.PlayPanel.PlayPanel;

@SuppressWarnings("serial")
public class MainScreen extends JFrame {

	private CtrlPresentation CP;
	private String name;
	private JPanel contentPane;
	private MainMenuPanel mainMenuPanel;
	private PlayMenuPanel playMenuPanel;
	private WelcomePanel welcPanel;
	private GeneratePanel genPanel;
	private SelectPanel selPanel;
	private LoadGamePanel loadGamePanel;
	private ProposePanel propPanel;
	private RankingsPanel rankPanel;
	private SettingsPanel setPanel;
	private PlayPanel playPanel;

	public MainScreen(CtrlPresentation CP, String name) {
		this.CP = CP;
		this.name = name;
		mainMenuPanel = new MainMenuPanel(this); 
		selPanel = new SelectPanel(CP,this,name);
		loadGamePanel = new LoadGamePanel(CP,this,name);
		welcPanel = new WelcomePanel(name);
		genPanel = new GeneratePanel(CP,this,name);
		propPanel = new ProposePanel(CP,this,name);
		setPanel = new SettingsPanel(CP,name);
		rankPanel = new RankingsPanel(CP,name);
		initialize();
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
		getContentPane().requestFocusInWindow();
	}
	
	private void initialize() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(MainScreen.class.getResource("/Presentation/img/frameLogo.png")));
		setTitle("The Kakuro Game");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 732);
		setPreferredSize(new Dimension(1000,700));
		setMinimumSize(new Dimension(1000,700));
		contentPane = new JPanel();
		contentPane.setBorder(null);
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		contentPane.add(welcPanel, BorderLayout.CENTER);
		contentPane.add(mainMenuPanel, BorderLayout.WEST);
	}
	
	public void setMainScreen() {
		playMenuPanel.setVisible(false);
		playPanel.setVisible(false);
		contentPane.add(welcPanel, BorderLayout.CENTER);
		contentPane.add(mainMenuPanel, BorderLayout.WEST);
		mainMenuPanel.setVisible(true);
		welcPanel.setVisible(true);
		getContentPane().requestFocusInWindow();
	}
	
	private void changeScreen() {
		welcPanel.setVisible(false);
		genPanel.setVisible(false);
		selPanel.setVisible(false);
		loadGamePanel.setVisible(false);
		propPanel.setVisible(false);
		rankPanel.setVisible(false);
		setPanel.setVisible(false);
	}
	
	public void setGeneratePanel() {
		changeScreen();
		genPanel = new GeneratePanel(CP,this,name);
		contentPane.add(genPanel,BorderLayout.CENTER);
		genPanel.setVisible(true);
	}
	
	public void setSelectPanel() {
		changeScreen();
		selPanel = new SelectPanel(CP,this,name);
		contentPane.add(selPanel,BorderLayout.CENTER);
		selPanel.setVisible(true);
	}
	
	public void setLoadGamePanel() {
		changeScreen();
		loadGamePanel = new LoadGamePanel(CP,this,name);
		contentPane.add(loadGamePanel,BorderLayout.CENTER);
		loadGamePanel.setVisible(true);
	}
	
	public void setProposePanel() {
		changeScreen();
		propPanel = new ProposePanel(CP,this,name);
		contentPane.add(propPanel,BorderLayout.CENTER);
		propPanel.setVisible(true);
	}
	
	public void setRankPanel() {
		changeScreen();
		rankPanel = new RankingsPanel(CP,name);
		contentPane.add(rankPanel,BorderLayout.CENTER);
		rankPanel.setVisible(true);
	}
	
	public void setSettingsPanel() {
		changeScreen();
		setPanel = new SettingsPanel(CP,name);
		contentPane.add(setPanel,BorderLayout.CENTER);
		setPanel.setVisible(true);
	}
		
	public void setPlayPanel(CtrlGame CG) {
		changeScreen();
		mainMenuPanel.setVisible(false);
		playPanel = new PlayPanel(CG,this);
		contentPane.add(playPanel,BorderLayout.CENTER);
		playPanel.setVisible(true);
		playMenuPanel = new PlayMenuPanel(playPanel,this);
		contentPane.add(playMenuPanel,BorderLayout.WEST);
		playMenuPanel.setVisible(true);
		getContentPane().requestFocusInWindow();
	}
}
