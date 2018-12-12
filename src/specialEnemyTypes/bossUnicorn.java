package specialEnemyTypes;

import GUI.GUIManager;
import base.Armour;
import base.Creature;
import base.Game;
import base.Inventory;
import base.Monster;
import base.Player;
import base.Weapon;
import consumableTypes.imortalityPotion;

public class bossUnicorn extends Monster {
	GUIManager GUI;
	Player p;

	public bossUnicorn() {
		super("Pegasus Unicorn", 1000, 120, 250, 500,
				new Inventory(new Weapon("Unicorn Horn", 5000, 50, true), new Armour("Pegasus Wings", 7500, 50, true)));
		GUI = Game.GUI;
		p = Game.player;
	}

	@Override
	public void attack(Creature target) {
		if (!Game.bananaEaten)
			super.attack(target);
		else {
			Game.victory();
			damage(getMaxHP());
		}

	}

	@Override
	public void death() {
		GUI.output("The unicorn walks up to you and eats your banana");
		GUI.output("It died, because pottasium is deadly to unicorns");
		p.gainGold(500);
		p.gainXP(250);
	}

	@Override
	public void loot() {
		p.getInventory().add(new imortalityPotion(2));
		p.getInventory().add(getInventory());
		GUI.output("You have looted new items!");
		GUI.repaint();
	}

}
