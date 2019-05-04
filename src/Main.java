import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;

public class Main {
	
	// amount of rows and columns the playing field has
	public static final int gameWidth = 10, gameHeight = 30;
	// just for the look
	static int scaling = 25;
	public static int width = gameWidth * scaling, height = gameHeight * scaling;
	// the blocks that are moved are called Tetrominos
	// the field stores the solid Tetrominos and does things related to this tast
	public static Tetromino t = new Tetromino();
	public static Field f = new Field();
	
	public static void main(String[] args) {
		
		JCanvas jc = new JCanvas();
		JFrame jf = jc.createFrame("Tetris", width, height);
		
		jf.add(jc);
		jf.addKeyListener(new KeyHandler());
		
		
		while(true) {
			
			Graphics g = jc.draw();
			
			g.setColor(Color.GRAY);
			g.fillRect(0, 0, width, height);
			
			g.setColor(t.c);
			
			for (int i = 0; i < 4; i++) {
				
				g.fillRect(t.pos[i].x * scaling, t.pos[i].y * scaling, scaling, scaling);
			}
			
			for (int i = 0; i < f.solids.length; i++) {
				
				g.setColor(f.colors[i]);
				g.fillRect(f.solids[i].x * scaling, f.solids[i].y * scaling, scaling, scaling);
			}
			
			// to see the border
			// the border consists of the important blocks that have to be checked wether a Tetromino collides with the field
//			for (int i = 0; i < f.border.length; i++) {
//				
//				g.setColor(Color.white);
//				g.fillRect(f.border[i].x * scaling, f.border[i].y * scaling, scaling, scaling);
//			}
			
			t.update();
			
			g.setColor(Color.BLACK);
			
			for (int x = 0; x <= gameWidth; x++) {
				g.drawLine(x * scaling, 0, x * scaling, height);
			}
			
			for (int y = 0; y <= gameHeight; y++) {
				g.drawLine(0, y * scaling, width, y * scaling);
			}
			
			jc.stop(g);
			
			//crappy and unthoughtfull way to implement intervall for the Tetrominoes to move
			try {
				Thread.sleep(150);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
}
