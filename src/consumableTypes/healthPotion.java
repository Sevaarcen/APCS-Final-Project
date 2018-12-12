package consumableTypes;

import base.Consumable;
import base.Creature;
import base.Effect;

public class healthPotion extends Consumable {

	public healthPotion() {
		super("Health Potion", 200, 1, new healEffect());
	}

	public healthPotion(int amount) {
		super("Health Potion", 200, amount, new healEffect());
	}

}

class healEffect implements Effect {

	@Override
	public void activate(Creature target) {
		int hpGain = target.getMaxHP() / 3;
		if (hpGain < 8)
			hpGain = 8;
		target.heal(hpGain);
	}

}