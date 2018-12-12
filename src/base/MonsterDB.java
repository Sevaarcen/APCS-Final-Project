package base;

import specialEnemyTypes.bossUnicorn;
import specialEnemyTypes.clone;
import specialEnemyTypes.orangutan;

public class MonsterDB {

	// TODO make more monsters with good loot
	public enum Monsters {
		ASSASSINVINE, BANDIT, BANDITLEADER, bossUNICORN, CAVETROLL, CLONE, DEMONLORD, DRAGON, ERROR, GOBLIN, ORANGUTAN
	};

	public static Monster assassinVine() {
		return new Monster("Assassin Vine", 30, 3, 20, 0,
				new Inventory(new Weapon("Vine", 25, 8, true), new Armour("Hard Exterior", 0, 6, false)));
	}

	public static Monster badCodeCausedAnError() {
		return new Monster("ERROR 404", 99999, 99999, 99999, 99999, new Inventory(
				new Weapon("BAD CODE", 99999, 99999, false), new Armour("CODE PROTECTION", 99999, 99999, false)));
	}

	public static Monster bandit() {
		return new Monster("Bandit", 20, 2, 10, 100,
				new Inventory(new Weapon("Club", 25, 6, true), new Armour("Jerkin", 75, 2, true)));
	}

	public static Monster banditLeader() {
		return new Monster("Bandit Leader", 30, 4, 25, 100,
				new Inventory(new Weapon("Halberd", 500, 10, true), new Armour("Chainmail", 600, 6, true)));
	}

	public static Monster caveTroll() {
		return new Monster("Cave Troll", 40, 5, 35, 250,
				new Inventory(new Weapon("Large Rock", 10, 10, true), new Armour("Rocky Skin", 0, 10, false)));
	}

	public static Monster createNew(Monsters m) {
		switch (m) {
		case GOBLIN:
			return goblin();
		case BANDIT:
			return bandit();
		case ASSASSINVINE:
			return assassinVine();
		case BANDITLEADER:
			return banditLeader();
		case CAVETROLL:
			return caveTroll();
		case ORANGUTAN:
			return new orangutan();
		case DRAGON:
			return dragon();
		case DEMONLORD:
			return demonLord();
		case CLONE:
			return new clone(Game.player);
		case bossUNICORN:
			return new bossUnicorn();
		default:
			return badCodeCausedAnError();
		}
	}

	public static Monster demonLord() {
		return new Monster("Demon Lord", 120, 15, 100, 800, new Inventory(
				new Weapon("Flaming Greatsword", 4000, 24, true), new Armour("Infernal Platemail", 2400, 24, true)));
	}

	public static Monster dragon() {
		return new Monster("Dragon", 80, 10, 100, 500,
				new Inventory(new Weapon("Fire Breath", 0, 16, false), new Armour("Dragon Scales", 600, 16, true)));
	}

	// Enemy(name, hp, maxHP, level, xpReward, gpReward, new Inventory(new
	// Weapon(name, value, damage)));
	public static Monster goblin() {
		return new Monster("Goblin", 12, 1, 5, 25,
				new Inventory(new Weapon("Dirk", 50, 5, true), new Armour("Goblin Clothes", 10, 1, true)));
	}
}
