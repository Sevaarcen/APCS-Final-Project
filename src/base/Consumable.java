package base;

public class Consumable implements Item {
	String name;
	Effect effect;
	int value, amount;

	public Consumable(String name, int value, int amount, Effect effect) {
		this.name = name;
		this.value = value;
		this.amount = amount;
		this.effect = effect;
	}

	public void consume(Player player) {
		amount -= 1;
		player.getInventory().refresh();
		Game.GUI.output("You have consumed a " + name);
		effect.activate(player);
	}

	public void changeQuantity(int change) {
		amount += change;
	}

	@Override
	public int getValue() {
		return value;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return amount + "x " + name + ", worth " + value + "gp each";
	}

	public int getQuantity() {
		return amount;
	}
}
