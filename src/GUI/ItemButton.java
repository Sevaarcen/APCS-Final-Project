package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import base.Armour;
import base.Consumable;
import base.Equipable;
import base.Game;
import base.Item;
import base.Weapon;

public class ItemButton extends JButton {
	private static final long serialVersionUID = 6174912295746760510L;

	Item item;
	int index;
	JButton discard, sell;
	ClassLoader cl;

	public ItemButton(Item i, int inventoryIndex, boolean shop) {
		item = i;
		index = inventoryIndex;
		cl = getClass().getClassLoader();
		discard = new JButton(new ImageIcon(cl.getResource("redX.gif")));
		discard.setBackground(Color.white);
		discard.addActionListener(new DiscardListener());
		sell = new JButton(new ImageIcon(cl.getResource("money.gif")));
		sell.setBackground(Color.white);
		sell.addActionListener(new SaleListener());

		setLayout(new GridLayout(3, 1));
		if (item instanceof Consumable) {
			JLabel itemNameandValue = new JLabel(
					((Consumable) item).getQuantity() + "x " + item.getName() + " (" + item.getValue() + "gp each)",
					SwingConstants.CENTER);
			itemNameandValue.setFont(new Font(getFont().getName(), Font.BOLD, 16));
			add(itemNameandValue);
			add(new JLabel());
		} else if (item instanceof Weapon) {
			JLabel itemNameandValue = new JLabel(item.getName() + " (" + item.getValue() + "gp)",
					SwingConstants.CENTER);
			itemNameandValue.setFont(new Font(getFont().getName(), Font.BOLD, 16));
			JLabel damageInfo = new JLabel(((Weapon) item).getDamage() + " damage", SwingConstants.CENTER);
			add(itemNameandValue);
			add(damageInfo);
		} else if (item instanceof Armour) {
			JLabel itemNameandValue = new JLabel(item.getName() + " (" + item.getValue() + "gp)",
					SwingConstants.CENTER);
			itemNameandValue.setFont(new Font(getFont().getName(), Font.BOLD, 16));
			JLabel defenceInfo = new JLabel(((Armour) item).getDefence() + " defence", SwingConstants.CENTER);
			add(itemNameandValue);
			add(defenceInfo);
		} else {
			JLabel itemNameandValue = new JLabel(item.getName() + " (" + item.getValue() + "gp)",
					SwingConstants.CENTER);
			itemNameandValue.setFont(new Font(getFont().getName(), Font.BOLD, 16));
			add(itemNameandValue);
			add(new JLabel());
		}
		addActionListener(new EquipListener());
		if (!(item instanceof Equipable))
			setEnabled(false);
		if (item instanceof Equipable && ((Equipable) item).isEquiped())
			setBackground(Color.GREEN);
		else if (item instanceof Equipable)
			setBackground(Color.RED);
		if (!shop)
			add(discard);
		else {
			add(sell);
			setBackground(Color.gray);
		}

	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		if (item instanceof Equipable)
			if (((Equipable) item).isEquiped())
				setBackground(Color.green);
			else
				setBackground(Color.red);
	}

	public void overrideIndex(int newIndex) {
		index = newIndex;
	}

	private class EquipListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			((Equipable) item).toggleEquip();
			repaint();
		}
	}

	private class DiscardListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			Game.player.getInventory().remove(index);
			Game.GUI.output("You have dropped your " + item.getName());
			Game.GUI.repaint();
		}
	}

	private class SaleListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			Game.player.getInventory().sell(index);
			Game.GUI.output("You sold your " + item.getName());
			Game.GUI.repaint();
		}
	}
}
