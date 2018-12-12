package GUI;

import base.Consumable;

import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;

import base.Game;
import base.Inventory;
import base.Player;

public class ConsumablesMenu extends JPanel {
	private static final long serialVersionUID = 2473076752421070602L;

	JComboBox<Object> things;
	Player player;
	Inventory inventory;
	JButton use;
	GUIManager GUI;
	int lastSize;
	static boolean forceRecreate = false;

	public ConsumablesMenu(GUIManager g, Object[] items) {
		GUI = g;
		player = Game.player;
		inventory = player.getInventory();
		lastSize = inventory.size();
		setLayout(new GridLayout(2, 1));
		things = new JComboBox<Object>(items);
		things.addActionListener(new UseListener());
		use = new JButton("Consume");
		use.addActionListener(new UseListener());
		add(things);
		add(use);
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		if (forceRecreate || inventory.size() != lastSize) {
			lastSize = inventory.size();
			forceRecreate = false;
			removeAll();
			things = new JComboBox<Object>(inventory.getConsumables());
			add(things);
			add(use);
		}
		validate();
	}

	// deselecting the combo box actually doesnt end up calling action listener!
	// FIXME breaks key listener if you dont select something and click off onto
	// the map
	private class UseListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (things.getSelectedItem() != null) {
				if (e.getSource() == use) {
					Consumable c = ((Consumable) things.getSelectedItem());
					if (c.getQuantity() > 0)
						c.consume(player);
					if (c.getQuantity() <= 0)
						forceRecreate = true;
				}
				if (e.getSource() == things)
					System.out.println(things.getSelectedItem());
			}
			GUI.repaint();
			GUI.requestFocus();
		}

	}
}
