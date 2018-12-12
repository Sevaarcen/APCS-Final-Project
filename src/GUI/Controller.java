package GUI;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import base.Game;

public class Controller implements KeyListener {
	Map map;

	public Controller() {
		map = Game.map;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_W)
			if (map.isShowing())
				map.changePlayerPos(0, -1);
		if (e.getKeyCode() == KeyEvent.VK_A)
			if (map.isShowing())
				map.changePlayerPos(-1, 0);
		if (e.getKeyCode() == KeyEvent.VK_S)
			if (map.isShowing())
				map.changePlayerPos(0, 1);
		if (e.getKeyCode() == KeyEvent.VK_D)
			if (map.isShowing())
				map.changePlayerPos(1, 0);
		map.repaint();
	}

	@Override
	public void keyReleased(KeyEvent arg0) {

	}

	@Override
	public void keyTyped(KeyEvent arg0) {

	}

}