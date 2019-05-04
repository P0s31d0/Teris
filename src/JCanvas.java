import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

public class JCanvas extends Canvas{
	
	// my friends way to create sort of an setup xD
	// thanks to Linus Thriemer (check him out at instagram: @lns.thrmr)
	private static final long serialVersionUID = -6845300493185059443L;
	private BufferStrategy bs;
	
	public JCanvas() {
		super();
	}
	
	public JFrame createFrame(String title, int width, int height) {
		
		JFrame frame = new JFrame(title);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(width + 17, height + 40);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		return frame;
	}
	
	public Graphics draw() {
		
		if (bs == null) {
			this.createBufferStrategy(2);
			bs = this.getBufferStrategy();
		}
		
		return bs.getDrawGraphics();
	}
	
	public void stop(Graphics g) {
		
		g.dispose();
		bs.show();
		Toolkit.getDefaultToolkit().sync();
	}
}
