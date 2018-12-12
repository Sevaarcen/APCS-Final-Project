package consumableTypes;

import base.Consumable;
import base.Creature;
import base.Effect;
import base.Player;

public class imortalityPotion extends Consumable {

	public imortalityPotion() {
		super("Imortality Potion", 2500, 1, new imortalityEffect());
	}

	public imortalityPotion(int quantity) {
		super("Imortality Potion", 2500, quantity, new imortalityEffect());
	}

}

class imortalityEffect implements Effect {
	@Override
	public void activate(Creature target) {
		if (target instanceof Player) {
			((Player) target).setMaxHP(target.getMaxHP() + 4);
			((Player) target).rest();
		}
	}

}
