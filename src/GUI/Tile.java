package GUI;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import javax.swing.JPanel;

import base.Encounter;
import base.Game;
import base.MonsterDB;
import base.MonsterDB.Monsters;

public class Tile extends JPanel {
	private static final long serialVersionUID = -1418816566157778199L;

	// TODO create new detail tiles
	public enum Terrain {
		PLAIN, CITY, BOSS, DUNGEON, JUNGLE
	};

	Image image;
	Terrain type;
	private static final boolean bordered = false;

	public Tile(Terrain type) {
		String name = "";
		this.type = type;
		switch (type) {
		case PLAIN:
			name += "plain2";
			break;
		case CITY:
			name += "city2";
			break;
		case BOSS:
			name += "boss1";
			break;
		case DUNGEON:
			name += "caveEntrance";
			break;
		case JUNGLE:
			name += "jg";
			break;
		default:
			name += "pT";
		}
		if (bordered)
			name += 'B';
		name += ".gif";
		try {
			image = Game.loader.loadImage(name);
		} catch (IOException e) {
			e.printStackTrace();
		}
		setPreferredSize(new Dimension(80, 80));
	}

	public Image getImage() {
		return image;
	}

	@Override
	public void paint(Graphics g) {
		if (image == null)
			return;
		g.drawImage(image, 0, 0, null);
	}

	public Terrain getType() {
		return type;
	}

	@Override
	public String toString() {
		return type.toString();
	}

	public void interact() {
		if (Game.map.isShowing())
			switch (type) {
			case PLAIN:
				Game.GUI.output("There is nothing to interact with");
				break;
			case DUNGEON:
				Game.enterEncounter(new Encounter(Game.player, MonsterDB.createNew(Monsters.CAVETROLL)));
				break;
			case JUNGLE:
				Game.enterEncounter(new Encounter(Game.player, MonsterDB.createNew(Monsters.ORANGUTAN)));
				break;
			case BOSS:
				Game.enterEncounter(new Encounter(Game.player, MonsterDB.createNew(Monsters.bossUNICORN)));
				break;
			// case CITY: Game.GUI.changeView(new
			// shopGUI(Game.player.getInventory()), true);
			case CITY:
				if (Game.player.getGold() >= 1000) {
					Game.GUI.output("You have purchased a level for 1000gp");
					Game.player.gainGold(-1000);
					Game.player.gainXP(25);
				} else {
					Game.GUI.output("You cannot afford a level (1000gp)");
				}
			}
	}
}
