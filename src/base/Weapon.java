package base;

public class Weapon implements Item, Equipable {
	boolean equiped, lootable;
	String name;
	int value, damage;

	public Weapon(String name, int value, int damage, boolean lootable) {
		this.name = name;
		this.value = value;
		this.damage = damage;
		this.lootable = lootable;
	}

	public void dealDamage(Creature target) {
		target.damage(damage);
	}

	@Override
	public void equip() {
		equiped = true;
	}

	public int getDamage() {
		return damage;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public int getValue() {
		return value;
	}

	@Override
	public boolean isEquiped() {
		return equiped;
	}

	@Override
	public boolean isLootable() {
		return lootable;
	}

	@Override
	public void toggleEquip() {
		equiped = !equiped;
	}

	@Override
	public String toString() {
		System.out.println("Lootable: " + lootable);
		return this.name + ", worth " + value + "gp and does " + damage + " damage";
	}

	@Override
	public void unequip() {
		equiped = false;
	}
}
