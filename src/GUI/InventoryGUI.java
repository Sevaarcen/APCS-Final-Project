package GUI;

import java.awt.Graphics;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

import GUI.Tile.Terrain;
import base.Game;
import base.Inventory;

public class InventoryGUI extends JPanel {
	private static final long serialVersionUID = 886821317978214039L;

	Inventory playerInv;
	JButton[] buttons;
	boolean inShop;

	// EXTRA make looting (functionality already built into ItemButton if index = -1)
	// EXTRA make a shop
	public InventoryGUI(Inventory inv) {
		super();
		this.inShop = Game.map.getTypeAtPlayerPos() == Terrain.CITY;
		playerInv = inv;
		buttons = new JButton[6];
		setLayout(new GridLayout(2, 3));
		for (int i = 0; i < 6; i++) {
			if (i >= playerInv.size()) {
				JButton noItem = new JButton("empty");
				noItem.setEnabled(false);
				buttons[i] = noItem;
			} else {
				if (!inShop) {
					ItemButton item = new ItemButton(playerInv.get(i), i, false);
					buttons[i] = item;
				} else {
					ItemButton item = new ItemButton(playerInv.get(i), i, true);
					buttons[i] = item;
				}
			}
		}
		for (int k = 0; k < 6; k++)
			add(buttons[k]);
		setVisible(true);
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		removeAll();
		buttons = new JButton[6];
		setLayout(new GridLayout(2, 3));
		for (int i = 0; i < 6; i++) {
			if (i >= playerInv.size()) {
				JButton noItem = new JButton("empty");
				noItem.setEnabled(false);
				buttons[i] = noItem;
			} else {

				if (!inShop) {
					ItemButton item = new ItemButton(playerInv.get(i), i, false);
					buttons[i] = item;
				} else {
					ItemButton item = new ItemButton(playerInv.get(i), i, true);
					buttons[i] = item;
				}
			}
		}
		for (int k = 0; k < 6; k++)
			add(buttons[k]);
	}
}
