package specialEnemyTypes;

import GUI.GUIManager;
import base.Armour;
import base.Game;
import base.Inventory;
import base.Monster;
import base.Player;
import base.Weapon;
import consumableTypes.banana;

public class orangutan extends Monster {

	GUIManager GUI;
	Player p;

	public orangutan() {
		super("Orangutan", 60, 8, 40, 0,
				new Inventory(new Weapon("Banana as a Club", 250, 5, true), new Armour("Fur", 0, 3, false)));
		GUI = Game.GUI;
		p = Game.player;
	}

	@Override
	public void loot() {
		p.getInventory().add(new banana());
		GUI.output("You have looted a banana!");
		p.getInventory().add(getInventory());
		GUI.output("You have looted new items!");
		GUI.repaint();
	}

}
