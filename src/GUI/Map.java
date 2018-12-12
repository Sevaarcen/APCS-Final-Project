package GUI;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import javax.swing.JPanel;

import GUI.Tile.Terrain;
import base.Game;

public class Map extends JPanel {
	private static final long serialVersionUID = 5269409411316586390L;

	Tile[][] map;
	Image playerImage;
	int posX, posY;

	public Map() {
		// EXTRA make maps read from a file
		// EXTRA make a map maker
		posX = 5;
		posY = 5;

		try {
			playerImage = Game.loader.loadImage("Player1.gif");
		} catch (IOException e) {
			e.printStackTrace();
		}

		Tile plains = new Tile(Terrain.PLAIN);
		Tile city = new Tile(Terrain.CITY);
		Tile boss = new Tile(Terrain.BOSS);
		Tile dungeon = new Tile(Terrain.DUNGEON);
		Tile jungle = new Tile(Terrain.JUNGLE);
		map = new Tile[][]
			  { { plains, plains, plains, plains, dungeon, plains, jungle, jungle, jungle, jungle },
				{ plains, plains, plains, plains, plains, plains, plains, plains, jungle, jungle },
				{ plains, city, plains, plains, plains, plains, plains, city, plains, jungle },
				{ plains, plains, plains, plains, plains, plains, plains, plains, plains, plains },
				{ plains, plains, plains, plains, boss, city, plains, plains, dungeon, plains },
				{ dungeon, plains, plains, plains, plains, plains, plains, plains, plains, plains },
				{ plains, plains, plains, plains, plains, plains, plains, plains, plains, plains },
				{ plains, plains, plains, plains, plains, plains, plains, plains, plains, city },
				{ plains, plains, city, plains, plains, plains, dungeon, plains, plains, plains },
				{ plains, plains, plains, plains, plains, plains, plains, plains, plains, plains } };
		setPreferredSize(new Dimension(800, 800));
		setVisible(true);
	}

	@Override
	public void paint(Graphics g) {
		// draws in reverse so that the rows are col so it is easier to read
		super.paint(g);
		for (int col = 0; col < map.length; col++)
			for (int row = 0; row < map[0].length; row++) {
				g.drawImage(map[col][row].getImage(), row * 80, col * 80, null);
			}
		g.drawImage(playerImage, posX * 80, posY * 80, null);
	}

	public void terraform(int row, int col, Terrain type) {
		map[row][col] = new Tile(type);
	}

	public Terrain getTerrainType(int row, int col) {
		return map[row][col].getType();
	}

	public Terrain getTypeAtPlayerPos() {
		return map[posY][posX].getType();
	}

	public void changePlayerPos(int xChange, int yChange) {
		// POLISH make movement animation
		posX += xChange;
		posY += yChange;
		if (checkBounds())
			if (getTypeAtPlayerPos() == Terrain.PLAIN)
				Game.encounterCheck();
	}

	// returns true if the player was in bounds
	public boolean checkBounds() {
		boolean notChanged = true;
		if (posX < 0) {
			posX = 0;
			notChanged = true;
		}
		if (posX >= map[0].length) {
			posX = map[0].length - 1;
			notChanged = true;
		}
		if (posY < 0) {
			posY = 0;
			notChanged = true;
		}
		if (posY >= map.length) {
			posY = map.length - 1;
			notChanged = true;
		}
		return notChanged;
	}

	public void interact() {
		map[posY][posX].interact();
	}
}
