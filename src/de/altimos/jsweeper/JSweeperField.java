package de.altimos.jsweeper;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class JSweeperField extends JPanel {
	
	private JSweeperButton[][] buttons;
	private JSweeperGame game;
	
	public JSweeperField(JSweeperGame game) {
		this.game = game;
		
		buttons = new JSweeperButton[game.getWidth()][game.getHeight()];
		
		setLayout(new GridLayout(game.getHeight(), game.getWidth(), 1, 1));
		for(int x = 0; x < game.getWidth(); x++) {
			for(int y = 0; y < game.getHeight(); y++) {
				buttons[x][y] = new JSweeperButton(x, y);
				add(buttons[x][y]);
			}
		}
	}
	
	public void clickField(int x, int y) {
		JSweeperButton b = buttons[x][y];
		if(b.isEnabled()) {
			if(game.isMine(x, y)) {
				showMines();
			}else{
				b.showField();
				
				int mines = game.getMinesNear(x, y);
				if(mines > 0) {
					for(int cx = x-1; cx < x+2; cx++) {
						for(int cy = y-1; cy < y+2; cy++) {
							if(cx >= 0 && cy >= 0 && cx < game.getWidth() && cy < game.getHeight()) {
								if(game.getMinesNear(cx, cy) == 0) {
									clickField(cx, cy);
								}
							}
						}
					}
				}else{
					for(int cx = x-1; cx < x+2; cx++) {
						for(int cy = y-1; cy < y+2; cy++) {
							if(cx >= 0 && cy >= 0 && cx < game.getWidth() && cy < game.getHeight()) {
								if(game.getMinesNear(cx, cy) == 0) {
									clickField(cx, cy);
								}else{
									showField(cx, cy);
								}
							}
						}
					}
				}
			}
		}
	}
	
	public void showField(int x, int y) {
		JSweeperButton b = buttons[x][y];
		if(b.isEnabled()) {
			if(game.isMine(x, y)) {
				showMines();
			}else{
				b.showField();
			}
		}
	}
	
	public void showMines() {
		for(int x = 0; x < game.getWidth(); x++) {
			for(int y = 0; y < game.getHeight(); y++) {
				if(game.isMine(x, y)) {
					buttons[x][y].showField();
				}
			}
		}
	}
	
	class JSweeperButton extends JButton implements MouseListener {
		
		private int x, y;
		private boolean marked = false;
		
		public JSweeperButton(int x, int y) {
			this.x = x;
			this.y = y;
			setMargin(new Insets(1,1,1,1));
			addMouseListener(this);
			setPreferredSize(new Dimension(20, 20));
		}
		
		public int getSweeperX() {
			return x;
		}
		
		public int getSweeperY() {
			return y;
		}
		
		public boolean isMine() {
			return game.isMine(x, y);
		}
		
		public void showField() {
			setEnabled(false);
			if(isMine()) {
				setIcon(null);
				setText("X");
			}else{
				int mines = game.getMinesNear(x, y);
				if(mines > 0) {
					setText(String.valueOf(mines));
				}
			}
		}
		
		public boolean isMarked() {
			return marked;
		}
		
		public void setMarked(boolean m) {
			marked = m;
//			setEnabled(!m);
			if(m) {
				setText("F");
			} else {
				setText("");
			}
		}
		
		@Override
		public void mouseClicked(MouseEvent e) {
			if(!isMarked()) {
				if(e.getButton() == MouseEvent.BUTTON1) {
					clickField(x, y);
				}
			}
		}
		
		@Override
		public void mouseEntered(MouseEvent e) { }
		
		@Override
		public void mouseExited(MouseEvent e) { }
		
		@Override
		public void mousePressed(MouseEvent e) {
			if(e.isPopupTrigger()) {
				setMarked(!marked);
			}
		}
		
		@Override
		public void mouseReleased(MouseEvent e) {
			if(e.isPopupTrigger()) {
				setMarked(!marked);
			}
		}
		
	}
}
