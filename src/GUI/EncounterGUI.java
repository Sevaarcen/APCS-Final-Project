package GUI;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import base.Encounter;
import base.Monster;
import base.Player;

public class EncounterGUI extends JPanel {
	private static final long serialVersionUID = 9127005173908807348L;

	Encounter encounter;
	Monster enemy;
	JLabel pInfo, eInfo;
	Player player;

	public EncounterGUI(Encounter e) {
		setPreferredSize(new Dimension(800, 800));
		setLayout(new GridLayout(2, 1));
		encounter = e;
		player = e.getPlayer();
		enemy = e.getEnemy();

		eInfo = new JLabel(enemy.getName() + " (LVL " + enemy.getLVL() + ") " + enemy.getHP() + "/" + enemy.getMaxHP(),
				SwingConstants.CENTER);
		pInfo = new JLabel("Player (LVL " + player.getLVL() + ") " + player.getHP() + "/" + player.getMaxHP(),
				SwingConstants.CENTER);
		add(eInfo);
		add(pInfo);
		setVisible(true);
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		eInfo.setText(enemy.getName() + " (LVL " + enemy.getLVL() + ") " + enemy.getHP() + "/" + enemy.getMaxHP());
		pInfo.setText("Player (LVL " + player.getLVL() + ") " + player.getHP() + "/" + player.getMaxHP());
		eInfo.setFont(new Font(getFont().getName(), Font.PLAIN, 24));
		pInfo.setFont(new Font(getFont().getName(), Font.PLAIN, 24));
		repaint();
	}
}
