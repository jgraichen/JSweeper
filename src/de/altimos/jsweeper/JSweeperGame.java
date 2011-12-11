package de.altimos.jsweeper;



public class JSweeperGame {
	
	private int width;
	private int height;
	private int mines;
	private boolean[][] data;
	
	public JSweeperGame(int width, int height, int mines) {
		this.width = width;
		this.height = height;
		this.mines = mines;
		
		regenerate();
	}
	
	public void regenerate() {
		data = new boolean[width][height];
		
		for(int x = 0; x < width; x++) {
			for(int y = 0; y < height; y++) {
				data[x][y] = false;
			}
		}
		
		int placed = 0;
		while(placed < mines) {
			int x = (int)(Math.random()*width);
			int y = (int)(Math.random()*height);
			if(!data[x][y]) {
				data[x][y] = true;
				placed++;
			}
		}
	}
	
	public int getMineCount() {
		return mines;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public boolean isMine(int x, int y) {
		return data[x][y];
	}
	
	public int getMinesNear(int x, int y) {
		int mines = 0;
		
		for(int cx = x-1; cx < x+2; cx++) {
			for(int cy = y-1; cy < y+2; cy++) {
				if(cx >= 0 && cy >= 0 && cx < width && cy < height) {
					if(isMine(cx, cy)) {
						mines++;
					}
				}
			}
		}
		return mines;
	}
}
