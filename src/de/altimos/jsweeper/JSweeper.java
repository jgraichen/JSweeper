package de.altimos.jsweeper;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.WindowConstants;

public class JSweeper extends JFrame {
	
	static public void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		new JSweeper();
	}
	
	private JSweeperField field;
	private JSweeperGame game;
	
	public JSweeper() {
		super("JavaSweeper");
		game = new JSweeperGame(20, 20, 60);
		field = new JSweeperField(game);
		
		add(field);
		
		setBounds(100, 100, 100, 100);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		pack();
		setVisible(true);
	}
}
