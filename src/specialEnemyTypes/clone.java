package specialEnemyTypes;

import base.Equipable;
import base.Inventory;
import base.Item;
import base.Monster;
import base.Player;

public class clone extends Monster {

	public clone(Player p) {
		super("Clone", p.getHP(), p.getLVL(), 50, 800, new Inventory(p.getInventory()));
		Inventory inv = p.getInventory();
		for(Item item : inv)
			if(item instanceof Equipable)
				((Equipable)item).equip();
	}

}
