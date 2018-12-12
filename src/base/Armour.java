package base;

public class Armour implements Item, Equipable {
	String name;
	int value, defence;
	boolean equiped, lootable;

	public Armour(String name, int value, int defence, boolean lootable) {
		this.name = name;
		this.value = value;
		this.defence = defence;
		this.lootable = lootable;
	}

	@Override
	public boolean isEquiped() {
		return equiped;
	}

	@Override
	public void equip() {
		equiped = true;
	}

	@Override
	public void unequip() {
		equiped = false;
	}

	@Override
	public int getValue() {
		return value;
	}

	public int getDefence() {
		return defence;
	}

	@Override
	public String toString() {
		System.out.println("Lootable: " + lootable);
		return this.name + ", worth " + value + "gp and gives " + defence + " defence";
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void toggleEquip() {
		equiped = !equiped;
	}

	@Override
	public boolean isLootable() {
		return lootable;
	}
}
