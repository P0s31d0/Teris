import java.awt.Color;
import java.awt.Point;
import java.util.Arrays;

public class Field {
	
	// information about the position an color of solid Tetrominos
	public Point[] solids;
	public Point[] border = new Point[0];
	public Color[] colors;
	
	public Field() {
		
		solids = new Point[Main.gameWidth];
		colors = new Color[Main.gameWidth];
		
		for (int i = 0; i < Main.gameWidth; i++) {
			
			solids[i] = new Point(i, Main.gameHeight);
			colors[i] = Color.RED;
		}
		
		findBorder();
	}
	
	public void push() {
			
		for (int i = 0; i < Main.t.pos.length; i++) {
				
			solids = pushP(solids, Main.t.pos[i]);
			colors = pushC(colors, Main.t.c);
		}
		
		removeIfNeeded();
		findBorder();
	}
	
	private void removeIfNeeded() {
		
		int[] ys = new int[0];
		
		for (int i = 0; i < solids.length; i++) {
			
			ys = pushI(ys, solids[i].y);
		}
		
		Arrays.sort(ys);
		
		int[] removedYs = new int[0];
		
		for (int y = ys[0]; y < Main.gameHeight; y++) {
			
			int counter = 0;
			
			for (int x = 0; x < Main.gameWidth; x++) {
				
				for (int i = 0; i < solids.length; i++) {
					
					if (x == solids[i].x && y == solids[i].y) {
						
						counter++;
					}
				}
			}
			
			if (counter == Main.gameWidth) {
				
				removedYs = pushI(removedYs, y);
				
				Point[] tmp = solids;
				
				for (int i = 0; i < tmp.length; i++) {
					
					if  (tmp[i].y == y) {
						
						for (int j = 0; j < solids.length; j++) {
							
							if(solids[j].y == tmp[i].y) {
								
								solids = removeP(solids, j);
								colors = removeC(colors, j);
							}
						}
						
					}
				}
			}
		}
		
		if (removedYs.length > 0) {
			
			Arrays.sort(removedYs);
			
			for (int i = 0; i < removedYs.length; i++) {
				for (int j = 0; j < solids.length; j++) {

					
					if (solids[j].y < removedYs[removedYs.length - 1]) {
						
						System.out.println("+1");
						solids[j].y = solids[j].y + 1;
					}
				}
			}
		}
	}
	
	private void findBorder() {
		
		border = new Point[0];
		
		Point[] tmp = solids;
		
	    int[] allXs = new int[tmp.length];
		 
		for (int i = 0; i < tmp.length; i++) {
			 allXs[i] = solids[i].x;
		}
		 
		Arrays.sort(allXs);
		 
		int[] xs = new int[0];
		 
		for (int i = 0; i < allXs.length; i++) {
			
			if (i == 0) {
				
				xs = pushI(xs, allXs[i]);
			}
			
			if (i > 0 && allXs[i] != allXs[i - 1]) {
				 
				xs = pushI(xs, allXs[i]);
			}
		} 
		 
		for (int n = 0; n < xs.length; n++) {
			
			Point[] pYmin = new Point[0];
			
			for (int i = 0; i < tmp.length; i++) {
				
				if (tmp[i].x == xs[n]) {
					
					pYmin = pushP(pYmin, solids[i]);
				}
			}
			
			for (int i = pYmin.length; i > 0; i--) {
				for (int j = 0; j < i - 1; j++){
					 
					if (pYmin[j].y > pYmin[j + 1].y) {
						 
						Point tmpP = pYmin[j + 1];
						pYmin[j + 1] = pYmin[j];
						pYmin[j] = tmpP;
					}
				}
			}
			
			for (int i = 0; i < pYmin.length; i++) {
				
				if (i != 0) {
					
					int dy = pYmin[i].y - pYmin[i - 1].y;
					
					if (dy > 1) border = pushP(border, pYmin[i]);
				}
				
			}
			
			border = pushP(border, pYmin[0]);
		}
	}
	
	private Point[] removeP(Point[] p, int j) {
		
		// crappy way to push or pull information (same applies for all the other 'remove()' and 'push()' functions)
		// also possible and most likely better way to do this way be the usage of 'ArrayList' or 'LinkedList')
		// i just wanted to get some experience of working with arrays xD
		Point[] tmpP = p;
		
		p = new Point[tmpP.length - 1];
		
		int l = 0;
		
		for (int i = 0; i < tmpP.length; i++) {
			
			if (i != j) {
				
				p[l] = tmpP[i];
				l++;
			}
		}
		
		return p;
	}
	
	private Color[] removeC(Color[] p, int j) {
		
		Color[] tmpP = p;
		
		p = new Color[tmpP.length - 1];
		
		int l = 0;
		
		for (int i = 0; i < tmpP.length; i++) {
			
			if (i != j) {
				
				p[l] = tmpP[i];
				l++;
			}
		}
		
		return p;
	}
	
	private int[] pushI(int[] p, int newP) {
		
		int[] tmpP = p;
		
		p = new int[tmpP.length + 1];
		
		for (int i = 0; i < tmpP.length; i++) {
			p[i] = tmpP[i];
		}
		
		p[tmpP.length] = newP;
		
		return p;
	}
	
	private Point[] pushP(Point[] p, Point newP) {
		
		Point[] tmpP = p;
		
		p = new Point[tmpP.length + 1];
		
		for (int i = 0; i < tmpP.length; i++) {
			p[i] = tmpP[i];
		}
		
		p[tmpP.length] = newP;
		
		return p;
	}
	
	private Color[] pushC(Color[] p, Color newP) {
		
		Color[] tmpP = p;
		
		p = new Color[tmpP.length + 1];
		
		for (int i = 0; i < tmpP.length; i++) {
			p[i] = tmpP[i];
		}
		
		p[tmpP.length] = newP;
		
		return p;
	}
}
