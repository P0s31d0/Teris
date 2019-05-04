import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{

	@Override
	public void keyPressed(KeyEvent e) {
		
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			
			Main.t.left();
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			
			Main.t.right();
		}
		if (e.getKeyCode() == KeyEvent.VK_D) {
			
			Main.t.turnClock();
		}
		if (e.getKeyCode() == KeyEvent.VK_A) {
			
			Main.t.turnCounter();
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
