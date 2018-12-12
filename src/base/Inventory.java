package base;

import java.util.ArrayList;

public class Inventory extends ArrayList<Item> {
	private static final long serialVersionUID = 4022860173714468873L;

	public Inventory() {
		super(5);
	}

	public Inventory(Inventory clone) {
		super(clone);
	}

	public Inventory(Weapon weapon, Armour armour) {
		super(5);
		weapon.equip();
		armour.equip();
		add(weapon);
		add(armour);
	}

	// this is used for looting
	public void add(Inventory inventory) {
		for (Item i : inventory) {
			if (i instanceof Equipable) {
				if (((Equipable) i).isLootable())
					add(i);
			} else
				add(i);
		}
	}

	@Override
	public boolean add(Item i) {
		for (int k = 0; k < size(); k++) {
			if (get(k).getName().equals(i.getName()) && get(k) instanceof Consumable) {
				((Consumable) get(k)).changeQuantity(((Consumable) i).getQuantity());
				return true;
			}
		}
		if (size() < 6)
			return super.add(i);

		Game.GUI.output("!!!You cannot carry anymore!!!");
		return false;
	}

	public int calculateACBonus() {
		for (Item i : this)
			if (i instanceof Armour && ((Equipable) i).isEquiped())
				return ((Armour) i).getDefence();
		return 0;
	}

	public void equip(int[] indexes) {
		for (int i : indexes)
			if (get(i) instanceof Equipable)
				((Equipable) get(i)).equip();
	}

	public Consumable[] getConsumables() {
		ArrayList<Consumable> cons = new ArrayList<Consumable>();
		for (Item i : this)
			if (i instanceof Consumable)
				cons.add((Consumable) i);
		Consumable[] c = new Consumable[cons.size()];
		for (int i = 0; i < cons.size(); i++)
			c[i] = cons.get(i);
		return c;
	}

	public Weapon getWeapon() {
		for (Item i : this)
			if (i instanceof Weapon && ((Weapon) i).isEquiped())
				return (Weapon) i;
		return new Weapon("Fists", 0, 2, false);
	}

	public void refresh() {
		for (int i = 0; i < size(); i++) {
			if (get(i) instanceof Consumable && ((Consumable) get(i)).getQuantity() <= 0)
				super.remove(i);
		}
	}

	@Override
	public Item remove(int index) {
		if (get(index) instanceof Consumable) {
			Consumable c = ((Consumable) get(index));
			c.changeQuantity(-1);
			refresh();
			return c;

		} else
			return super.remove(index);
	}

	public void sell(int index) {
		if (get(index) instanceof Consumable) {
			((Consumable) get(index)).changeQuantity(-1);
			Game.player.gainGold(get(index).getValue());
		} else
			Game.player.gainGold(remove(index).getValue());
		refresh();
	}

	@Override
	public String toString() {
		String s = "Player has:\n";
		for (Item i : this)
			s += '-' + i.toString() + '\n';
		return s;
	}
}
