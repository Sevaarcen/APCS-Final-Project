package base;

import GUI.GUIManager;
import consumableTypes.healthPotion;

public class Monster implements Creature {
	GUIManager GUI;
	int hp, maxHP, lvl, xpReward, gpReward;
	Inventory inventory;
	String name;
	Player p;

	public Monster(String name, int hp, int lvl, int xpReward, int gpReward, Inventory inventory) {
		GUI = Game.GUI;
		p = Game.player;
		this.name = name;
		this.hp = hp;
		maxHP = hp;
		this.lvl = lvl;
		this.xpReward = xpReward;
		this.gpReward = gpReward;
		this.inventory = inventory;
	}

	@Override
	public void attack(Creature target) {
		Weapon equiped = inventory.getWeapon();
		int roll = (int) (Math.random() * 20 + 1) + lvl + equiped.getDamage();
		int damage = (int) ((equiped.getDamage()) + lvl
				+ (Math.random() * equiped.getDamage() - equiped.getDamage() / 2.0));
		if (roll < ((Player) target).getAC())
			GUI.output("Your enemy's " + equiped.getName() + " bounced off your armour!");
		else {
			GUI.output("The " + name + " has hit you for " + damage + " damage");
			target.damage(damage);
		}
	}

	@Override
	public void damage(int i) {
		hp -= i;
		if (hp <= 0)
			death();
	}

	@Override
	public void death() {
		GUI.output("You have slain a " + name + "\nYou gained " + xpReward + "xp and " + gpReward + "gp");
		p.gainGold(gpReward);
		p.gainXP(xpReward);
		hp = -1;
		if (name.equals("ERROR 666"))
			try {
				throw new IllegalAccessException("YOU CANNOT KILL BAD CODE!!1!!!");
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
	}

	@Override
	public int getAC() {
		return 5 + lvl + inventory.calculateACBonus();
	}

	public int getGPReward() {
		return gpReward;
	}

	@Override
	public int getHP() {
		return hp;
	}

	@Override
	public Inventory getInventory() {
		return inventory;
	}

	@Override
	public int getLVL() {
		return lvl;
	}

	@Override
	public int getMaxHP() {
		return maxHP;
	}

	public String getName() {
		return name;
	}

	public int getXPReward() {
		return xpReward;
	}

	@Override
	public void heal(int i) {
		hp += i;
	}

	public void loot() {
		// 1 chance to loot a potion per level
		for (int i = 0; i < lvl; i++) {
			// 25% chance to loot a potion
			if ((int) (Math.random() * 4 + 1) == 4) {
				p.getInventory().add(new healthPotion());
				GUI.output("You have looted new items!");
			}
		}
		p.getInventory().add(inventory);
		GUI.output("You have looted new items!");
		GUI.repaint();
	}
}
