package base;

public interface Creature {
	public void death();

	public void damage(int amount);

	public void heal(int amount);

	public int getHP();

	public int getMaxHP();

	public int getLVL();

	public void attack(Creature target);

	public int getAC();

	public Inventory getInventory();
}
