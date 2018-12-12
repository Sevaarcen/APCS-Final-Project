package base;

import GUI.GUIManager;
import GUI.GUIManager.State;
import base.MonsterDB.Monsters;

public class Encounter {
	GUIManager GUI;
	Player player;
	Monster enemy;
	private static final int LEVELSCALE = 13;

	public Encounter(Player p, Monster m) {
		GUI = Game.GUI;
		GUI.output("You are under attack by a " + m.getName());
		player = p;
		enemy = m;
	}

	// POLISH move to a better system of choosing CR
	public static Encounter createNewRandom() {
		int roll = (int) (Math.random() * 100 + 1) + (LEVELSCALE*(Game.player.getLVL() - 1));
		if (roll <= 40)
			return new Encounter(Game.player, MonsterDB.createNew(Monsters.GOBLIN));
		if (roll <= 80)
			return new Encounter(Game.player, MonsterDB.createNew(Monsters.BANDIT));
		if (roll <= 100)
			return new Encounter(Game.player, MonsterDB.createNew(Monsters.ASSASSINVINE));
		if (roll <= 140)
			return new Encounter(Game.player, MonsterDB.createNew(Monsters.BANDITLEADER));
		if (roll <= 175)
			return new Encounter(Game.player, MonsterDB.createNew(Monsters.CAVETROLL));
		if (roll <= 210)
			return new Encounter(Game.player, MonsterDB.createNew(Monsters.ORANGUTAN));
		if (roll <= 260)
			return new Encounter(Game.player, MonsterDB.createNew(Monsters.DRAGON));
		if (roll <= 500)
			return new Encounter(Game.player, MonsterDB.createNew(Monsters.DEMONLORD));
		else
			return new Encounter(Game.player, MonsterDB.createNew(Monsters.CLONE));
	}

	public Monster getEnemy() {
		return enemy;
	}

	public Player getPlayer() {
		return player;
	}

	public boolean continueCombat() {
		return player.getHP() > 0 && enemy.getHP() > 0;
	}

	public void endTurn() {
		if (!(continueCombat())) {
			endCombat();
		}
	}

	public void combatTurn() {
		player.attack(enemy);
		if (continueCombat())
			enemy.attack(player);
		endTurn();
	}

	public void endCombat() {
		player.combatEnd();
		if (enemy.getHP() <= 0)
			enemy.loot();
		GUI.changeState(State.MAP);
		GUI.changeView(Game.map, true);
		Game.encounter = null;
	}

	@Deprecated
	public void combatTurnSim() {
		player.attack(enemy);
		enemy.attack(player);
		endTurn();
	}

	@Deprecated
	public void combatFullSim() {
		while (continueCombat()) {
			combatTurnSim();
		}
	}

	@Deprecated
	public void combatTurnAmountSim(int i) {
		while (i > 0) {
			combatTurnSim();
			player.rest();
			enemy.heal(enemy.getMaxHP());
			i--;
		}
		enemy.heal(-enemy.getHP());
		endTurn();
	}

	public void runAway() {
		int eRoll = (int) (Math.random() * 20 + enemy.getLVL());
		int pRoll = (int) (Math.random() * 20 + 2 * player.getLVL());
		if (pRoll >= eRoll) {
			GUI.output("You have run away");
			endCombat();
		} else {
			GUI.output("You were chased down!");
			enemy.attack(player);
		}

	}
}
