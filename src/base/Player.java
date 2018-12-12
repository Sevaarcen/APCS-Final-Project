package base;

import GUI.GUIManager;
import consumableTypes.healthPotion;

public class Player implements Creature {
	private GUIManager GUI;
	int hp, lvl, xp, maxHP, gold;
	private Inventory inventory;

	public Player() {
		GUI = Game.GUI;
		hp = 20;
		maxHP = hp;
		lvl = 1;
		xp = 0;
		gold = 0;
		inventory = new Inventory();
		inventory.add(new Weapon("Longsword", 250, 8, true));
		inventory.add(new Armour("Leather Armour", 175, 4, true));
		inventory.add(new healthPotion(2));
		// inventory.add(new banana());
		inventory.equip(new int[] { 0, 1 });
	}

	@Override
	public void attack(Creature target) {
		Weapon equiped = inventory.getWeapon();
		int roll = (int) (Math.random() * 20 + 1) + lvl + equiped.getDamage();
		int damage = (int) ((equiped.getDamage()) + lvl
				+ (Math.random() * equiped.getDamage() - equiped.getDamage() / 2.0));
		if (roll < ((Monster) target).getAC())
			GUI.output("Your " + equiped.getName() + " has bounced off their armour!");
		else {
			GUI.output("You have struck your foe for " + damage + " damage");
			target.damage(damage);
		}
	}

	// this clears the overload on HP
	public void combatEnd() {
		if (hp > maxHP)
			hp = maxHP;
	}

	@Override
	public void damage(int i) {
		hp -= i;
		if (hp <= 0)
			death();
	}

	@Override
	public void death() {
		hp = -1;
		Game.gameOver();
	}

	public void fieldRest() {
		for(int i = 0; i < 3 && Game.encounter == null; i++)
			Game.encounterCheck();
		if (Game.encounter == null)
			rest();
		else
			GUI.output("!!!You were ambushed in your sleep!!!");
	}

	public void gainGold(int gp) {
		gold += gp;
	}

	public void gainXP(int amount) {
		xp += amount;
		while (xp >= 25) {
			lvl++;
			hp += 4;
			maxHP += 4;
			xp -= 25;
			GUI.output("You have leveled up to level: " + lvl);
		}
	}

	@Override
	public int getAC() {
		return 5 + lvl + inventory.calculateACBonus();
	}

	public int getGold() {
		return gold;
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

	public int getXP() {
		return xp;
	}

	// NOTE you can overload past maxHP, that is a feature not a bug
	@Override
	public void heal(int i) {
		hp += i;
	}

	public void lolBadCodeOrder(GUIManager gui) {
		GUI = gui;
	}

	public void rest() {
		hp = maxHP;
		GUI.output("You have rested and are at full HP!");
		GUI.repaint();
	}

	public void setMaxHP(int newMax) {
		maxHP = newMax;
		rest();
	}

}
