package testing2;

import java.awt.Color;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MyListener implements MouseListener, MouseMotionListener {

	public void mouseClicked(MouseEvent e) {
		MagicLabel clickedLabel = (MagicLabel) e.getSource();
		//System.out.println(clickedLabel.c);
		//JFrameThings.cont.setBackground(clickedLabel.c);
		//JFrameThings.cont.remove(clickedLabel);
		if (clickedLabel.isTrue) {
			JFrameThings.cont.setBackground(Color.GREEN);
		} else {
			JFrameThings.cont.setBackground(Color.RED);
		}
		System.out.println("Score: " +clickedLabel.score);
	}

	public void mousePressed(MouseEvent e) { }

	public void mouseReleased(MouseEvent e) {
		System.out.println("Release");
	}

	public void mouseEntered(MouseEvent e) { }

	public void mouseExited(MouseEvent e) { }

	public void mouseDragged(MouseEvent e) {
		int x = e.getPoint().x;
		int y = e.getPoint().y;
		
		System.out.println("Dragged");
	}

	public void mouseMoved(MouseEvent e) {
		System.out.println("Moved");
		
	}

}
