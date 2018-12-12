package consumableTypes;

import base.Consumable;
import base.Creature;
import base.Effect;
import base.Game;
import specialEnemyTypes.bossUnicorn;

public class banana extends Consumable {
	public banana() {
		super("Banana", 1000, 1, new bananaEffect());
	}

	public banana(int amount) {
		super("Banana", 1000, amount, new bananaEffect());
	}
}

class bananaEffect implements Effect {

	@Override
	public void activate(Creature target) {
		Game.bananaEaten = true;
		if (Game.encounter != null && Game.encounter.getEnemy() instanceof bossUnicorn)
			Game.encounter.combatTurn();
		else
			Game.bananaEaten = false;
	}

}
