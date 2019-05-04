import java.awt.Color;
import java.awt.Point;

public class Tetromino {
	
	// all basic Tetrominos
	private final int[][] oT = {{1, 1},
						        {1, 1}},
			
						  sT = {{0, 0, 0},
								{0, 1, 1},
								{1, 1, 0}},
					
						  zT = {{0, 0, 0},
						        {1, 1, 0},
						        {0, 1, 1}},
					
						  lT = {{1, 0, 0},
								{1, 0, 0},
								{1, 1, 0}},
					
						  jT = {{0, 0, 1},
								{0, 0, 1},
								{0, 1, 1}},
					
						  tT = {{0, 0, 0},
								{1, 1, 1},
								{0, 1, 0}},
					
						  iT = {{1, 0, 0, 0},
								{1, 0, 0, 0},
								{1, 0, 0, 0},
								{1, 0, 0, 0}};
	
	private final int[][][] tets = {oT, sT, zT, lT, jT, tT, iT};
	
	private final Color[] color = {Color.YELLOW,
							  	   Color.RED,
							  	   Color.GREEN,
							  	   Color.ORANGE,
							  	   Color.PINK,
							  	   Color.MAGENTA,
							  	   Color.BLUE};
	
	public int index;
	public int[][] tet;
	public Point[] pos;
	private Point topLeftPos;
	public Color c;
	
	public Tetromino() {

		 pickNew();
	}
	
	public void turnClock() {
		
		// crappy way of turning the Tetrominos (interesting nevertheless)
		// the problem is that the rotation center point is not in the middle of the Tetromino
		int[][] tmpTet = tet;
		
		tet = new int[tmpTet.length][tmpTet.length];
		
		for (int y = 0; y < tmpTet.length; y++) {
			for (int x = 0; x < tmpTet.length; x++) {
				
				tet[y][x] = tmpTet[(tmpTet.length - 1) - x][y];
			}
		}
		
		pos = new Point[4];
		
		int i = 0;

		for (int y = 0; y < tet.length; y++) {
			for (int x = 0; x < tet.length; x++) {
				 
				if (tet[y][x] == 1) {
					
					pos[i] = new Point(x + topLeftPos.x, y + topLeftPos.y);
					i++;
				}
			}
		}
	}
	
	public void turnCounter() {
		
		int[][] tmpTet = tet;
		
		tet = new int[tmpTet.length][tmpTet.length];
		
		for (int y = 0; y < tmpTet.length; y++) {
			for (int x = 0; x < tmpTet.length; x++) {
				
				tet[x][y] = tmpTet[y][(tmpTet.length - 1) - x];
			}
		}
		
		pos = new Point[4];
		
		int i = 0;

		for (int y = 0; y < tet.length; y++) {
			for (int x = 0; x < tet.length; x++) {
				 
				if (tet[y][x] == 1) {
					
					pos[i] = new Point(x + topLeftPos.x, y + topLeftPos.y);
					i++;
				}
			}
		}
	}
	
	public void pickNew() {
		
		pos = new Point[4];
		
		index = (int)(Math.random()* (tets.length));
		
		tet = tets[index];
		c = color[index];
		 
		int i = 0;
		int rand = (int)(Math.random() * Main.gameWidth/2 + Main.gameWidth/4);
		 
		topLeftPos = new Point(rand, -4);
		 
		for (int y = 0; y < tet.length; y++) {
			for (int x = 0; x < tet.length; x++) {
				 
				if (tet[y][x] == 1) {
					pos[i] = new Point(x + topLeftPos.x, y + topLeftPos.y);
					i++;
				}
			}
		}
	}
	
	public void update() {
		
		for (int i = 0; i < 4; i++) {
			
			pos[i].y = pos[i].y + 1;
		}
		
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < Main.f.border.length; j++) {
				
				if (pos[i].x == Main.f.border[j].x && pos[i].y + 1 == Main.f.border[j].y) {
					
					Main.f.push();
					pickNew();
					return;
				}
			}
		}
		
		topLeftPos.y = topLeftPos.y + 1;
	}
	
	public void right() {
		
		// simple way to move the Terominos right
		// could be improved by far (same applies to 'left()')
		for (int i = 0; i < 4; i++) {
			
			if (pos[i].x == Main.gameWidth - 1) return;
			
			for (int j = 0; j < Main.f.solids.length; j++) {
				
				if (pos[i].x + 1 == Main.f.solids[j].x && pos[i].y == Main.f.solids[j].y) return;
			}
		}
		
		for (int i = 0; i < 4; i++) {
			
			pos[i].x = pos[i].x + 1;
		}
		
		topLeftPos.x = topLeftPos.x + 1;
	}
	
	public void left() {
		
		for (int i = 0; i < 4; i++) {
			
			if (pos[i].x == 0) return;
			
			for (int j = 0; j < Main.f.solids.length; j++) {
				
				if (pos[i].x - 1 == Main.f.solids[j].x && pos[i].y == Main.f.solids[j].y) return;
			}
		}
		
		for (int i = 0; i < 4; i++) {
			
			pos[i].x = pos[i].x - 1;
		}
		topLeftPos.x = topLeftPos.x - 1;
	}
}
