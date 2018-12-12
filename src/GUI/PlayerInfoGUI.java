package GUI;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import base.Player;

public class PlayerInfoGUI extends JPanel {
	private static final long serialVersionUID = 5598966659849384485L;

	Player player;
	JLabel hp, lvl, xp, gold;

	public PlayerInfoGUI(Player p) {
		player = p;
		setLayout(new GridLayout(3, 1));

		JPanel stats = new JPanel();
		stats.setLayout(new GridLayout(1, 4));
		hp = new JLabel("HP: " + p.getHP() + "/" + p.getMaxHP(), SwingConstants.CENTER);
		hp.setFont(new Font(getFont().getName(), Font.BOLD, 16));
		lvl = new JLabel("LVL: " + p.getLVL(), SwingConstants.CENTER);
		xp = new JLabel("XP: " + p.getXP() + " / 25", SwingConstants.CENTER);
		gold = new JLabel("Gold Pieces: " + p.getGold(), SwingConstants.CENTER);
		stats.add(hp);
		stats.add(lvl);
		stats.add(xp);
		stats.add(gold);

		add(stats);
		add(new InventoryGUI(p.getInventory()));
		add(new JPanel());
		setVisible(true);
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		hp.setText("HP: " + player.getHP() + "/" + player.getMaxHP());
		hp.setFont(new Font(getFont().getName(), Font.BOLD, 16));
		lvl.setText("LVL: " + player.getLVL());
		xp.setText("XP: " + player.getXP() + " / 25");
		gold.setText("Gold Pieces: " + player.getGold());
	}
}
