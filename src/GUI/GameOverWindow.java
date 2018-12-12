package GUI;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;

import base.Game;

public class GameOverWindow extends JFrame {
	private static final long serialVersionUID = 1291086058511656L;
	Image pic;

	public GameOverWindow(GUIManager GUI) {
		super(GUI.getLastOutput());
		setResizable(false);
		GUI.dispatchEvent(new WindowEvent(GUI, WindowEvent.WINDOW_CLOSING));
		try {
			pic = Game.loader.loadImage("gameOver.jpg");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		GameOverWindow g = this;
		setBounds(GUI.getX(), GUI.getY(), pic.getWidth(null), pic.getHeight(null));
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		JButton restart = new JButton("Try and beat your level of: " + Game.player.getLVL());
		restart.addActionListener((ActionEvent e) -> {
                    Game.newGame();
                    dispatchEvent(new WindowEvent(g, WindowEvent.WINDOW_CLOSING));
                });
		add(restart, BorderLayout.SOUTH);
		setVisible(true);
		repaint();
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(pic, 0, 0, null);
	}
}
